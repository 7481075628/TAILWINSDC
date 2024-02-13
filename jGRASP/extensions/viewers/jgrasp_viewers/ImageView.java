package jgrasp_viewers;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.EnumSet;
import javax.swing.JLabel;
import javax.swing.JPanel;
import jgrasp.viewer.PaintUtil;
import jgrasp.viewer.ViewerCreateData;
import jgrasp.viewer.ViewerException;
import jgrasp.viewer.ViewerInfo;
import jgrasp.viewer.ViewerPriorityData;
import jgrasp.viewer.ViewerRoot;
import jgrasp.viewer.ViewerValueData;
import jgrasp.viewer.ViewerUpdateData;
import jgrasp.viewer.jgrdi.DebugContext;


/** A base class for image viewers. **/
public abstract class ImageView extends ViewerRoot {

   /** Data for the current image. **/
   public static class Data {
   
      /** The display size description. **/
      private final String dsText;
   
      /** Text supplied by the subclass. **/
      private final String clientText;
   
      /** The pixel data, or null if the image is not available. **/
      private final int[] pixelData;
   
      /** The width of the image. **/
      private final int w;
   
      /** The height of the image. **/
      private final int h;
   
      /** True if the image data is complete, false otherwise. **/
      private final boolean complete;
   
   
      /** Creates a new Data.
       *
       * @param dsTextIn the display size description.
       *
       * @param clientTextIn text supplied by the subclass.
       *
       * @param pixelDataIn the pixel data, or null if the image is not available.
       *
       * @param wIn the width of the image supplied in <code>pixelData</code>.
       *
       * @param hIn the height of the image supplied in <code>pixelData</code>.
       *
       * @param completeIn true if the image data is complete, false otherwise. **/
      Data(final String dsTextIn, final String clientTextIn, final int[] pixelDataIn, final int wIn, final int hIn,
            final boolean completeIn) {
         dsText = dsTextIn;
         clientText = clientTextIn;
         pixelData = pixelDataIn;
         w = wIn;
         h = hIn;
         complete = completeIn;
      }
   }

   /** Maximum preferred image display width. **/
   private static final int MAX_WIDTH = 2000;

   /** Maximum preferred image display height. **/
   private static final int MAX_HEIGHT = 2000;

   /** The current image data. **/
   private Data currentData;

   /** The controls. **/
   private JLabel controls;

   /** The gui root panel. **/
   private JPanel imagePanel;

   /** The display image. **/
   private BufferedImage displayImage;

   /** Display text describing image size. **/
   private String displaySizeText;

   /** Last display text provided by subclass. **/
   private String currentClientText;

   /** True if the image was complete when captured, false otherwise. **/
   private boolean isComplete;


   /** Creates a new image viewer.
    *
    * @param vcd viewer creation data. **/
   public ImageView(final ViewerCreateData vcd) {
      super(false, EnumSet.of(CreationFlags.STANDARD_BORDER));
   }


   /** {@inheritDoc} **/
   @Override
   public void buildGui(final JPanel mainPanel) {
      buildImagePanel();
      mainPanel.setLayout(new BorderLayout());
      mainPanel.add(imagePanel, "Center");
      buildControls();
   }


   /** {@inheritDoc} **/
   @Override
   public void destroy() {
   }


   /** {@inheritDoc} **/
   @Override
   public void getInfo(final ViewerInfo vi) {
      vi.setShortDescription("Detail viewer for images");
      vi.setLongDescription("This viewer displays the image at actual size up to a maximum of 250x250 pixels.");
   }


   /** {@inheritDoc} **/
   @Override
   public int getPriority(final ViewerPriorityData vpd) {
      return 1000;
   }


   /** {@inheritDoc} **/
   @Override
   public String getViewName() {
      return "Image";
   }


   /** {@inheritDoc} **/
   @Override
   public void updateGui() {
      Data data = currentData;
   
      displaySizeText = data.dsText;
      currentClientText = data.clientText;
   
      if (data.pixelData == null) {
         displayImage = null;
      }
      else {
         displayImage = new BufferedImage(data.w, data.h, BufferedImage.TYPE_INT_ARGB);
         displayImage.setRGB(0, 0, data.w, data.h, data.pixelData, 0, data.w);
         imagePanel.revalidate();
         getMainPanel().revalidate();
      }
      isComplete = data.complete;
   
      imagePanel.repaint();
   }


   /** Builds the controls. **/
   private void buildControls() {
      controls = new JLabel(" ");
      getVIData().setControls(controls);
   }


