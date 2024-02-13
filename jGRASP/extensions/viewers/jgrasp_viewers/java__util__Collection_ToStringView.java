package jgrasp_viewers;


import java.util.List;
import jgrasp.viewer.Util;
import jgrasp.viewer.ViewerCreateData;
import jgrasp.viewer.ViewerException;
import jgrasp.viewer.ViewerInfo;
import jgrasp.viewer.ViewerPriorityData;
import jgrasp.viewer.ViewerUpdateData;
import jgrasp.viewer.ViewerValueData;
import jgrasp.viewer.jgrdi.DebugContext;
import jgrasp.viewer.jgrdi.Method;
import jgrasp.viewer.jgrdi.Type;
import jgrasp.viewer.jgrdi.Value;
import jgrasp.viewer.text.StringListViewWSV;


/** A java.util.Collection viewer that displays the elements as a list of strings. **/
public class java__util__Collection_ToStringView extends StringListViewWSV {

   /** True if the most recently updated value is a java.util.List, false otherwise. **/
   private boolean isList;


   /** Creates a new string list viewer for collections.
    *
    * @param vcd viewer creation data. **/
   public java__util__Collection_ToStringView(final ViewerCreateData vcd) {
      super(vcd, false);
   }


   /** {@inheritDoc} **/
   @Override
   public void getInfo(final ViewerInfo vi) {
      vi.setShortDescription("collections \"toString()\" viewer");
      vi.setLongDescription("This viewer lists the toString() value of each collection element. Selecting an element "
            + "will cause it to be displayed in a subviewer.\n\n"
            + "Note that for identification purposes, indices are shown even for collections for which the elements "
            + "have no specific order.");
   }


   /** {@inheritDoc} **/
   @Override
   public int getPriority(final ViewerPriorityData vpd) {
      return 10;
   }


   /** {@inheritDoc} **/
   @Override
   public String getSubviewerLabel(final int index, final String viewerLabel, final int itemIndex) {
      return "Element " + (isList ? "<" : "(") + itemIndex + (isList ? ">" : ")");
   }


   /** {@inheritDoc} **/
   @Override
   public String getSubviewerTreeLabel(final int index, final String viewerLabel, final int itemIndex) {
      return (isList ? "<" : "(") + itemIndex + (isList ? ">" : ")");
   }


   /** {@inheritDoc} **/
   @Override
   public String getViewName() {
      return "Collection Elements";
   }


   /** {@inheritDoc} **/
   @Override
   public int update(final ViewerValueData valueData, final ViewerUpdateData data, final DebugContext context,
         final int viewOffset, final int numItemsShown, final int selected, final String[] textOut,
         final Value[] valuesOut, final Value[] selectedValuesOut, final String[] selectedExpressionsOut,
         final String[] errorOut) throws ViewerException {
      Value value = valueData.getValue();
      isList = value.isInstanceOf(context, "java.util.List");
   
      Method sizeMethod = value.getMethod(context, "size", "int", null);
      int size = value.invokeMethod(context, sizeMethod, null).toInt(context);
   
      if (!isList && size > 1500) {
         errorOut[0] = "Collection contains " + size + " elements.\n\n"
               + "For performance reasons, this view can display a maximum of 1500 elements.";
         return 0;
      }
   
      Value array = null;
      String lb;
      String rb;
      getVIData().setIndexable(isList);
      List<Value> values = null;
      if (isList) {
         values = (size - viewOffset <= 0) ? null
               : ArrayOrListUtil.getValues(value, viewOffset, Math.min(numItemsShown, size - viewOffset), context);
         lb = "<";
         rb = ">";
      }
      else {
         // For non-lists, we have no choice but to get all elements.
         if (size - viewOffset > 0) {
            Method toArrayMethod = value.getMethod(context, "toArray", "java.lang.Object[]", null);
            array = value.invokeMethod(context, toArrayMethod, null);
            values = array.getArrayElements(context, viewOffset, Math.min(numItemsShown, size - viewOffset));
         }
         lb = "(";
         rb = ")";
      }
   
      boolean gotSelection = false;
      for (int i = 0; i < numItemsShown; i++) {
         if (i + viewOffset < size && values != null) {
            Value element = values.get(i);
            valuesOut[i] = element;
            if (i + viewOffset == selected) {
               selectedValuesOut[0] = element;
               selectedExpressionsOut[0] = getExpr(valueData, i + viewOffset, context);
               gotSelection = true;
            }
            String str = element == null ? "<error>" : Util.encodeString(element.toString(context), true);
            if (str.length() > 100) {
               str = str.substring(0, 96) + " ...";
            }
            textOut[i] = lb + (i + viewOffset) + rb + " = " + str;
         }
         else {
            valuesOut[i] = null;
            textOut[i] = " ";
         }
      }
      if (selected >= 0 && selected < size && !gotSelection) {
         // Selection is outside visible range.
         Value element;
         if (isList) {
            element = ArrayOrListUtil.getValue(value, selected, context);
         }
         else {
            if (array == null) {
               Method toArrayMethod = value.getMethod(context, "toArray", "java.lang.Object[]", null);
               array = value.invokeMethod(context, toArrayMethod, null);
            }
            element = array.getArrayElement(context, selected);
         }
         selectedValuesOut[0] = element;
         selectedExpressionsOut[0] = getExpr(valueData, selected, context);
      }
   
      return size;
   }


   /** Gets the expression for a value at an index.
    *
    * @param valueData the value update data.
    *
    * @param index the index of interest.
    *
    * @return an expression for the value at the requested index, or null if there is not a compact expression for
    * it or if there is no expression for the current value. **/
   private String getExpr(final ViewerValueData valueData, final int index, final DebugContext context) {
      String expr = getVIData().getExpression();
      if (expr == null) {
         return null;
      }
      Value value = valueData.getValue();
      Type dt = valueData.getDeclaredType();
      if (value.isInstanceOf(context, "java.util.List")) {
         if (!dt.isInstanceOf(context, "java.util.List")) {
            return "((java.util.List) " + expr + ").get(" + index + ")";
         }
         if (getVIData().getNeedParen()) {
            return "(" + expr + ").get(" + index + ")";
         }
         return expr + ".get(" + index + ")";
      }
      if (value.isInstanceOf(context, "java.util.Vector")) {
         if (!dt.isInstanceOf(context, "java.util.Vector")) {
            return "((java.util.Vector) " + expr + ").elementAt(" + index + ")";
         }
         if (getVIData().getNeedParen()) {
            return "(" + expr + ").elementAt(" + index + ")";
         }
         return expr + ".elementAt(" + index + ")";
      }
      return null;
   }
}
