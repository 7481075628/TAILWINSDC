package jgrasp_viewers;


import java.util.List;
import jgrasp.viewer.Util;
import jgrasp.viewer.ViewerCreateData;
import jgrasp.viewer.ViewerException;
import jgrasp.viewer.ViewerInfo;
import jgrasp.viewer.ViewerInitData;
import jgrasp.viewer.ViewerPriorityData;
import jgrasp.viewer.ViewerUpdateData;
import jgrasp.viewer.ViewerValueData;
import jgrasp.viewer.jgrdi.DebugContext;
import jgrasp.viewer.jgrdi.Type;
import jgrasp.viewer.jgrdi.Value;
import jgrasp.viewer.text.StringListViewWSV;
import org.w3c.dom.Element;


/** An array viewer that displays the elements as a list of strings. **/
public class _X_A_ToStringView extends StringListViewWSV {

   /** Creates a new string list viewer for arrays.
    *
    * @param vcd viewer creation data. **/
   public _X_A_ToStringView(final ViewerCreateData vcd) {
      super(vcd, false);
   }


   /** {@inheritDoc} **/
   @Override
   public void build(final ViewerInitData vid, final Element initDataIn) {
      super.build(vid, initDataIn);
      vid.setIndexable(true);
   }


   /** {@inheritDoc} **/
   @Override
   public void getInfo(final ViewerInfo vi) {
      vi.setShortDescription("Array \"toString()\" viewer");
      vi.setLongDescription("This viewer displays the toString() value \for array elements. Selecting an element "
            + "will cause its value to be displayed in a subviewer.");
   }


   /** {@inheritDoc} **/
   @Override
   public int getPriority(final ViewerPriorityData vpd) {
      return 10;
   }


   /** {@inheritDoc} **/
   @Override
   public String getSubviewerLabel(final int index, final String viewerLabel, final int itemIndex) {
      return viewerLabel + " [" + itemIndex + "]";
   }


   /** {@inheritDoc} **/
   @Override
   public String getSubviewerTreeLabel(final int index, final String viewerLabel, final int itemIndex) {
      return "[" + itemIndex + "]";
   }


   /** {@inheritDoc} **/
   @Override
   public String getViewName() {
      return "Array Elements";
   }


   /** {@inheritDoc} **/
   @Override
   public int update(final ViewerValueData valueData, final ViewerUpdateData data, final DebugContext context,
         final int viewOffset, final int numItemsShown, final int selected, final String[] textOut,
         final Value[] valuesOut, final Value[] selectedValuesOut, final String[] selectedExpressionsOut,
         final String[] errorOut) throws ViewerException {
   
      Value value = valueData.getValue();
      int len = value.getArrayLength(context);
   
      boolean gotSelection = false;
      List<Value> values = value.getArrayElements(context, viewOffset, Math.min(numItemsShown, len - viewOffset));
      for (int i = 0; i < numItemsShown; i++) {
         if (i + viewOffset < len) {
            Value element = values.get(i);
            valuesOut[i] = element;
            if (i + viewOffset == selected && selectedValuesOut.length > 0) {
               selectedValuesOut[0] = element;
               selectedExpressionsOut[0] = getExpr(valueData, selected, context);
               gotSelection = true;
            }
            String str = Util.encodeString(element.toString(context), true);
            if (str.length() > 100) {
               str = str.substring(0, 96) + " ...";
            }
            textOut[i] = "[" + (i + viewOffset) + "] = " + str;
         }
         else {
            valuesOut[i] = null;
            textOut[i] = " ";
         }
      }
      if (selected >= 0 && selected < len && !gotSelection && selectedValuesOut.length > 0) {
         Value element = value.getArrayElement(context, selected);
         selectedValuesOut[0] = element;
         selectedExpressionsOut[0] = getExpr(valueData, selected, context);
      }
   
      return len;
   }


   /** Gets the expression for a value at a specified index.
    *
    * @param valueData the value update data.
    *
    * @param index the index of interest.
    *
    * @return an expression for the value at the requested index, or null if there is no expression for the current
    * value. **/
   private String getExpr(final ViewerValueData valueData, final int index, final DebugContext context) {
      String expr = getVIData().getExpression();
      if (expr == null) {
         return null;
      }
      Type dt = valueData.getDeclaredType();
      if (dt.getName(context).endsWith("[]")) {
         if (getVIData().getNeedParen()) {
            return "(" + expr + ")[" + index + "]";
         }
         return expr + "[" + index + "]";
      }
      Value value = valueData.getValue();
      String cast = value.getType(context).getName(context);
      return "((" + (cast.indexOf('.') >= 0 ? "`cast`" : "") + cast + ") " + expr + ")[" + index + "]";
   }

}
