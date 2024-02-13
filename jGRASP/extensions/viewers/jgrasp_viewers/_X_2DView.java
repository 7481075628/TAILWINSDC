package jgrasp_viewers;


import java.util.List;
import jgrasp.PluginOptOut;
import jgrasp.viewer.ViewerCreateData;
import jgrasp.viewer.ViewerException;
import jgrasp.viewer.ViewerInfo;
import jgrasp.viewer.ViewerPriorityData;
import jgrasp.viewer.ViewerUpdateData;
import jgrasp.viewer.ViewerValueData;
import jgrasp.viewer.jgrdi.DebugContext;
import jgrasp.viewer.jgrdi.Type;
import jgrasp.viewer.jgrdi.Value;
import jgrasp.viewer.text.StringTableViewWSV;


/** An Object viewer that displays the elements as a 2D table of strings. **/
public class _X_2DView extends StringTableViewWSV {

   /** Creates a 2D view.
    *
    * @param vcd viewer creation data. **/
   public _X_2DView(final ViewerCreateData vcd) {
      super(vcd, false);
      Value v = vcd.getValue();
      DebugContext dc = vcd.getDebugContext();
      if (v.isNull()) {
         throw new PluginOptOut();
      }
      try {
         if (v.getType(dc).getName(dc).endsWith("[][]") || (ArrayOrListUtil.getLength(v, dc) > 0
               && ArrayOrListUtil.isArrayOrList(ArrayOrListUtil.getValue(v, 0, dc), dc))) {
            return;
         }
      }
      catch (ViewerException ignored) {
      }
      throw new PluginOptOut();
   }


   /** {@inheritDoc} **/
   @Override
   public void getInfo(final ViewerInfo vi) {
      vi.setShortDescription("2D \"toString()\" viewer");
      vi.setLongDescription("This viewer displays the toString() value for array and list elements in a 2D grid of "
            + " cells. Selecting a cell will cause its value to be displayed in a subviewer.");
   }


   /** {@inheritDoc} **/
   @Override
   public int getPriority(final ViewerPriorityData vpd) {
      Value value = vpd.getValue();
      if (value == null) {
         return Integer.MIN_VALUE;
      }
      DebugContext dc = vpd.getDebugContext();
      if (value.getType(dc).getName(dc).endsWith("[][]")) {
         return 202;
      }
      try {
         if (ArrayOrListUtil.getLength(value, dc) > 0
               && ArrayOrListUtil.isArrayOrList(ArrayOrListUtil.getValue(value, 0, dc), dc)) {
            return 9;
         }
      }
      catch (ViewerException ignored) {
      }
      return Integer.MIN_VALUE;
   }


   /** {@inheritDoc} **/
   @Override
   public String getSubviewerLabel(final int index, final String viewerLabel, final int rowIndex,
         final int colIndex) {
      return viewerLabel + " [" + rowIndex + "," + colIndex + "]";
   }


   /** {@inheritDoc} **/
   @Override
   public String getSubviewerTreeLabel(final int index, final String viewerLabel, final int rowIndex,
         final int colIndex) {
      return "[" + rowIndex + "," + colIndex + "]";
   }


   /** {@inheritDoc} **/
   @Override
   public String getViewName() {
      return "2D Elements";
   }


