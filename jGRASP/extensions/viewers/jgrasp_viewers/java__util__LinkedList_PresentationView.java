package jgrasp_viewers;


import java.util.ArrayList;
import java.util.List;
import jgrasp.viewer.ViewerCreateData;
import jgrasp.viewer.ViewerException;
import jgrasp.viewer.ViewerInfo;
import jgrasp.viewer.jgrdi.DebugContext;
import jgrasp.viewer.jgrdi.Method;
import jgrasp.viewer.jgrdi.NoSuchTypeException;
import jgrasp.viewer.jgrdi.Value;
import jgrasp.viewer.presentation.PresentationListView;


/** Presentation viewer for java.util.LinkedList. **/
public class java__util__LinkedList_PresentationView extends PresentationListView {

   /** The per-class settings. **/
   private static Settings perClassSettings;

   /** True if the object being viewed is a Java 1.7 LinkedList, false if it is from an earlier version of Java. **/
   private boolean v17;


   /** Creates a new presentation LinkedList viewer.
    *
    * @param vcd creation data. **/
   public java__util__LinkedList_PresentationView(final ViewerCreateData vcd) {
      super(vcd, LINKED | BACK_LINKS | HEADER | END_LINK_TO_START | START_LINK_TO_END);
      try {
         vcd.getDebugContext().getType("java.util.LinkedList$Node");
         v17 = true;
         setLinkage(false, false, false);
      }
      catch (NoSuchTypeException ignored) {
      }
   }


   /** {@inheritDoc} **/
   @Override
   public List<ListElementData> getElements(final Value value, final int offset, final int len,
         final DebugContext context) throws ViewerException {
         
      List<ListElementData> result = new ArrayList<>();
      
      Value entry;
      if (offset == 0) {
         entry = value.getFieldValue(context, v17? "first" : "header");
      }
      else {
         entry = getEntry(value, offset, context);
      }
   
      for (int i = 0; i < len; i++) {
         Value element = entry.getFieldValue(context, v17? "item" : "element");
         result.add(new ListElementData(element, entry, true));
         entry = entry.getFieldValue(context, "next");
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
      vi.setShortDescription("Presentation viewer for LinkedLists");
      vi.setLongDescription("This viewer displays the internal structure of a LinkedList. Selecting an entry or "
            + "value will cause it to be displayed in a subviewer.\n\n"
            + "A correct structure is assumed. Stepping into LinkedList methods may cause the viewer to temporarily "
            + "fail.");
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


   /** Gets the list node ("entry" in java.util.LinkedList terminology) at a specified index.
    *
    * @param value the linked list value.
    *
    * @param index index of the node of interest.
    *
    * @param context the current debugger context.
    *
    * @return the requested node.
    *
    * @throws ViewerException if an error occurs while retrieving the node. **/
   private Value getEntry(final Value value, final int index, final DebugContext context) throws ViewerException {
      String methodName = v17? "node" : "entry";
      String returnType = v17? "java.util.LinkedList$Node" : "java.util.LinkedList$Entry";
      Method getMethod = value.getMethod(context, methodName, returnType, new String[] { "int" });
      return value.invokeMethod(context, getMethod, new Value[]
            { context.createPrimitiveValue("int", String.valueOf(index)) });
   }
}
