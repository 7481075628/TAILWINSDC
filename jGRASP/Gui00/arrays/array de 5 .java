import java.util.Scanner;

/**
 *
 * @author montserrat
 */
public class TestLista {

    /**
     * @Crea un arreglo de “n” elementos pidiendo por teclado cada elemento
      * al final da el arreglo
     */
    public static void main (String [ ] args) {

           //System.out.println ("Empezamos el programa");
           Scanner x = new Scanner (System.in); //Creación de un objeto Scanner
           int[] array = new int[5];
           int i, limiteDelArreglo;
           System.out.println("Ingrese el limite del arreglo");
           limiteDelArreglo = x.nextInt();
           
           System.out.println ("Digite los elementos del arreglo");
           for ( i= 0; i<limiteDelArreglo; i++){
               
               array[i]= x.nextInt();
               System.out.println ("El Indice ["+ (i) +"]="+ " esta en el valor "+array[i]);
           }
           for ( i= 0; i<limiteDelArreglo; i++){
               System.out.print(array[i]+ ", ");
           }
    }
} //Cierre de la clase