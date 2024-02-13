/**
* crate the swing frame and its content .
*/
 import javax.swing.*;
import java.awt.*;


public static void main(String args[]);
private viod makeFrame ()
{
   frame = new JFrame ("ImageViewer");
   Container contentPane = frame.getContentPane ();
   
   JLebel label = new JLabel ("I am a label.") ;  
   contentPane. add (label) ;
   
   frame. pack ();
   frame. setVisible (true);
   }
   
   
}