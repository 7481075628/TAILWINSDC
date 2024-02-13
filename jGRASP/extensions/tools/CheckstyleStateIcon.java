
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import jgrasp.Colors;
import jgrasp.Drawing;
import jgrasp.StateIcon;


/** State icon for Checkstyle. **/
public class CheckstyleStateIcon extends StateIcon {

   /** Creates a new CheckstyleStateIcon.
    *
    *  @param stateIn the icon state. **/
   public CheckstyleStateIcon(final State state) {
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
   
      g.setPaint(Drawing.createPaint(.136f, 1f, 1f, .82f, xoffs, yoffs,
             xoffs + d, yoffs + d));
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

