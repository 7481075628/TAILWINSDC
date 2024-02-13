/* 2.0.6/2.0.7 Unified. */
package jgrasp_viewers;


import java.util.ArrayList;
import java.util.Comparator;
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
import jgrasp.viewer.text.StringTableViewWSV;


/** A java.util.Collection viewer that displays the entries as a key/value strings. **/
public class java__util__Map_ToStringView extends StringTableViewWSV {


   /** Map element holder. **/
   private static class El {
   
      /** The key string. **/
      public final String keyString;
      
      /** The key. **/
      public final Value key;
      
      /** The value. **/
      public final Value value;
      
      
      /** Creates a new El.
       *
       * @param keyStringIn the key string representation.
       *
       * @param keyIn the key.
       *
       * @param valueIn the value. **/
      public El(final String keyStringIn, final Value keyIn, final Value valueIn) {
         keyString = keyStringIn;
         key = keyIn;
         value = valueIn;
      }
   }


   /** Creates a new string list viewer for maps.
    *
    * @param vcd viewer creation data. **/
   public java__util__Map_ToStringView(final ViewerCreateData vcd) {
      super(vcd, true);
   }


   /** {@inheritDoc} **/
   @Override
   public void getInfo(final ViewerInfo vi) {
      vi.setShortDescription("Map \"toString()\" viewer");
      vi.setLongDescription("This viewer displays the toString() value for each key and value in the map. Selecting "
            + "an entry will cause the key and value for that entry to be displayed in a subviewer.\n\nNote that for "
            + "identification purposes, indices are assigned to the entries, although they have no specific order.");
   }


   /** {@inheritDoc} **/
   @Override
   public int getPriority(final ViewerPriorityData vpd) {
      return 10;
   }


   /** {@inheritDoc} **/
   @Override
   public String getSubviewerLabel(final int index, final String viewerLabel, final int rowIndex,
         final int colIndex) {
      return (colIndex == 0 ? "Key" : "Value") + " (" + colIndex + ")";
   }


   /** {@inheritDoc} **/
   @Override
   public String getSubviewerTreeLabel(final int index, final String viewerLabel, final int rowIndex,
         final int colIndex) {
      return (colIndex == 0 ? "key" : "value") + " (" + rowIndex + ")";
   }


   /** {@inheritDoc} **/
   @Override
   public String getViewName() {
      return "Key/Value";
   }


   /** {@inheritDoc} **/
   @Override
   public void update(final ViewerValueData valueData, final ViewerUpdateData data, final DebugContext context,
         final int rowOffset, final int colOffset, final int numRowsShown, final int numColsShown,
         final int selectedRow, final int selectedCol, final String[][] textOut,
         final Value[][] valuesOut, final Value[] selectedValuesOut, final String[] selectedExpressionsOut,
         final int[] rowsOut, final int[] colsOut, final String[] errorOut)
         throws ViewerException {
      Value value = valueData.getValue();
      Method sizeMethod = value.getMethod(context, "size", "int", null);
      int size = value.invokeMethod(context, sizeMethod, null).toInt(context);
      if (size > 1500) {
         errorOut[0] = "Map contains " + size + " entries.\n\n"
               + "For performance reasons, this view can display a maximum of 1500 entries.";
         rowsOut[0] = 0;
         colsOut[0] = 0;
         return;
      }
   
      Method entrySetMethod = value.getMethod(context, "entrySet", "java.util.Set", null);
      Value entrySet = value.invokeMethod(context, entrySetMethod, null);
   
      Method toArrayMethod = entrySet.getMethod(context, "toArray", "java.lang.Object[]", null);
      Value array = entrySet.invokeMethod(context, toArrayMethod, null);
   
      Method getKeyMethod = null;
      Method getValueMethod = null;
   
      List<Value> elements = array.getArrayElements(context);
      List<El> els = new ArrayList<>(elements.size());
      for (Value e : elements) {
         if (getKeyMethod == null) {
            getKeyMethod = e.getMethod(context, "getKey", "java.lang.Object", null);
            getValueMethod = e.getMethod(context, "getValue", "java.lang.Object", null);
         }
         Value keyVal = e.invokeMethod(context, getKeyMethod, null);
         Value valueVal = e.invokeMethod(context, getValueMethod, null);
         String keyString = keyVal.toString(context);
         els.add(new El(keyString, keyVal, valueVal));
      }
      els.sort(Comparator.comparing(e -> e.keyString));
   
      boolean gotSelection = false;
      for (int i = 0; i < numRowsShown; i++) {
         if (i + rowOffset < size && i + rowOffset < els.size()) {
            El el = els.get(i + rowOffset);
            Value keyVal = el.key;
            Value valueVal = el.value;
         
            if (i + rowOffset == selectedRow) {
               selectedValuesOut[0] = (selectedCol == 0)? keyVal : valueVal;
               gotSelection = true;
               
               if (selectedCol == 1) {
                  selectedExpressionsOut[0] = getExpr(valueData, keyVal, context);
               }
            }
            String keyStr = Util.encodeString(el.keyString, true);
            if (keyStr.length() > 25) {
               keyStr = keyStr.substring(0, 21) + " ...";
            }
            String valueStr = Util.encodeString(valueVal.toString(context), true);
            if (valueStr.length() > 25) {
               valueStr = valueStr.substring(0, 21) + " ...";
            }
            if (colOffset == 0) {
               textOut[i][0] = keyStr;
            }
            if (1 - colOffset < numColsShown) {
               textOut[i][1 - colOffset] = valueStr;
            }
         }
         else {
            textOut[i][0] = " ";
            if (numColsShown > 0) {
               textOut[i][1] = " ";
            }
         }
      }
      if (selectedRow >= 0 && selectedRow < size && !gotSelection) {
         El el = els.get(selectedRow);
         selectedValuesOut[0] = (selectedCol == 0)? el.key : el.value;
       
         if (selectedCol == 1) {
            selectedExpressionsOut[0] = getExpr(valueData, el.key, context);
         }
      }
   
      rowsOut[0] = size;
      colsOut[0] = 2;
   }


   /** Gets the expression for a value for a specified key.
    *
    * @param valueData the value update data.
    *
    * @param keyVal the key value of interest.
    *
    * @return an expression for the value at the requested index, or null if there is no expression for the current
    * value. **/
   private String getExpr(final ViewerValueData valueData, final Value keyVal, final DebugContext context)
         throws ViewerException {
      String expr = getVIData().getExpression();
      if (expr == null) {
         return null;
      }
      String valExpr = keyVal.getEquivalentExpression(context);
      if (valExpr == null) {
         return null;
      }
      Type dt = valueData.getDeclaredType();
      if (dt.isInstanceOf(context, "java.util.Map")) {
         return expr + ".get(" + valExpr + ")";
      }
      return "((java.util.Map) " + expr + ").get(" + valExpr + ")";
   }


   /** {@inheritDoc} **/
   @Override
   public String getColumnLabel(final int column) {
      return (column == 0)? "Key" : "Value";
   }


   /** {@inheritDoc} **/
   @Override
   public String getRowLabel(final int column) {
      return null;
   }
}
