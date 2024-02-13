


import java.awt.*;
import javax.swing.*;


public class Gui04 extends JFrame {
public Gui04(){


setTitle("Horno microondas");
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setLayout(new BorderLayout());
// Create panel p1 for the buttons and set GridLayout
JPanel p1 = new JPanel();
p1.setLayout(new GridLayout(4, 3));
// Add buttons to the panel
for(int i = 1; i <= 9; i++) {
p1.add(new JButton("" + i));
}
p1.add(new JButton("" + 0));
p1.add(new JButton("Start"));
p1.add(new JButton("Stop"));
// Create panel p2 to hold a text field and p1
JPanel p2 = new JPanel();
p2.setLayout(new BorderLayout());
p2.add(new JTextField("Aquí el tiempo"),
BorderLayout.NORTH);
p2.add(p1, BorderLayout.CENTER);
// Add p2 and a button to the frame
add(p2, BorderLayout.EAST);
add(new Button("Aquí la comida"),
BorderLayout.CENTER);
setSize(400, 250);
setVisible(true);
}
/** Main method */
public static void main(String[] args) {
Gui04 frame = new Gui04();
}
}