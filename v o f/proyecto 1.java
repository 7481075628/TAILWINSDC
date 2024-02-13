import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner escaner = new Scanner(System.in);
		// Mandar mensaje
		System.out.println("Escribe un número:");
		// Leer lo que se introduce
		double numero = escaner.nextDouble();
		// Comparar
		if (numero == 0) {
			System.out.println("El número es neutro");
		} else if (numero < 0) {
			System.out.println("El número es negativo");
		} else {
			System.out.println("El número es positivo");
		}
	}
}
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
import java.util.Scanner;

public class Promedio {

 

 

    public static void main(String[] args) {

      Scanner read = new Scanner (System.in);

        read = new Scanner (System.in);

             Scanner a1= new Scanner (System.in);

        int OPC;

        double a;

        do{

            System.out.println("Bienvenido");

            System.out.println("1 Iniciar");

            System.out.println("2.Salir");

               OPC = read.nextInt();

        switch (OPC){

            case 1:

                    System.out.println("Promedio");

             System.out.println("Inserte un numero");

             a = a1.nextDouble();

 

             if (a<=5.9)

             {

                 System.out.println("Reprobado ");

             }

             if (a>=6 && a<=6.9)

             {

                 System.out.println("Suficiente");

             }

             if (a>=7 && a<=7.9)

             {

                 System.out.println("Regular");

             }

             if (a>=8 && a<=8.9)

             {

                 System.out.println("Bien");

             }

             if (a>=9 && a<=9.9)

             {

                 System.out.println("Muy bien ");

             }

             if (a>=10)

             {

                 System.out.println("Excelente");

             }

                }

        }while(OPC<2);

    }

}