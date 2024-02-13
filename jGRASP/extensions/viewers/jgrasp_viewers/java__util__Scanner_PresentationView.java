package jgrasp_viewers;


import java.util.ArrayList;
import java.util.List;
import jgrasp.viewer.IndexItem;
import jgrasp.viewer.ViewerCreateData;
import jgrasp.viewer.ViewerException;
import jgrasp.viewer.ViewerInfo;
import jgrasp.viewer.ViewerPriorityData;
import jgrasp.viewer.jgrdi.DebugContext;
import jgrasp.viewer.jgrdi.Value;
import jgrasp.viewer.presentation.PresentationListView;


/** Presentation viewer for scanners. **/
public class java__util__Scanner_PresentationView extends PresentationListView {

   /** The per-class settings. **/
   private static Settings perClassSettings;


   /** Creates a new scanner viewer.
    *
    * @param vcd creation data. **/
   public java__util__Scanner_PresentationView(final ViewerCreateData vcd) {
      super(vcd, 0);
   }


   /** {@inheritDoc} **/
   @Override
   public int getAutoscrollIndex(final Value value, final DebugContext context) throws ViewerException {
      return value.getFieldValue(context, "position").toInt(context);
   }


   /** {@inheritDoc} **/
   @Override
   public List<ListElementData> getElements(final Value value, final int offset, final int len,
         final DebugContext context) throws ViewerException {
      Value buf = value.getFieldValue(context, "buf");
      if (buf == null) {
         return null;
      }
      Value hb = buf.getFieldValue(context, "hb");
      if (hb == null) {
         return null;
      }
      int offs = buf.getFieldValue(context, "offset").toInt(context);
      int limit = buf.getFieldValue(context, "limit").toInt(context);
      
      List<Value> values = hb.getArrayElements(context, offset, len);
      List<ListElementData> result = new ArrayList<>();
      for (int i = 0; i < len; i++) {
         result.add(new ListElementData(values.get(i), null, offset + i >= offs && offset + i < limit));
      }
      return result;
   }


   /** {@inheritDoc} **/
   @Override
   public int getFullLimit() {
      return 500;
   }


   /** {@inheritDoc} **/
   @Override
   public void getInfo(final ViewerInfo vi) {
      vi.setShortDescription("Presentation viewer for Scanners");
      vi.setLongDescription("This viewer displays the contents of the Scanner's buffer.");
   }


   /** {@inheritDoc} **/
   @Override
   public String getLeftIndexMarker() {
      return "[";
   }


   /** {@inheritDoc} **/
   @Override
   public int getLength(final Value value, final DebugContext context) throws ViewerException {
      Value buf = value.getFieldValue(context, "buf");
      if (buf == null) {
         return 0;
      }
      Value hb = buf.getFieldValue(context, "hb");
      if (hb == null) {
         return 0;
      }
      return hb.getArrayLength(context);
   }


   /** {@inheritDoc} **/
   @Override
   public List<IndexItem> getMarkers(final Value value, final DebugContext context) throws ViewerException {
      List<IndexItem> result = new ArrayList<>();
      int pos = value.getFieldValue(context, "position").toInt(context);
      // Hash prevents conflict with other indexes.
      result.add(new IndexItem("#position", "position", pos, false));
      return result;
   }


   /** {@inheritDoc} **/
   @Override
   public Settings getPerClassSettings() {
      if (perClassSettings == null) {
         perClassSettings = createGlobalSettings();
      }
      return perClassSettings;
   }


   /** {@inheritDoc} **/
   @Override
   public int getPriority(final ViewerPriorityData vpd) {
      return 1000;
   }


   /** {@inheritDoc} **/
   @Override
   public String getRightIndexMarker() {
      return "]";
   }


   /** {@inheritDoc} **/
   @Override
   public String getViewName() {
      return "Presentation";
   }


   /** {@inheritDoc} **/
   @Override
   public boolean isObjectList(final Value value, final DebugContext context) throws ViewerException {
      return false;
   }
}
