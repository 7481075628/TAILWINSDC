package jgrasp_viewers;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import javax.swing.JPanel;
import jgrasp.Colors;
import jgrasp.Drawing;
import jgrasp.PluginOptOut;
import jgrasp.viewer.IndexItem;
import jgrasp.viewer.PaintUtil;
import jgrasp.viewer.Sizes;
import jgrasp.viewer.ViewerCreateData;
import jgrasp.viewer.ViewerException;
import jgrasp.viewer.ViewerInfo;
import jgrasp.viewer.ViewerInitData;
import jgrasp.viewer.ViewerPriorityData;
import jgrasp.viewer.ViewerUpdateData;
import jgrasp.viewer.ViewerValueData;
import jgrasp.viewer.ViewerRoot;
import jgrasp.viewer.jgrdi.DebugContext;
import jgrasp.viewer.jgrdi.Method;
import jgrasp.viewer.jgrdi.Type;
import jgrasp.viewer.jgrdi.Value;
import org.w3c.dom.Element;


/** A viewer that displays a bar graph for arrays and lists of integral or floating point type. **/
public class java__lang__Object_BarGraphView extends ViewerRoot {

   /** State data. **/
   public static class Data {
   
      /** The object value. **/
      private Value obj;
   
      /** The current values. **/
      private List<Long> values;
   
      /** The current float values. **/
      private List<Double> floatValues;
   
      /** Changed state for each value. **/
      private List<Boolean> changed;
   
      /** New state for each value. **/
      private List<Boolean> isNew;
   
      /** True if the data was derived from an array type, false if it came from a list. **/
      private boolean isArray;
   
      /** Index expressions. This may be null. **/
      private List<IndexItem> indexes;
   }

   /** Supported element types. **/
   private static final Collection<String> supportedTypes = new HashSet<>();

   /** Supported float types. **/
   private static final Collection<String> floatTypes = new HashSet<>();

   static {
      supportedTypes.add("long");
      supportedTypes.add("int");
      supportedTypes.add("short");
      supportedTypes.add("char");
      supportedTypes.add("byte");
      supportedTypes.add("java.lang.Long");
      supportedTypes.add("java.lang.Integer");
      supportedTypes.add("java.lang.Short");
      supportedTypes.add("java.lang.Char");
      supportedTypes.add("java.lang.Byte");
      supportedTypes.add("float");
      supportedTypes.add("double");
      supportedTypes.add("java.lang.Float");
      supportedTypes.add("java.lang.Double");
   }

   static {
      floatTypes.add("float");
      floatTypes.add("double");
      floatTypes.add("java.lang.Float");
      floatTypes.add("java.lang.Double");
   }

   /** Current state data. **/
   private Data stateData;

   /** Current font height in pixels. **/
   private int fontHeight;

   /** Drawing base unit, in pixels. **/
   private int margin;

   /** Height of index pointers. **/
   private int pointerLen;

   /** Current default size. **/
   private Dimension mainSize;

   /** Rectangle for first bar. **/
   private Rectangle barRect;


   /** Creates a new bar graph viewer.
    *
    * @param vcd viewer creation data. **/
   public java__lang__Object_BarGraphView(final ViewerCreateData vcd) {
      super(false);
      Value v = vcd.getValue();
      DebugContext dc = vcd.getDebugContext();
      Type t = v.getType(dc);
      try {
         if (!t.isArray(dc)) {
            if (v.isInstanceOf(dc, "java.util.List")) {
               return;
            }
         }
         else {
            Type elementType = t.getArrayElementType(dc);
            String tName = elementType.getName(dc);
            if (supportedTypes.contains(tName) || "java.lang.Object".equals(tName)) {
               return;
            }
         }
      }
      catch (ViewerException ignored) {
      }
      throw new PluginOptOut();
   }


   /** {@inheritDoc} **/
   @Override
   public void build(final ViewerInitData vid, final Element initDataIn) {
      super.build(vid, initDataIn);
      vid.setIndexable(true);
   }


