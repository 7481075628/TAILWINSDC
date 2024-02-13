
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * ComponentExample -- This example is intended to illustrate the capabilities of the jGRASP viewers for Components. 
 *
 * Set a breakpoint on the last line of "main()" as indicated below. Click the "Run in Canvas" button on the toolbar.
 * Then hit the blue "Resume" button on either the canvas window or the "Debug" tab. Adjust the size of the frame that
 * pops up to see the effect on the frame viewer. Select the frame viewer then move the mouse across the windows shown
 * there to see information for the window under the mouse and all its ancestors.
 * 
 * To create your own canvas, on the Debug tab click the "Open Canvas" button. Click the "Step" and Step-in" buttons
 * as needed until you see objects and primitives of interest in the Variables tab  and then drag them onto the
 * canvas. Arrange the viewers and click the "Play" button or step/resume using debug controls on the canvas or in
 * the "Debug" tab.
 */
public class ComponentExample {

   public static void main(String[] args) {
      JPanel panel = new JPanel(new BorderLayout(5, 5));
      JLabel l1 = new JLabel(" ");
      l1.setOpaque(true);
      panel.add(l1, "South");
      JPanel center = new JPanel();
      center.setLayout(new BoxLayout(center, BoxLayout.X_AXIS));
      panel.add(center, "Center");
      JLabel l2 = new JLabel("1");
      l2.setOpaque(true);
      JLabel l3 = new JLabel("2");
      l3.setOpaque(true);
      center.add(l2);
      center.add(l3);
   
      JFrame frame = new JFrame();
   
      frame.setContentPane(panel);
      frame.setAlwaysOnTop(true);
      frame.pack();
      frame.setVisible(true);
      l1.setBackground(Color.WHITE);
      center.setBackground(Color.RED);
      l2.setBackground(Color.GREEN);
      l3.setBackground(Color.CYAN);
      int i = 0;  // Set breakpoint here
   }

}
