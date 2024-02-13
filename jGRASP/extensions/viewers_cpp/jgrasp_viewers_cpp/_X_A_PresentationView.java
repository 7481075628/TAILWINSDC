package jgrasp_viewers_cpp;


import java.util.ArrayList;
import java.util.List;
import jgrasp.viewer.ViewerCreateData;
import jgrasp.viewer.ViewerException;
import jgrasp.viewer.ViewerInfo;
import jgrasp.viewer.jgrdi.DebugContext;
import jgrasp.viewer.jgrdi.Value;
import jgrasp.viewer.jgrdi.ValueIsNotAnArrayException;
import jgrasp.viewer.presentation.PresentationListView;


/** Presentation viewer for arrays. **/
public class _X_A_PresentationView extends PresentationListView {

   /** The per-class settings. **/
   private static Settings perClassSettings;


   /** Creates a new presentation array viewer.
    *
    * @param vcd creation data. **/
   public _X_A_PresentationView(final ViewerCreateData vcd) {
      super(vcd, 0);
   }


   /** {@inheritDoc} **/
   @Override
   public List<ListElementData> getElements(final Value value, final int offset, final int len,
         final DebugContext context) throws ViewerException {
      List<ListElementData> result = new ArrayList<>();
      for (Value v : value.getArrayElements(context, offset, len)) {
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
      vi.setShortDescription("Presentation viewer for arrays");
      vi.setLongDescription("This viewer displays the string value for array elements in a graphical array "
            + "representation. Selecting an element will cause its value to be displayed in a subviewer.");
   }


   /** {@inheritDoc} **/
   @Override
   public String getLeftIndexMarker() {
      return "[";
   }


   /** {@inheritDoc} **/
   @Override
   public int getLength(final Value value, final DebugContext context) throws ValueIsNotAnArrayException {
      return value.getArrayLength(context);
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
      return "]";
   }


   /** {@inheritDoc} **/
   @Override
   public boolean isObjectList(final Value value, final DebugContext context) throws ValueIsNotAnArrayException {
      return value.getArrayComponentType(context).isObject(context);
   }
}
