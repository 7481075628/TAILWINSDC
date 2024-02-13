package jgrasp_viewers;


import java.util.ArrayList;
import java.util.List;
import jgrasp.viewer.ViewerCreateData;
import jgrasp.viewer.ViewerException;
import jgrasp.viewer.ViewerInfo;
import jgrasp.viewer.ViewerPriorityData;
import jgrasp.viewer.jgrdi.DebugContext;
import jgrasp.viewer.jgrdi.JgrdiNoSuchFieldException;
import jgrasp.viewer.jgrdi.Value;
import jgrasp.viewer.presentation.PresentationListView;


/** Presentation viewer for Strings. **/
public class java__lang__String_CharArrayView extends PresentationListView {

   /** The per-class settings. **/
   private static Settings perClassSettings;


   /** Creates a new presentation String viewer.
    *
    * @param vcd creation data. **/
   public java__lang__String_CharArrayView(final ViewerCreateData vcd) {
      super(vcd, 0);
   }


   /** {@inheritDoc} **/
   @Override
   public List<ListElementData> getElements(final Value value, final int offset, final int len,
         final DebugContext context) throws ViewerException {
      Value valArray = value.getFieldValue(context, "value");
      int strOffset;
      try {
         Value offsetV = value.getFieldValue(context, "offset");
         strOffset = offsetV.toInt(context);
      }
      catch (JgrdiNoSuchFieldException e) {
         strOffset = 0;
      }
      List<ListElementData> result = new ArrayList<>();
      if (context.getTargetMajorVersionNumber() <= 8) {
         // Value is chars.
         for (Value v : valArray.getArrayElements(context, offset + strOffset, len)) {
            result.add(new ListElementData(v, null, true));
         }
         return result;
      }
      Value coder = value.getFieldValue(context, "coder");
      if (coder.toInt(context) == 0) {
         List<Value> values = valArray.getArrayElements(context, offset + strOffset, len);
         for (Value v : valArray.getArrayElements(context, offset + strOffset, len)) {
            result.add(new ListElementData(context.createPrimitiveValue("char", String.valueOf(v.toChar(context))),
                  null, true));
         }
         return result;
      }
   
      List<Value> values = valArray.getArrayElements(context, offset + strOffset, len * 2);
      for (int i = 0; i < len * 2; i += 2) {
         byte low = values.get(i).toByte(context);
         byte high = values.get(i + 1).toByte(context);
         result.add(new ListElementData(context.createPrimitiveValue("char",
               String.valueOf((char) ((high << 8) + (low & 0xff)))), null, true));
      }
      return result;
   }


   /** {@inheritDoc} **/
   @Override
   public int getFullLimit() {
      return 500;
   }


   /** {@inheritDoc} **/
   @Override
   public void getInfo(final ViewerInfo vi) {
      vi.setShortDescription("Character array viewer for Strings");
      vi.setLongDescription("This viewer displays a String as an array of characters. Note that this is not "
            + "necessarily the same as the character array that the String uses to hold its characters (which may be "
            + "a larger array, where the Strings offset and length limit the range).");
   }


   /** {@inheritDoc} **/
   @Override
   public String getLeftIndexMarker() {
      return "[";
   }


   /** {@inheritDoc} **/
   @Override
   public int getLength(final Value value, final DebugContext context) throws ViewerException {
      try {
         return value.getFieldValue(context, "count").toInt(context);
      }
      catch (JgrdiNoSuchFieldException e) {
         Value valArray = value.getFieldValue(context, "value");
         int result = valArray.getArrayLength(context);
         if (context.getTargetMajorVersionNumber() <= 8) {
            return result;
         }
         Value coder = value.getFieldValue(context, "coder");
         return coder.toInt(context) == 0 ? result : result / 2;
      }
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
   public int getPriority(final ViewerPriorityData vpd) {
      return 2;
   }


   /** {@inheritDoc} **/
   @Override
   public String getRightIndexMarker() {
      return "]";
   }


   /** {@inheritDoc} **/
   @Override
   public String getViewName() {
      return "Presentation Char Array";
   }


   /** {@inheritDoc} **/
   @Override
   public boolean isObjectList(final Value value, final DebugContext context) throws ViewerException {
      return false;
   }
}
