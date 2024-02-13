package jgrasp_viewers;


import java.awt.image.BufferedImage;
import java.util.List;
import jgrasp.viewer.ViewerCreateData;
import jgrasp.viewer.ViewerException;
import jgrasp.viewer.ViewerValueData;
import jgrasp.viewer.ViewerUpdateData;
import jgrasp.viewer.jgrdi.Constructor;
import jgrasp.viewer.jgrdi.DebugContext;
import jgrasp.viewer.jgrdi.Field;
import jgrasp.viewer.jgrdi.JgrdiIsFinalException;
import jgrasp.viewer.jgrdi.Method;
import jgrasp.viewer.jgrdi.Type;
import jgrasp.viewer.jgrdi.Value;


/** Image viewer for java Images. **/
public abstract class ImageImageView extends ImageView {


   /** Creates a new color viewer.
    *
    * @param vcd viewer creation data. **/
   public ImageImageView(final ViewerCreateData vcd) {
      super(vcd);
   }


    /** {@inheritDoc} **/
   @Override
   public Data getData(final ViewerValueData valueData, final ViewerUpdateData data, final DebugContext context)
         throws ViewerException {
      String[] clientTextOut = new String[1];
      Value value = getImage(valueData.getValue(), context, clientTextOut);
      if (value.isNull()) {
         return new Data("Image is null.", clientTextOut[0], null, 0, 0, false);
      }
      Method getWidthMethod = value.getMethod(context, "getWidth", "int", null);
      int width = value.invokeMethod(context, getWidthMethod, null).toInt(context);
      Method getHeightMethod = value.getMethod(context, "getHeight", "int", null);
      int height = value.invokeMethod(context, getHeightMethod, null).toInt(context);
   
      String dsText = width + " x " + height;
   
      if (width <= 0 || height <= 0) {
         return new Data(dsText, clientTextOut[0], null, 0, 0, false);
      }
      Value scaledWidthV = context.createPrimitiveValue("int", String.valueOf(width));
      Value scaledHeightV = context.createPrimitiveValue("int", String.valueOf(height));
      Value zeroV = context.createPrimitiveValue("int", "0");
      Value nullV = context.createNullValue();
   
      Value bufferedImage = null;
      boolean complete = true;
      boolean createdImage = false;
      int type = BufferedImage.TYPE_INT_ARGB;
      if (value.isInstanceOf(context, "java.awt.image.BufferedImage")) {
         type = value.getFieldValue(context, "imageType").toInt(context);
         if (type == BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_RGB
               || type == BufferedImage.TYPE_INT_BGR) {
            bufferedImage = value;
         }
      }
      if (bufferedImage == null) {
         createdImage = true;
         Type bufferedImageType = context.getType("java.awt.image.BufferedImage");
         bufferedImageType.validate(context);
         Constructor biCons = bufferedImageType.getConstructor(context, new String[] { "int", "int", "int" });
         bufferedImage = bufferedImageType.createInstance(context, biCons, new Value[] { scaledWidthV, scaledHeightV,
               context.createPrimitiveValue("int", "2" /* TYPE_INT_ARGB */) });
         Method getGraphicsMethod = bufferedImageType.getMethod(context, "getGraphics", "java.awt.Graphics", null);
         Value graphics = bufferedImage.invokeMethod(context, getGraphicsMethod, null);
         Method drawImage = graphics.getMethod(context, "drawImage", "boolean", new String[] { "java.awt.Image",
               "int", "int", "int", "int", "java.awt.image.ImageObserver" });
         Value result = graphics.invokeMethod(context, drawImage, new Value[] { value, zeroV, zeroV, scaledWidthV,
               scaledHeightV, nullV });
         complete = result.toBoolean(context);
         Method dispose = graphics.getMethod(context, "dispose", "void", null);
         graphics.invokeMethod(context, dispose, null);
         List<Field> gFields = graphics.getType(context).getFields(Type.INSTANCE);
         for (Field f : gFields) {
            Type t = f.getType(context);
            if (t != null && t.isObject(context)) {
               graphics.setFieldValue(context, f, nullV);
            }
         }
      }
      List<Value> pixels = null;
      Value raster = bufferedImage.getFieldValue(context, "raster");
      if (!raster.isNull()) {
         Value rgbData = raster.getFieldValue(context, "data");
         if (!rgbData.isNull()) {
            pixels = rgbData.getArrayElements(context);
         }
      }
   
      if (pixels == null || pixels.size() != width * height) {
         return new Data(dsText, clientTextOut[0], null, 0, 0, false);
      }
      int[] pixelData = new int[pixels.size()];
      for (int i = 0; i < pixels.size(); i++) {
         int pixel = pixels.get(i).toInt(context);
         switch (type) {
            case BufferedImage.TYPE_INT_RGB:
               pixel |= 0xff000000;
               break;
            case BufferedImage.TYPE_INT_BGR:
               pixel = 0xff000000 | (pixel & 0xff0000) >> 16 | pixel & 0xff00 | (pixel & 0xff) << 16;
               break;
            default: // Includes BufferedImage.TYPE_INT_ARGB.
               break;
         }
         pixelData[i] = pixel;
      }
   
      if (createdImage) {
         Method flush = bufferedImage.getMethod(context, "flush", "void", null);
         bufferedImage.invokeMethod(context, flush, null);
         try {
            bufferedImage.setFieldValue(context, "raster", nullV);
         }
         catch (JgrdiIsFinalException ignored) {
         }
         try {
            bufferedImage.setFieldValue(context, "surfaceManager", nullV);
         }
         catch (JgrdiIsFinalException ignored) {
         }
      }
      return new Data(dsText, clientTextOut[0], pixelData, width, height, complete);
   }


   /** Gets the image to be displayed.
    *
    * @param value the value being viewed.
    *
    * @param context the current debugger context.
    *
    * @param clientTextOut the first element of this array is used to return display text, if any.
    *
    * @throws ViewerException if an error occurs while retrieving the image.
    *
    * @return the image value. **/
   public abstract Value getImage(Value value, DebugContext context, String[] clientTextOut) throws ViewerException;
}