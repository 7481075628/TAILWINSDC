package jgrasp_viewers;


import java.util.Collections;
import java.util.List;
import jgrasp.PluginOptOut;
import jgrasp.viewer.ViewerCreateData;
import jgrasp.viewer.ViewerException;
import jgrasp.viewer.ViewerInfo;
import jgrasp.viewer.ViewerPriorityData;
import jgrasp.viewer.ViewerUpdateData;
import jgrasp.viewer.ViewerValueData;
import jgrasp.viewer.jgrdi.DebugContext;
import jgrasp.viewer.jgrdi.Field;
import jgrasp.viewer.jgrdi.Method;
import jgrasp.viewer.jgrdi.Type;
import jgrasp.viewer.jgrdi.Value;


/** An Object viewer that displays the elements as a 2D image. **/
public class _X_ImageView extends ImageView {
   //*** Need to make this scroll when the max size is exceeded.


   /** Value getter methods. **/
   private static class Methods {
   
      /** Integer.intValue method. **/
      public Method intValue;
      
      public Field valueField;
   }


   //** Maximum width and height. **/
   private static final int MAX_SIZE = 1000;


   /** Creates a new image viewer for 2D arrays or lists.
    *
    * @param vcd viewer creation data. **/
   public _X_ImageView(final ViewerCreateData vcd) {
      super(vcd);
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
      vi.setShortDescription("2D image viewer");
      vi.setLongDescription("This viewer displays the value for 2D array and list elements as an image. Integer "
         + "values will be interpreted as RGB, other numbers as zero = black and non-zero = white, and other "
         + "objects as null = black and non-null = white.");
   }


   /** {@inheritDoc} **/
   @Override
   public int getPriority(final ViewerPriorityData vpd) {
      return -1;
   }


   /** {@inheritDoc} **/
   @Override
   public String getViewName() {
      return "2D Image";
   }


       /** {@inheritDoc} **/
   @Override
   public Data getData(final ViewerValueData valueData, final ViewerUpdateData data, final DebugContext context)
         throws ViewerException {
      Value value = valueData.getValue();
      int rows = Math.min(ArrayOrListUtil.getLength(value, context), MAX_SIZE);
   
      int longestCol = 0;
      List<Value> rowValues = ArrayOrListUtil.getValues(value, 0, rows, context);
      if (rowValues == null) {
         rowValues = Collections.emptyList();
      }

      for (Value row : rowValues) {
         int cols = (row == null || row.isNull())? 0 : ArrayOrListUtil.getLength(row, context);
         if (cols > longestCol) {
            longestCol = cols;
         }
      }
      
      int[] pixels = new int[rows * longestCol];
      int pixelInd = 0;
   
      Methods methods = new Methods();
      for (Value row : rowValues) {
         int cols = (row == null || row.isNull())? 0 : Math.min(ArrayOrListUtil.getLength(row, context), MAX_SIZE);
         if (cols > 0) {
            List<Value> values = ArrayOrListUtil.getValues(row, 0, cols, context);
            if (values == null) {
               values = Collections.emptyList();
            }
         
            if (row.isArray()) {
               Type t = row.getType(context).getArrayElementType(context);
               String tName = t.getName(context);
               if (tName.equals("int")) {
                  for (int j = 0; j < cols; j++) {
                     pixels[pixelInd++] = 0xff000000 | values.get(j).toInt(context);
                  }
               }
               else if (tName.equals("java.lang.Integer")) {
                  if (methods.valueField == null) {
                     methods.valueField = t.getField("value");
                  }
                  for (int j = 0; j < cols; j++) {
                     pixels[pixelInd++] = 0xff000000 | values.get(j).getFieldValue(context, "value").toInt(context);
                  }
               }
               else {
                  for (int j = 0; j < cols; j++) {
                     pixels[pixelInd++] = 0xff000000 | getRGB(values.get(j), methods, context);
                  }
               }
            }
            else {
               for (int j = 0; j < cols; j++) {
                  pixels[pixelInd++] = 0xff000000 | getRGB(values.get(j), methods, context);
               }
            }
         }
         // Leave zeros at end of shorter columns.
         pixelInd += longestCol - cols;
      }
      
      return new Data(longestCol + " x " + rows, null, pixels, longestCol, rows, true);
   }
   
   
   private static int getRGB(final Value v, final Methods methods, final DebugContext context) {
      Type t = v.getType(context);
      String tName = t.getName(context);
      boolean on = false;
      try {
         switch (tName) {
            case "int" :
               return v.toInt(context);
            case "long" :
               on = v.toLong(context) != 0;
               break;
            case "short" :
               on = v.toShort(context) != 0;
               break;
            case "char" :
               on = v.toChar(context) != 0;
               break;
            case "byte" :
               on = v.toByte(context) != 0;
               break;
            case "float" :
               on = v.toFloat(context) != 0.0f;
               break;
            case "double" :
               on = v.toDouble(context) != 0.0;
               break;
            case "boolean" :
               on = v.toBoolean(context);
               break;
            case "java.lang.Integer":
               if (methods.valueField == null) {
                  methods.valueField = t.getField("value");
               }
               return v.getFieldValue(context, "value").toInt(context);
            default:
               on = !v.isNull();
         }
      }
      catch (ViewerException ignored) {
      }
      return on? 0xffffff : 0;
   }
}
