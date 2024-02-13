
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Stroke;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import jgrasp.Colors;
import jgrasp.Drawing;
import jgrasp.StateIcon;


/** State icon for JUnit. **/
public class JUnitStateIcon extends StateIcon {

   /** Reusable array. **/
   private static float[] xpoints = new float[3];

   /** Reusable array. **/
   private static float[] ypoints = new float[3];


   /** Creates a new JUnitStateIcon.
    *
    *  @param stateIn the icon state. **/
   public JUnitStateIcon(final State state) {
      super(state);
   }


   /** {@inheritDoc} **/
   public void paintMainIcon(final Graphics2D g, final int x, final int y,
         final int w, final int h) {
      int d = (Math.min(w - 1, h - 1) + 1) * 5 / 12 * 2;
      int xoffs = x + (w - d) / 2;
      int yoffs = y + h - 1 - (h - d) / 2 - d;
      
      int lineW = Drawing.getLineWidth(h);
      float lineOffs = lineW / 2f;
      Rectangle2D.Float rect = new Rectangle2D.Float(xoffs + lineOffs,
            yoffs + lineOffs, d - lineW, d - lineW);
   
      g.setPaint(new LinearGradientPaint(xoffs, yoffs, xoffs + d, yoffs + d,
            new float[] { 0, .5f, 1.0f },
            new Color[] { Drawing.getColor(Colors.HL_RED),
               Drawing.getColor(Colors.WHITE), Drawing.getColor(Colors.HL_GREEN)}));
      g.fill(rect);
      
      Stroke stroke = new BasicStroke(lineW, BasicStroke.CAP_SQUARE,
            BasicStroke.JOIN_ROUND);
      g.setStroke(stroke);
      if (Drawing.drawOutlines()) {
         g.setColor(Drawing.getColor(Colors.BLACK));
      }
      g.draw(rect);
   }
}

