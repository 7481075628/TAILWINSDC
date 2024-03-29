/* 2.0.6/2.0.7 Unified. */
package jgrasp_viewers;


import jgrasp.viewer.ViewerCreateData;
import jgrasp.viewer.ViewerException;
import jgrasp.viewer.ViewerInfo;
import jgrasp.viewer.ViewerPriorityData;
import jgrasp.viewer.jgrdi.DebugContext;
import jgrasp.viewer.jgrdi.Value;
import jgrasp.viewer.text.TextAreaView;


/** A viewer that displays the text of a java.lang.StringBuilder. **/
public class java__lang__StringBuilder_FormattedView extends TextAreaView {

   /** Creates a new StringBuilder formatted text viewer.
    *
    * @param vcd creation data. **/
   public java__lang__StringBuilder_FormattedView(final ViewerCreateData vcd) {
   }


   /** {@inheritDoc} **/
   @Override
   public String getDisplayText(final Value value, final DebugContext context) throws ViewerException {
      return value.toString(context);
   }


   /** {@inheritDoc} **/
   @Override
   public void getInfo(final ViewerInfo vi) {
      vi.setShortDescription("Formatted StringBuilder viewer");
      vi.setLongDescription("This viewer displays a the String value of a StringBuilder as it would be displayed in "
            + "a text window.");
   }


   /** {@inheritDoc} **/
   @Override
   public int getPriority(final ViewerPriorityData vpd) {
      return 10;
   }


   /** {@inheritDoc} **/
   @Override
   public String getViewName() {
      return "Formatted";
   }
}
