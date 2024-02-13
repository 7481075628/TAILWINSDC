
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import jgrasp.AutosizedIcon;
import jgrasp.Colors;
import jgrasp.Drawing;


/** The Checkstyle file action icon. **/
public class CheckstyleFileIcon extends AutosizedIcon {
   
   /** The last paint used. **/
   private static Paint paint;
   
   /** The last icon size, or -1 if uninitialized. **/
   private static int lastSz = -1;
   
   /** The last color state, or -1 if uninitialized. **/
   private static int lastState = -1;
   
   
   /** Sets the last icon size and cached paint.
    *
    *  @param sz the current icon size.
    *
    *  @param st the current color state.
    *
    *  @param paintIn the new paint. **/
   private static void setPaint(final int sz, final int st, final Paint paintIn) {
      lastSz = sz;
      lastState = st;
      paint = paintIn;
   }

   
   /** {@inheritDoc} **/
   public void paintImage(final Graphics2D g, final int w, final int h) {
      int sz = Math.min(w, h);
      if (sz < 3) {
         return;
      }
      int xoffs = (w - sz) / 2;
      int yoffs = (h - sz) / 2;
      int state = Drawing.getColorState();
      if (sz != lastSz || state != lastState) {
         setPaint(sz, state,
             Drawing.createPaint(.136f, 1f, 1f, .82f, sz / 5f, sz / 5f,
             sz - sz / 5f, sz - sz / 5f));
      }
      Drawing.drawLinedPage(g, xoffs, yoffs, sz, sz, paint, false,
            Drawing.getColor(Colors.BLACK), 0, !Drawing.drawOutlines());
      int checkW = (sz * 4 + 2) / 5;
      int checkOffsX = sz - checkW;
      int checkOffsY = (sz - checkW + 1) / 2;
      Drawing.drawCheck(g, xoffs + checkOffsX, yoffs + checkOffsY, checkW, sz,
            Drawing.getColor(Colors.WHITE), !Drawing.drawOutlines());
   }
}