   /** {@inheritDoc} **/
   @Override
   public void configure() {
      Data currentData = stateData;
      if (currentData == null) {
         return;
      }
   
      JPanel mp = getMainPanel();
      Graphics2D g = (Graphics2D) mp.getGraphics();
      if (g == null) {
         return;
      }
      g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
   
      g.setFont(mp.getFont());
      FontMetrics fm = g.getFontMetrics();
      fontHeight = fm.getHeight();
      margin = (fm.getHeight() + 3) / 4;
      List<? extends Number> currentValues = currentData.values == null ? currentData.floatValues
            : currentData.values;
      if (currentValues == null) {
         return;
      }
      int w = Math.max(50, currentValues.size()) * margin;
      int labelHeight = margin * 2 + fm.getHeight();
      int h = margin * 10 + labelHeight;
      if (currentData.indexes != null) {
         // Marker space.
         h += pointerLen * 2 + margin * 2;
         // Label space.
         h += fontHeight * currentData.indexes.size();
      }
   
      Dimension size = new Dimension(w, h);
      boolean sizeChanged = false;
      if (!size.equals(mainSize)) {
         mainSize = size;
         sizeChanged = true;
      }
      int minHeight = margin * 5 + labelHeight;
   
      pointerLen = (fontHeight + 2) / 4;
   
      if (currentData.indexes != null) {
         // Marker space.
         minHeight += pointerLen * 2 + margin * 2;
         // Label space.
         minHeight += fontHeight * currentData.indexes.size();
      }
   
      int count = currentValues.size();
      Dimension minSize = new Dimension(count + 2 * margin, minHeight);
   
      List<Double> floatValues = currentData.floatValues;
      List<Long> values = currentData.values;
      String minL;
      String maxL;
      if (floatValues != null) {
         double max = 0;
         double min = 0;
         if (!currentValues.isEmpty()) {
            max = Collections.max(floatValues).doubleValue();
            min = Collections.min(floatValues).doubleValue();
         }
         if (min > 0) {
            min = 0;
         }
         else if (max < 0) {
            max = 0;
         }
         minL = String.format("%.3g", Double.valueOf(min));
         maxL = String.format("%.3g", Double.valueOf(max));
      }
      else {
         long max = 0;
         long min = 0;
         if (!currentValues.isEmpty()) {
            max = Collections.max(values).longValue();
            min = Collections.min(values).longValue();
         }
         if (min > 0) {
            min = 0;
         }
         else if (max < 0) {
            max = 0;
         }
         minL = String.valueOf(min);
         maxL = String.valueOf(max);
      }
      Rectangle2D d = fm.getStringBounds(minL, g);
      double lw = d.getWidth();
      d = fm.getStringBounds(maxL, g);
      lw = Math.max(lw, d.getWidth());
      minSize.width += (int) Math.ceil(lw) + margin * 2;
   
      if (!mp.getPreferredSize().equals(minSize)) {
         mp.setPreferredSize(minSize);
         sizeChanged = true;
      }
      if (sizeChanged) {
         mp.revalidate();
         mp.validate();
      }
      mp.repaint();
   }


   /** {@inheritDoc} **/
   @Override
   public void destroy() {
   }


   /** {@inheritDoc} **/
   @Override
   public void getInfo(final ViewerInfo vi) {
      vi.setShortDescription("Viewer for integral and floating point arrays and lists");
      vi.setLongDescription("This viewer displays integral and floating point arrays and java.util.List "
            + "implementations as a bar graph.");
   }


   /** {@inheritDoc} **/
   @Override
   public Dimension getMainSize() {
      return mainSize;
   }


   /** {@inheritDoc} **/
   @Override
   public String getMainToolTipText(final MouseEvent e) {
      Data currentData = stateData;
      if (currentData == null || barRect == null) {
         return null;
      }
      int y = e.getY();
      if (y < barRect.y || y > barRect.y + barRect.height) {
         return null;
      }
      int x = e.getX();
      int ind = (x - barRect.x) / barRect.width;
      if (ind < 0) {
         return null;
      }
      if (currentData.floatValues != null) {
         if (ind >= currentData.floatValues.size()) {
            return null;
         }
         String val = String.valueOf(currentData.floatValues.get(ind));
         if (currentData.isArray) {
            return "[" + ind + "] = " + val;
         }
         return "<" + ind + "> = " + val;
      }
      if (ind >= currentData.values.size()) {
         return null;
      }
      String val = String.valueOf(currentData.values.get(ind));
      if (currentData.isArray) {
         return "[" + ind + "] = " + val;
      }
      return "<" + ind + "> = " + val;
   }


