/* 2.0.6/2.0.7 Unified. */
package jgrasp_viewers;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.EnumSet;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import jgrasp.viewer.ViewerCreateData;
import jgrasp.viewer.ViewerException;
import jgrasp.viewer.ViewerInfo;
import jgrasp.viewer.ViewerPriorityData;
import jgrasp.viewer.ViewerRoot;
import jgrasp.viewer.ViewerUpdateData;
import jgrasp.viewer.ViewerValueData;
import jgrasp.viewer.jgrdi.DebugContext;
import jgrasp.viewer.jgrdi.Method;
import jgrasp.viewer.jgrdi.Value;


/** A viewer that displays a java.awt.Color with a color swatch and RGB components. **/
public class java__awt__Color_ColorView extends ViewerRoot {

   /** The color swatch panel. **/
   private JPanel colorPanel;
   
   /** Text display components. **/
   private final JLabel[] colorText = new JLabel[7];
   
   /** Text labels. **/
   private static final String[] labels = { " r: ", " g: ", " b: ", " a: ", " h: ", " s: ", " b: " };
   
   /** Size reference component. **/
   private JLabel ref;

   /** The current color RGB value. **/
   private int currentRGB;


   /** Creates a new color viewer.
    *
    * @param vcd viewer creation data. **/
   public java__awt__Color_ColorView(final ViewerCreateData vcd) {
      super(false, EnumSet.of(CreationFlags.STANDARD_BORDER));
   }


   /** {@inheritDoc} **/
   @Override
   public void buildGui(final JPanel mainPanel) {
      buildColorPanel();
      mainPanel.setLayout(new BorderLayout());
      mainPanel.add(colorPanel, "Center");
   }


   /** {@inheritDoc} **/
   @Override
   public void destroy() {
   }


   /** {@inheritDoc} **/
   @Override
   public void getInfo(final ViewerInfo vi) {
      vi.setShortDescription("Detail viewer for colors");
      vi.setLongDescription("This viewer displays the RGBA components of a color, and displays a square of the "
            + "color.");
   }


   /** {@inheritDoc} **/
   @Override
   public int getPriority(final ViewerPriorityData vpd) {
      return 10;
   }


   /** {@inheritDoc} **/
   @Override
   public String getViewName() {
      return "Color";
   }


   /** {@inheritDoc} **/
   @Override
   public void updateGui() {
      Color color = new Color(currentRGB);
      colorPanel.setBackground(color);
   
      int r = (currentRGB & 0xff0000) >> 16;
      colorText[0].setText(r + " ");
      int g = (currentRGB & 0xff00) >> 8;
      colorText[1].setText(g + " ");
      int b = currentRGB & 0xff;
      colorText[2].setText(b + " ");
      int a = currentRGB >>> 24;
      colorText[3].setText(a + " ");
      float[] vals = Color.RGBtoHSB(r, g, b, null);
      colorText[4].setText(stripZero(String.format("%.3g ", Double.valueOf(vals[0]))));
      colorText[5].setText(stripZero(String.format("%.3g ", Double.valueOf(vals[1]))));
      colorText[6].setText(stripZero(String.format("%.3g ", Double.valueOf(vals[2]))));
   }


   /** Strips leading zeros from floating point strings.
    *
    * @param s the floating point string.
    *
    * @return the input string with a leading zero, if present, stripped. **/
   public static String stripZero(final String s) {
      if (!s.startsWith("0.")) {
         return s;
      }
      return s.substring(1);
   }


   /** {@inheritDoc} **/
   @Override
   public void updateState(final ViewerValueData valueData, final ViewerUpdateData data, final DebugContext context)
         throws ViewerException {
      Value value = valueData.getValue();
      Method getRgbMethod = value.getMethod(context, "getRGB", "int", null);
      currentRGB = value.invokeMethod(context, getRgbMethod, null).toInt(context);
   }


   /** Builds the main display panel. **/
   private void buildColorPanel() {
      ref = new JLabel("0");
      colorPanel = 
         new JPanel(
            new GridLayout(2, 4) {
               @Override
               public void layoutContainer(final Container parent) {
                  int d = (ref.getPreferredSize().height + 3) / 4;
                  setHgap(d);
                  setVgap(d);
                  super.layoutContainer(parent);
               }
            }) {
            @Override
            public Insets getInsets() {
               int d = (ref.getPreferredSize().height + 3) / 4;
               return new Insets(d, d, d, d);
            }
            @Override
            public void updateUI() {
               ref.updateUI();
               super.updateUI();
            }
         };
      colorPanel.setOpaque(true);
      for (int i = 0; i < colorText.length; i++) {
         JLabel label = new JLabel(labels[i]);
         colorText[i] = 
            new JLabel() {
               @Override
               public Dimension getPreferredSize() {
                  String text = getText();
                  setText(" .000 ");
                  Dimension result = super.getPreferredSize();
                  setText(text);
                  return result;
               }
            };
         colorText[i].setHorizontalAlignment(SwingConstants.RIGHT);
         JPanel pair = new JPanel(new BorderLayout());
         pair.setBorder(BorderFactory.createEtchedBorder());
         pair.add(label, "West");
         pair.add(colorText[i], "Center");
         colorPanel.add(pair);
      }
   }
}