   /** Builds the main display panel. **/
   private void buildImagePanel() {
      imagePanel = 
         new JPanel() {
         
            @Override
            public Dimension getPreferredSize() {
               Insets insets = getInsets();
               if (displayImage == null) {
                  return new Dimension(10 + insets.left + insets.right, 10 + insets.top + insets.bottom);
               }
               double pixelScale = PaintUtil.getBIScale(getGraphicsConfiguration());
               int imageWidth = displayImage.getWidth();
               int imageHeight = displayImage.getHeight();
               return new Dimension(Math.min((int) (imageWidth / pixelScale) + insets.left + insets.right, MAX_WIDTH),
                     Math.min((int) (imageHeight / pixelScale) + insets.top + insets.bottom, MAX_HEIGHT));
            }
          
          
            @Override
            public Dimension getMinimumSize() {
               return getPreferredSize();
            }
            
            
            @Override
            public void paintComponent(final Graphics g) {
               int width = getWidth();
               int height = getHeight();
               if (!isViewerTransparent()) {
                  g.setColor(getBackground());
                  g.fillRect(0, 0, width, height);
               }
               if (displayImage == null) {
                  return;
               }
               Insets insets = getInsets();
               int borderXOffs = insets.left;
               int borderYOffs = insets.top;
               width -= insets.left + insets.right;
               height -= insets.top + insets.bottom;
               if (width < 0) {
                  width = 0;
               }
               if (height < 0) {
                  height = 0;
               }
               int imageWidth = displayImage.getWidth();
               int imageHeight = displayImage.getHeight();
               AffineTransform xform = ((Graphics2D) g).getTransform();
               double sx = xform.getScaleX();
               double sy = xform.getScaleY();
               int scaledWidth = (int) (width * sx);
               int scaledHeight = (int) (height * sx);
               Image drawImage;
               boolean scaled = false;
               if (imageWidth == scaledWidth && imageHeight <= scaledHeight
                     || imageHeight == scaledHeight && imageWidth <= scaledWidth) {
                  drawImage = displayImage;
               }
               else {
                  scaled = true;
                  double scale = Math.min((double) scaledWidth / imageWidth, (double) scaledHeight / imageHeight);
                  if (scale > 1) {
                     scale = Math.floor(scale);
                  }
                  if (scale == 1) {
                     drawImage = displayImage;
                  }
                  else {
                     imageWidth = (int) (imageWidth * scale);
                     imageHeight = (int) (imageHeight * scale);
                     drawImage = (imageWidth > 0 && imageHeight > 0)
                           ? displayImage.getScaledInstance(imageWidth, imageHeight, Image.SCALE_DEFAULT) : null;
                  }
               }
               String text = displaySizeText;
               if (scaled) {
                  text += " (shown at " + imageWidth + " x " + imageHeight + ")";
               }
               if (!isComplete) {
                  text += " (image is currently incomplete)";
               }
               setDisplayText(text);
            
               int xOffs = borderXOffs + (width - (int) (imageWidth / sx)) / 2;
               int yOffs = borderYOffs + (height - (int) (imageHeight / sy)) / 2;
               if (drawImage != null) {
                  PaintUtil.drawPhysicalImage((Graphics2D) g, drawImage, xOffs, yOffs);
               }
            }
         };
   }


   /** Sets the display text.
    *
    * @param sizeText text description of the size. **/
   private void setDisplayText(final String sizeText) {
      String text = (sizeText == null) ? "" : sizeText;
      String ccText = currentClientText == null ? "" : currentClientText;
      if (!text.isEmpty() && !ccText.isEmpty()) {
         text += "  ";
      }
      text += ccText;
      if (text.isEmpty()) {
         text = " ";
      }
      controls.setText(text);
   }


    /** {@inheritDoc} **/
   @Override
   public void updateState(final ViewerValueData valueData, final ViewerUpdateData data, final DebugContext context)
         throws ViewerException {
      currentData = getData(valueData, data, context);
   }


   /** Gets the image data. This is called by the debugger when the value being viewed may have changed or a watched
    * event has occurred. This will be called from the debugger thread.
    *
    * @param valueData the new value and associated information, such as declared type. This will be null for
    * animation updates.
    *
    * @param data information about this update, such as why it was triggered, and if it was triggered by a flagged
    * method entry, the method argument values.
    *
    * @param context debugger context that is necessary for working with values, and provides some global debugger
    * access. This will be null for animation updates.
    *
    * @return the new image data.
    *
    * @throws ViewerException if an exception is encountered. **/
   public abstract Data getData(final ViewerValueData valueData, final ViewerUpdateData data,
         final DebugContext context) throws ViewerException;
}
