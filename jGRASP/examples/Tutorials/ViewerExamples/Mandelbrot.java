
import java.awt.Color;

/**
 * Mandelbrot - This example is intended to illustrate the capabilities of the 2D image viewer, which displays a
 * 2D structure (array, list, or combination of the two) of int or Integer as an image. The program zooms in to a
 * MandelBrot edge in 200 steps, scaling the range by .85 at each step. This is not the best/smoothest Mandlebrot
 * display possible, but one that runs fast enough to animate in real time in debug mode on modern hardware and is
 * computationally simple.
 *
 * Open Mandelbrot.jgrasp_canvas_xml .
 *
 * Set a breakpoint near the end of main() on the line indicated.
 *
 * Debug this program.
 *
 * When the debugger first stops, use the debugger controls to turn repeated resume on.
 *
 * Use the debugger controls to resume.
 *
 * The program will automatically animate, stopping at the breakpoint repeatedly.
 *
 * Use the "Delay" bar on the viewer to control the animation speed.
 */
public class Mandelbrot {

   public static void main(String[] args) {
      double centerX0 = 0.0;
      double centerY0 = 0.0;
      double scale0 = 2.0;
      double centerX1 = 0.3149995606276356;
      double centerY1 = 0.036975199984924934;
      double scale1 = .0;
      int maxIterations = 200;
   
      int size = 400;
      int[][] image = new int[size][size];
      
      int hueMag = 100;
      
      int numHues = 5000;
      int[] hues = new int[numHues];
      for (int h = 1; h < numHues; h++) {
         float hue = (h - 1) / (numHues - 1.0f);
         hues[h] = Color.getHSBColor(hue, .8f, 1.0f).getRGB();
      }
   
      double frac = 1.0;
      double fracStep = .92;
      while (true) {
         double centerX = centerX1 + (centerX0 - centerX1) * frac;
         double centerY = centerY1 + (centerY0 - centerY1) * frac;
         double scale = scale1 + (scale0 - scale1) * frac;
         if (scale < 2e-14) {
            // Stop when the precision gets too low for a decent image.
            break;
         }
         frac *= fracStep;
         int min = maxIterations * hueMag;
         int max = 0;
         for(int i = 0; i < size; i++) {
            double x0 = centerX + 2.0 * scale * (i / (double) size - .5);
            for (int j = 0; j < size; j++) {
               double y0 = centerY + 2.0 * scale * (j / (double) size - .5);
               double x = 0.0;
               double y = 0.0;
               double x2 = 0.0;
               double y2 = 0.0;
               int k;
               for (k = 0; x2 + y2 < 4.0 && k < maxIterations; k++) {
                  y = 2 * x * y + y0;
                  x = x2 - y2 + x0;
                  x2 = x * x;
                  y2 = y * y;
               }
               int extra = 0;
               if (k == maxIterations) {
                  k = 0;
               }
               else {
                  double partIter = 1 - Math.log(Math.log(x2 + y2) / 2.0 / Math.log(2)) / Math.log(2);
                  k = (int) ((k + partIter) * hueMag);
                  min = Math.min(k, min);
               }
               max = Math.max(k, max);
               image[i][j] = k;
            }
         }
         System.out.println("x " + centerX + " y " + centerY + " scale " + scale);
         for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
               int count = image[i][j];
               if (count > 0) {
                  int hue = 1 + (int) ((numHues - 2) * (image[i][j] - min) / (float) (max - min));
                  image[i][j] = hues[hue];
               }
            }
         }
      
         // Set a breakpoint on next line.
         int q = 0;
         q++;
      }
      try {
         Thread.sleep(10000);
      }
      catch (Exception e) {
      }
   }
}