   /** {@inheritDoc} **/
   @Override
   public void update(final ViewerValueData valueData, final ViewerUpdateData data, final DebugContext context,
         final int rowOffset, final int colOffset, final int numRowsShown, final int numColsShown,
         final int selectedRow, final int selectedCol, final String[][] textOut, final Value[][] valuesOut,
         final Value[] selectedValuesOut, final String[] selectedExpressionsOut, final int[] rowsOut,
         final int[] colsOut, final String[] errorOut) throws ViewerException {
   
      Value value = valueData.getValue();
      int rows = ArrayOrListUtil.getLength(value, context);
      rowsOut[0] = rows;
   
      int longestCol = 0;
      boolean gotSelection = false;
      List<Value> rowValues = ArrayOrListUtil.getValues(value, rowOffset, Math.min(numRowsShown, rows - rowOffset),
            context);
      for (int i = 0; i < numRowsShown; i++) {
         if (i + rowOffset < rows) {
            Value row = (rowValues == null) ? null : rowValues.get(i);
            int cols = 0;
            if (row != null && !row.isNull()) {
               cols = ArrayOrListUtil.getLength(row, context);
               if (cols == -1) {
                  if (1 > longestCol) {
                     longestCol = 1;
                  }
                  valuesOut[i][0] = null;
                  textOut[i][0] = "<not an array or List>";
                  continue;
               }
            }
            if (cols > longestCol) {
               longestCol = cols;
            }
            List<Value> colValues = (row == null || row.isNull() || cols - colOffset <= 0)? null
                  : ArrayOrListUtil.getValues(row, colOffset, Math.min(numColsShown, cols - colOffset), context);
            for (int j = 0; j < numColsShown; j++) {
               if (colValues != null && j + colOffset < cols) {
                  Value element = colValues.get(j);
                  valuesOut[i][j] = element;
                  if (i == selectedRow && j == selectedCol) {
                     selectedValuesOut[0] = element;
                     selectedExpressionsOut[0] = getExpr(valueData, i + rowOffset, j + colOffset, context);
                     gotSelection = true;
                  }
                  String str = (element == null) ? "" : element.toString(context);
                  if (str.length() > 100) {
                     str = str.substring(0, 96) + " ...";
                  }
                  textOut[i][j] = str;
               }
               else {
                  valuesOut[i][j] = null;
                  textOut[i][j] = null;
               }
            }
         }
         else {
            for (int j = 0; j < numColsShown; j++) {
               valuesOut[i][j] = null;
               textOut[i][j] = null;
            }
         }
      }
      colsOut[0] = longestCol;
   
      if (selectedRow >= 0 && selectedCol >= 0 && selectedRow < rows && !gotSelection) {
         Value row = ArrayOrListUtil.getValue(value, selectedRow, context);
         int cols = 0;
         if (row != null && !row.isNull()) {
            cols = ArrayOrListUtil.getLength(row, context);
         }
         if (selectedCol < cols) {
            Value element = ArrayOrListUtil.getValue(row, selectedCol, context);
            selectedValuesOut[0] = element;
            selectedExpressionsOut[0] = getExpr(valueData, selectedRow, selectedCol, context);
         }
      }
   }


   /** Gets the expression for a value at a specified row and column.
    *
    * @param valueData the value update data.
    *
    * @param row the row of interest.
    *
    * @param col the column of interest.
    *
    * @return an expression for the value at the requested position, or null if there is no expression for the current
    * value. **/
   private String getExpr(final ViewerValueData valueData, final int row, final int col, final DebugContext context) {
      String expr = getVIData().getExpression();
      if (expr == null) {
         return null;
      }
      Type dt = valueData.getDeclaredType();
      // Declared double array.
      if (dt.getName(context).endsWith("[][]")) {
         if (getVIData().getNeedParen()) {
            return "(" + expr + ")[" + row + "][" + col + "]";
         }
         return expr + "[" + row + "][" + col + "]";
      }
      Value value = valueData.getValue();
      String cast = value.getType(context).getName(context);
      String colResult;
      
       // Declared array.
      if (dt.getName(context).endsWith("[]")) {
         colResult = getVIData().getNeedParen() ? "(" + expr + ")[" + row + "]" : expr + "[" + row + "]";
      }
      else if (value.isArray()) {
         colResult = "((" + (cast.indexOf('.') >= 0 ? "`cast`" : "") + cast + ") " + expr + ")[" + row + "]";
      }
      else if (dt.isInstanceOf(context, "java.util.List")) {
         colResult = getVIData().getNeedParen() ? "(" + expr + ").get(" + row + ")" : expr + ".get(" + row + ")";
      }
      else {
         colResult = "((" + (cast.indexOf('.') >= 0 ? "`cast`" : "") + cast + ") " + expr + ").get(" + row + ")";
      }
      
      try {
         if (dt.isArray(context)) {
            Type elementType = dt.getArrayElementType(context);
            if (elementType.getName(context).endsWith("[]")) {
               return colResult + "[" + col + "]";
            }
            else if (elementType.isInstanceOf(context, "java.util.List")) {
               return colResult + ".get(" + col + ")";
            }
         }
         Value rowVal = ArrayOrListUtil.getValue(value, row, context);
         if (rowVal == null) {
            return null;
         }
         String rowCast = rowVal.getType(context).getName(context);
         String start = "((" + (rowCast.indexOf('.') >= 0 ? "`cast`" : "") + rowCast + ") " + colResult + ")";
         return start + (rowVal.isArray()? "[" + col + "]" : ".get(" + col + ")");
      }
      catch (ViewerException e) {
         return null;
      }
   }
}
