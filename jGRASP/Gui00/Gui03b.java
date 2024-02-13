import javax.swing.*;
import java.awt.*;
public class Gui03b extends JFrame {
public Gui03b() {
super("Ejemplo de Layout");
setLayout(new GridLayout(4, 3, 5, 5));
for(int i = 1; i <= 10; i++)
add(new JButton(Integer.toString(i)));
setSize(200,200);
setVisible(true);
setDefaultCloseOperation(EXIT_ON_CLOSE);
}