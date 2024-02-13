package jgrasp_viewers;


import java.util.List;
import jgrasp.viewer.ViewerException;
import jgrasp.viewer.jgrdi.DebugContext;
import jgrasp.viewer.jgrdi.Method;
import jgrasp.viewer.jgrdi.Value;


/** Utility methods that tread arrays and lists the same. **/
public class ArrayOrListUtil {
   
   
   /** Determines if a value is an array or list.
    *
    * @param value the value of interest. This may be null.
    *
    * @param context the current debug context.
    *
    * @return true if the value is an array or list, false otherwise. **/
   public static boolean isArrayOrList(final Value value, final DebugContext context) {
      return (value != null) && (value.isArray() || value.isInstanceOf(context, "java.util.List"));
   }
   
   
   /** Gets the array or list length for a value.
    *
    * @param value the value of interest.
    *
    * @param context the current debug context.
    *
    * @return the array or list length, or -1 if the value is not an array or list.
    *
    * @throws ViewerException if a debugger-related exception occurs. **/
   public static int getLength(final Value value, final DebugContext context) throws ViewerException {
      if (value.isArray()) {
         return value.getArrayLength(context);
      }
      if (value.isInstanceOf(context, "java.util.List")) {
         Method sizeMethod = value.getMethod(context, "size", "int", null);
         return value.invokeMethod(context, sizeMethod, null).toInt(context);
      }
      return -1;
   }
   
   
   /** Gets array or list elements for a value.
    *
    * @param value the array or list value.
    *
    * @param index the start index.
    *
    * @param length the number of value to retrieve.
    *
    * @param context the current debug context.
    *
    * @return the array or list value at the specified index, or null if the value is not an array or list.
    *
    * @throws ViewerException if a debugger-related exception occurs. **/
   public static List<Value> getValues(final Value value, final int index, final int length,
         final DebugContext context) throws ViewerException {
      if (value.isArray()) {
         return value.getArrayElements(context, index, length);
      }
      try {
         if (value.isInstanceOf(context, "java.util.List")) {
            Method sublistMethod = value.getMethod(context, "subList:(II)Ljava/util/List;");
            Value sublist = value.invokeMethod(context, sublistMethod, new Value[]
                  { context.createPrimitiveValue("int", String.valueOf(index)),
                  context.createPrimitiveValue("int", String.valueOf(index + length)) });
         
            Method toArrayMethod = sublist.getMethod(context, "toArray:()[Ljava/lang/Object;");
            Value array = sublist.invokeMethod(context, toArrayMethod, null);
            return array.getArrayElements(context, 0, length);
         }
      }
      catch (ViewerException ignored) {
         return null;
      }
      return null;
   }
   
   
   /** Gets an array or list element for a value. Note that getting a range of values is much faster than getting
    * them one-by-one using this method.
    *
    * @param value the array or list value.
    *
    * @param index the index of interest.
    *
    * @param context the current debug context.
    *
    * @return the array or list value at the specified index, or null if the value is not an array or list.
    *
    * @throws ViewerException if a debugger-related exception occurs. **/
   public static Value getValue(final Value value, final int index, final DebugContext context)
         throws ViewerException {
      if (value.isArray()) {
         return value.getArrayElement(context, index);
      }
      if (value.isInstanceOf(context, "java.util.List")) {
         Method getMethod = value.getMethod(context, "get", "java.lang.Object", new String[] { "int" });
         return value.invokeMethod(context, getMethod, new Value[]
               { context.createPrimitiveValue("int", String.valueOf(index)) });
      }
      return null;
   }
}