   /** {@inheritDoc} **/
   @Override
   public int getPriority(final ViewerPriorityData vpd) {
      Value v = vpd.getValue();
      DebugContext context = vpd.getDebugContext();
      try {
         if (v == null) {
            return Integer.MIN_VALUE;
         }
         Type type = v.getType(context);
         if (!type.isArray(context)) {
            if (v.isInstanceOf(context, "java.util.List")) {
               return -10;
            }
            return Integer.MIN_VALUE;
         }
         Type elementType = type.getArrayElementType(context);
         String elementTypeName = elementType.getName(context);
         if (!supportedTypes.contains(elementTypeName) && !"java.lang.Object".equals(elementTypeName)) {
            return Integer.MIN_VALUE;
         }
      }
      catch (ViewerException e) {
         return Integer.MIN_VALUE;
      }
      return -10;
   }


   /** {@inheritDoc} **/
   @Override
   public String getViewName() {
      return "Bar Graph";
   }


   /** {@inheritDoc} **/
   @Override
   public void paintMainView(final Graphics2D graphics, final JPanel paintPanel) {
      int l = Sizes.getVeryThinEdge(fontHeight);
      float lOffs = (l % 2) / 2.0f;
      if (!isViewerTransparent()) {
         graphics.setColor(Drawing.getColor(Colors.OUTLINE));
         graphics.fillRect(0, 0, paintPanel.getWidth(), paintPanel.getHeight());
         if (paintPanel.getWidth() > l + l) {
            graphics.setColor(Drawing.getColor(Colors.BG));
            graphics.fillRect(l, l, paintPanel.getWidth() - l - l, paintPanel.getHeight() - l - l);
         }
      }
      Data currentData = stateData;
      List<? extends Number> currentValues = currentData.values == null ? currentData.floatValues
            : currentData.values;
      if (currentValues == null) {
         return;
      }
   
      graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
   
      Dimension viewSize = paintPanel.getSize();
      FontMetrics fm = graphics.getFontMetrics();
      int fontAscent = fm.getAscent();
   
      int ht = viewSize.height - margin * 4 - fontAscent - fontAscent / 2;
      if (currentData.indexes != null) {
         // Marker space.
         ht -= (graphics.getFontMetrics().getHeight() + 2) / 4;
         ht -= margin * 2;
         // Label space.
         ht -= graphics.getFontMetrics().getHeight() * currentData.indexes.size();
      }
      if (ht < margin) {
         ht = margin;
      }
   
      List<Double> floatValues = currentData.floatValues;
      List<Boolean> changed = currentData.changed;
      List<Long> values = currentData.values;
   
      double scale;
      int zeroLine;
      int numberLine;
      int markerLine;
      boolean allPos = false;
      boolean allNeg = false;
      String minL;
      String maxL;
      int minLY;
      int maxLY;
      int minBar;
      int maxBar;
      if (floatValues != null) {
         double max = 0.0;
         double min = 0.0;
         if (!currentValues.isEmpty()) {
            max = Collections.max(floatValues).doubleValue();
            min = Collections.min(floatValues).doubleValue();
         }
         if (min >= 0.0) {
            min = 0.0;
            allPos = true;
         }
         if (max <= 0.0) {
            max = 0.0;
            allNeg = true;
         }
      
         scale = (max - min > 0.0) ? ht / (max - min) : 1.0;
         if (allPos) {
            zeroLine = margin + (int) (max * scale) + fontAscent / 2;
            numberLine = zeroLine;
            markerLine = numberLine + fontHeight + margin * 2;
         }
         else if (allNeg) {
            zeroLine = margin + (int) (max * scale) + margin * 2 + fontAscent;
            numberLine = zeroLine;
            markerLine = zeroLine + (int) (-min * scale) + margin * 2;
         }
         else {
            zeroLine = margin + (int) (max * scale) + fontAscent / 2;
            numberLine = zeroLine + (int) (-min * scale);
            markerLine = numberLine + fontHeight + margin * 2;
         }
         minL = String.format("%.3g", Double.valueOf(min));
         maxL = String.format("%.3g", Double.valueOf(max));
         minBar = (int) (min * scale);
         maxBar = (int) (max * scale);
         minLY = zeroLine - minBar;
         maxLY = zeroLine - maxBar;
      }
      else {
         long max = 0;
         long min = 0;
         if (!currentValues.isEmpty()) {
            max = Collections.max(values).longValue();
            min = Collections.min(values).longValue();
         }
         if (min >= 0) {
            min = 0;
            allPos = true;
         }
         if (max <= 0) {
            max = 0;
            allNeg = true;
         }
         scale = (max - min > 0) ? ht / (double) (max - min) : 1.0;
         if (allPos) {
            zeroLine = margin + (int) (max * scale) + fontAscent / 2;
            numberLine = zeroLine;
            markerLine = numberLine + fontHeight + margin * 2;
         }
         else if (allNeg) {
            zeroLine = margin + (int) (max * scale) + margin * 2 + fontAscent;
            numberLine = zeroLine;
            markerLine = zeroLine + (int) (-min * scale) + margin * 2;
         }
         else {
            zeroLine = margin + (int) (max * scale) + fontAscent / 2;
            numberLine = zeroLine + (int) (-min * scale);
            markerLine = numberLine + fontHeight + margin * 2;
         }
         minL = String.valueOf(min);
         maxL = String.valueOf(max);
         minBar = (int) (min * scale);
         maxBar = (int) (max * scale);
         minLY = zeroLine - minBar;
         maxLY = zeroLine - maxBar;
      }
   
      Rectangle2D d = fm.getStringBounds(minL, graphics);
      double minLW = d.getWidth();
      d = fm.getStringBounds(maxL, graphics);
      double maxLW = d.getWidth();
      int yLabelW = (int) Math.ceil(Math.max(minLW, maxLW)) + margin * 2;
   
      int w = viewSize.width - margin - margin - yLabelW;
      int size = currentValues.size();
      if (w < size) {
         w = size;
      }
   
      int maxLabelLen = (int) Math.log10(Math.max(1, size - 1)) + 1;
      int maxLabelW = (int) Math.ceil(
            fm.getStringBounds("000000000000000000", 0, Math.min(18, maxLabelLen), graphics).getWidth());
      int barW = size > 0 ? w / size : 0;
   
      int minLabelSep = (int) Math.ceil((maxLabelW + margin) / (double) barW);
      if (minLabelSep < 1) {
         minLabelSep = 1;
      }
      // Label on the 5s or the 10s.
      int label10Sep = (int) Math.ceil(Math.pow(10, Math.ceil(Math.log10(minLabelSep))));
      int label5Sep = 5 * (int) Math.ceil(Math.pow(10, Math.ceil(Math.log10(minLabelSep) - Math.log10(5))));
      int labelSep = Math.min(label10Sep, label5Sep);
   
      Color barColor = Drawing.convertForTheme(barW > 1 ? new Color(180, 180, 255) : new Color(90, 90, 130));
      Color barChangedColor = Drawing.convertForTheme(barW > 1 ? Color.RED : Color.RED.darker());
      Color barNewColor = Drawing.convertForTheme(barW > 1 ? Color.GREEN : Color.GREEN.darker());
   
      int halfMargin = margin / 2;
      int x = margin + yLabelW;
      graphics.setColor(Drawing.getColor(Colors.FG));
      graphics.setStroke(new BasicStroke(l, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 2.0f));
      if (minLY - maxLY >= fontAscent + margin || Math.abs(maxLY - zeroLine) >= Math.abs(minLY - zeroLine)) {
         graphics.drawString(maxL, x - (int) Math.ceil(maxLW) - margin - margin, maxLY + fontAscent / 2);
         graphics.draw(new Line2D.Float(x - margin - halfMargin + lOffs, maxLY + lOffs, x - halfMargin + lOffs,
               maxLY + lOffs));
      }
      if (minLY - maxLY >= fontAscent + margin || Math.abs(maxLY - zeroLine) < Math.abs(minLY - zeroLine)) {
         graphics.drawString(minL, x - (int) Math.ceil(minLW) - margin - margin, minLY + fontAscent / 2);
         graphics.draw(new Line2D.Float(x - margin - halfMargin + lOffs, minLY + lOffs, x - halfMargin + lOffs,
               minLY + lOffs));
      }
   
      barRect = new Rectangle(x, maxLY, barW, minLY - maxLY);
   
      startBarPaint();
      for (int v = 0; v < size; v++, x += barW) {
         int barH;
         boolean positive;
         if (floatValues != null) {
            double val = floatValues.get(v).doubleValue();
            barH = (int) (Math.abs(val) * scale);
            positive = val >= 0.0;
         }
         else {
            long val = values.get(v).longValue();
            barH = (int) (Math.abs(val) * scale);
            positive = val >= 0;
         }
         int y = positive ? zeroLine - barH : zeroLine;
         boolean ch = changed != null && changed.get(v).booleanValue();
         if (ch) {
            changedValue(v, size, barH * (positive? 1 : -1), minBar, maxBar);
         }
         boolean isNew = currentData.isNew == null || currentData.isNew.get(v).booleanValue();
         graphics.setColor(ch ? barChangedColor : (isNew ? barNewColor : barColor));
         Shape rect = new Rectangle2D.Double(x + (barW == 1 ? 1 : 0) + lOffs, y + lOffs, barW, barH);
         graphics.fill(rect);
         graphics.setColor(Drawing.getColor(Colors.OUTLINE));
         if (barW > 1) {
            graphics.draw(rect);
         }
      
         if (v % labelSep == 0) {
            String label = String.valueOf(v);
            int labelWidth = (label.isEmpty())? 0
                  : (int) Math.ceil(fm.getStringBounds(label, graphics).getWidth());
            int linePos = x + barW / 2;
            graphics.setColor(Drawing.getColor(Colors.FG));
            if (allNeg && !allPos) {
               y = numberLine - margin * 2;
               graphics.draw(new Line2D.Float(linePos + lOffs, (y + halfMargin) + lOffs, linePos + lOffs,
                     y + margin + halfMargin + lOffs));
            }
            else {
               y = numberLine + fontAscent + margin * 2;
               graphics.draw(new Line2D.Float(linePos + lOffs, numberLine + halfMargin + lOffs, linePos + lOffs,
                     numberLine + margin + halfMargin + lOffs));
            }
            graphics.drawString(label, (int) (x + barW / 2.0 - labelWidth / 2.0), y);
         }
      }
      endBarPaint();
      graphics.setColor(Drawing.getColor(Colors.FG));
      graphics.draw(new Line2D.Float(margin + yLabelW + lOffs, zeroLine + lOffs,
            margin + yLabelW + barW * size + lOffs, zeroLine + lOffs));
      graphics.draw(new Line2D.Float(margin + yLabelW + lOffs, maxLY + lOffs, margin + yLabelW + lOffs,
            minLY + lOffs));
   
      if (currentData.indexes != null) {
         int[] xCenters = new int[size];
         for (int i = 0; i < size; i++) {
            xCenters[i] = margin + yLabelW + i * barW + barW / 2;
         }
         PaintUtil.paintHorizontalIndexes(graphics, xCenters, markerLine, margin + yLabelW, viewSize.width - margin,
               pointerLen, currentData.indexes, 0);
      }
   }


