/* 2.0.6/2.0.7 Unified. */
package jgrasp_viewers;


import jgrasp.viewer.ViewerCreateData;
import jgrasp.viewer.ViewerException;
import jgrasp.viewer.ViewerInfo;
import jgrasp.viewer.ViewerPriorityData;
import jgrasp.viewer.jgrdi.DebugContext;
import jgrasp.viewer.jgrdi.Value;
import jgrasp.viewer.presentation.PresentationNodeView;


/** A presentation "toString()" viewer for anything. **/
public class _X_PresentationToStringView extends PresentationNodeView {

   /** Creates a new toString() viewer.
    *
    * @param vcd creation data. **/
   public _X_PresentationToStringView(final ViewerCreateData vcd) {
   }


   /** {@inheritDoc} **/
   @Override
   public String getDisplayText(final Value value, final DebugContext context) throws ViewerException {
      return value.toString(context);
   }


   /** {@inheritDoc} **/
   @Override
   public void getInfo(final ViewerInfo vi) {
      vi.setShortDescription("toString() viewer");
      vi.setLongDescription("This viewer displays the toString() value of a value.");
   }


   /** {@inheritDoc} **/
   @Override
   public int getPriority(final ViewerPriorityData vpd) {
      return -20;
   }


   /** {@inheritDoc} **/
   @Override
   public String getViewName() {
      return "Presentation String";
   }

}
