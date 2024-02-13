/* 2.0.6/2.0.7 Unified. */
package jgrasp_viewers;


import jgrasp.viewer.ViewerCreateData;
import jgrasp.viewer.ViewerException;
import jgrasp.viewer.ViewerInfo;
import jgrasp.viewer.ViewerPriorityData;
import jgrasp.viewer.jgrdi.Constructor;
import jgrasp.viewer.jgrdi.DebugContext;
import jgrasp.viewer.jgrdi.Method;
import jgrasp.viewer.jgrdi.Type;
import jgrasp.viewer.jgrdi.Value;
import jgrasp.viewer.text.TextAreaView;


/** A viewer that displays the stack trace of a throwable. **/
public class java__lang__Throwable_ThrowableView extends TextAreaView {

   /** Creates a new throwable viewer.
    *
    * @param vcd creation data. **/
   public java__lang__Throwable_ThrowableView(final ViewerCreateData vcd) {
   }


   /** {@inheritDoc} **/
   @Override
   public String getDisplayText(final Value value, final DebugContext context) throws ViewerException {
      Type swType = context.getType("java.io.StringWriter");
      Constructor swConstructor = swType.getConstructor(context, null);
      Value sw = swType.createInstance(context, swConstructor, null);
   
      Type pwType = context.getType("java.io.PrintWriter");
      Constructor pwConstructor = pwType.getConstructor(context, new String[] { "java.io.Writer" });
      Value pw = pwType.createInstance(context, pwConstructor, new Value[] { sw });
   
      Method pstMethod = value.getMethod(context, "printStackTrace", "void", new String[] { "java.io.PrintWriter" });
      value.invokeMethod(context, pstMethod, new Value[] { pw });
   
      Method gbMethod = sw.getMethod(context, "getBuffer", "java.lang.StringBuffer", null);
      Value buffer = sw.invokeMethod(context, gbMethod, null);
   
      return buffer.toString(context);
   }


   /** {@inheritDoc} **/
   @Override
   public void getInfo(final ViewerInfo vi) {
      vi.setShortDescription("Stack trace viewer for Throwables");
      vi.setLongDescription("This viewer displays the stack trace for a Throwable, as it would be displayed by "
            + "printStackTrace().");
   }


   /** {@inheritDoc} **/
   @Override
   public int getPriority(final ViewerPriorityData vpd) {
      return 10;
   }


   /** {@inheritDoc} **/
   @Override
   public String getViewName() {
      return "Throwable";
   }
}