   /** {@inheritDoc} **/
   @Override
   public void updateState(final ViewerValueData valueData, final ViewerUpdateData data, final DebugContext context)
         throws ViewerException {
      Data oldData = stateData;
      Value value = valueData.getValue();
      Type type = value.getType(context);
      Data newData = new Data();
      newData.obj = value;
      if (!type.isArray(context)) {
         if (value.isInstanceOf(context, "java.util.List")) {
            Method toArrayMethod = type.getMethod(context, "toArray", "java.lang.Object[]", null);
            value = value.invokeMethod(context, toArrayMethod, null);
            type = value.getType(context);
         }
         else {
            stateData = newData;
            return;
         }
      }
      else {
         newData.isArray = true;
      }
      Type elementType = type.getArrayElementType(context);
      boolean isObject = elementType.isObject(context);
   
      int len = value.getArrayLength(context);
      boolean isOneFloat = false;
      List<Long> newValues = null;
      List<Double> newFloatValues = null;
      Method longValue = null;
      Type lastType = null;
      Method doubleValue = null;
      Type lastFloatType = null;
      List<Value> values = value.getArrayElements(context, 0, len);
      for (int d = 0; d < len; d++) {
         Value v = values.get(d);
         Type t = isObject? v.getType(context) : elementType;
         String tName = t.getName(context);
         boolean isFloat = floatTypes.contains(tName);
         if (!isOneFloat && isFloat) {
            isOneFloat = true;
            if (newValues != null) {
               newFloatValues = new ArrayList<>(len);
               for (int i = 0; i < d; i++) {
                  Long l = newValues.get(i);
                  newFloatValues.add(l.doubleValue());
               }
            }
         }
      
         if (isOneFloat) {
            if (newFloatValues == null) {
               newFloatValues = new ArrayList<>(len);
            }
         }
         else {
            if (newValues == null) {
               newValues = new ArrayList<>(len);
            }
         }
      
         if (!supportedTypes.contains(tName)) {
            if (isOneFloat) {
               newFloatValues.add(Double.valueOf(0.0));
            }
            else {
               newValues.add(Long.valueOf(0));
            }
            continue;
         }
      
         if (isObject) {
            if (v.isNull()) {
               if (isOneFloat) {
                  newFloatValues.add(Double.valueOf(0.0));
               }
               else {
                  newValues.add(Long.valueOf(0));
               }
            }
            else {
               if (isFloat) {
                  if (!t.equals(lastFloatType)) {
                     doubleValue = t.getMethod(context, "doubleValue", "double", null);
                  }
                  lastType = t;
                  lastFloatType = t;
                  newFloatValues.add(v.invokeMethod(context, doubleValue, null).toDouble(context));
               }
               else {
                  if (!t.equals(lastType)) {
                     longValue = t.getMethod(context, "longValue", "long", null);
                  }
                  lastType = t;
                  if (isOneFloat) {
                     newFloatValues.add(Double.valueOf(Long.valueOf(v.invokeMethod(context, longValue, null)
                           .toLong(context)).doubleValue()));
                  }
                  else {
                     newValues.add(Long.valueOf(v.invokeMethod(context, longValue, null).toLong(context)));
                  }
               }
            }
         }
         else {
            if (isFloat) {
               newFloatValues.add(Double.valueOf(v.toDouble(context)));
            }
            else if (newValues != null /* Test is superfluous. */) {
               newValues.add(Long.valueOf(v.toLong(context)));
            }
         }
      }
      newData.values = newValues;
      newData.floatValues = newFloatValues;
      if (oldData != null && (oldData.floatValues != null && newFloatValues != null || oldData.values != null
            && newFloatValues == null)) {
         boolean valueChanged = !oldData.obj.equals(newData.obj);
         if (newFloatValues != null) {
            newData.changed = new ArrayList<>(newFloatValues.size());
            newData.isNew = new ArrayList<>(newFloatValues.size());
            for (int i = 0; i < newFloatValues.size(); i++) {
               boolean changed = !valueChanged && (i >= oldData.floatValues.size()
                     || !oldData.floatValues.get(i).equals(newFloatValues.get(i)));
               newData.changed.add(Boolean.valueOf(changed));
               boolean isNew = valueChanged || i >= oldData.floatValues.size();
               newData.isNew.add(Boolean.valueOf(isNew));
            }
         }
         else if (newValues != null /* Test is superfluous. */) {
            newData.changed = new ArrayList<>(newValues.size());
            newData.isNew = new ArrayList<>(newValues.size());
            for (int i = 0; i < newValues.size(); i++) {
               boolean changed = !valueChanged && (i >= oldData.values.size()
                     || !oldData.values.get(i).equals(newValues.get(i)));
               newData.changed.add(Boolean.valueOf(changed));
               boolean isNew = valueChanged || i >= oldData.values.size();
               newData.isNew.add(Boolean.valueOf(isNew));
            }
         }
      }
   
      newData.indexes = IndexItem.evalIndexExpressions(valueData.getIndexExpressions(),
            oldData == null ? null : oldData.indexes, context);
   
      stateData = newData;
   }
   
   
   /** Called before the painting of the bars starts. The default implementation does nothing. **/
   public void startBarPaint() {
   }
   
   
   /** Called during the painting of the bars, when a changed value is encountered. The default implementation does
    * nothing.
    *
    * @param index the index of the changed bar.
    *
    * @param size the number of elements in the graph.
    *
    * @param barHeight the bar height, which may be negative.
    *
    * @param barMin the minimum bar height.
    *
    * @param barMax the maximum bar height. **/
   public void changedValue(final int index, final int size, final int barHeight, final int barMin,
         final int barMax) {
   }
   
   
   /** Called after the painting of the bars starts. The default implementation does nothing. **/
   public void endBarPaint() {
   }
}
