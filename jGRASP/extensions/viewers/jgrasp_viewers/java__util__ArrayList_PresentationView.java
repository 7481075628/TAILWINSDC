package jgrasp_viewers;


import java.util.ArrayList;
import java.util.List;
import jgrasp.viewer.ViewerCreateData;
import jgrasp.viewer.ViewerException;
import jgrasp.viewer.ViewerInfo;
import jgrasp.viewer.jgrdi.DebugContext;
import jgrasp.viewer.jgrdi.Value;
import jgrasp.viewer.presentation.PresentationListView;


/** Presentation viewer for java.util.ArrayList. **/
public class java__util__ArrayList_PresentationView extends PresentationListView {

   /** The per-class settings. **/
   private static Settings perClassSettings;


   /** Creates a new presentation ArrayList viewer.
    *
    * @param vcd creation data. **/
   public java__util__ArrayList_PresentationView(final ViewerCreateData vcd) {
      super(vcd, 0);
   }


   /** {@inheritDoc} **/
   @Override
   public List<ListElementData> getElements(final Value value, final int offset, final int len,
         final DebugContext context) throws ViewerException {
      Value array = value.getFieldValue(context, "elementData");
      List<ListElementData> result = new ArrayList<>();
      for (Value v : array.getArrayElements(context, offset, len)) {
         result.add(new ListElementData(v, null, true));
      }
      return result;
   }


   /** {@inheritDoc} **/
   @Override
   public int getFullLimit() {
      return 50;
   }


   /** {@inheritDoc} **/
   @Override
   public void getInfo(final ViewerInfo vi) {
      vi.setShortDescription("Presentation viewer for ArrayLists");
      vi.setLongDescription("This viewer displays the graphical structure of an ArrayList. The toString() values of "
            + "the elements are displayed in the nodes. Selecting an element will cause its value to be displayed in "
            + "a subviewer.");
   }


   /** {@inheritDoc} **/
   @Override
   public String getLeftIndexMarker() {
      return "<";
   }


   /** {@inheritDoc} **/
   @Override
   public int getLength(final Value value, final DebugContext context) throws ViewerException {
      return value.getFieldValue(context, "size").toInt(context);
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
   public String getRightIndexMarker() {
      return ">";
   }


   /** {@inheritDoc} **/
   @Override
   public boolean isObjectList(final Value value, final DebugContext context) {
      return true;
   }
}
