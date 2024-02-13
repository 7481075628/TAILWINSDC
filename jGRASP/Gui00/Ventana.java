import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
  
  public class Ventana extends JFrame implements ActionListener {
   private JLabel texto ;
   private  JTextField caja ;
   private JButton boton ;
   
   public Ventana () {
     super (); 
       
         configurarVentana ();
      inicializarComponentes ();
 }
 
 private void configurarVentana () {
     this .setTitle ("Esta es una Ventana");
     this .setSize(310,210);
     this .setLocationRelativeTo(null);
     this .setLayout(null);
     this .setResizable (false);
     this .setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
        
 private void inicializarComponentes ()  {   
    texto = new JLabel();
    caja  = new JTextField();
    boton = new JButton ();
    
    texto.setText ("Inserte Nombre");
    texto.setBounds(50,50,100,25);
    caja.setBounds (150,50,100,25);
    boton.setText ("Mostrar Mensaje");
    boton.setBounds (50,100,200,30);
    boton.addActionListener(this);
    
    this.add(texto);
    this.add(caja);
    this.add(boton);
}

public void actionPerformed(ActionEvent e) {
   String nombre = caja.getText();
   JOptionPane.showMessageDialog(this, "Hola "+ nombre + "." );
   
 }
 public static void main (String [] args ) {
 
  Ventana V = new Ventana ();
  V.setVisible (true) ;
}
  }
  
  

       