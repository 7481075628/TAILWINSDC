
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import jgrasp.AutosizedIcon;
import jgrasp.Colors;
import jgrasp.Drawing;


/** The JUnit compile action icon. **/
public class JUnitCompileIcon extends AutosizedIcon {
   
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
         setPaint(sz, state, new LinearGradientPaint(
               xoffs + sz / 5, yoffs + sz / 5, xoffs + sz - sz / 5,
               yoffs + sz - sz / 5, new float[] { 0, .5f, 1.0f },
               new Color[] { Drawing.getColor(Colors.HL_RED),
                  Drawing.getColor(Colors.WHITE), Drawing.getColor(Colors.HL_GREEN)}));
      }
      Drawing.drawCross(g, xoffs, yoffs, sz, sz, paint, 1.1f, !Drawing.drawOutlines());
   }
}
