ort java.util.Scanner;

public class Ejercicio2 {

	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		
		System.out.print("Introduzca el número de alumnos: ");
		int numAlumnos = Integer.parseInt(entrada.nextLine());
		
		String[] nombres = new String[numAlumnos];
		double[] notasN = new double[numAlumnos];
		String[] notasS = new String[numAlumnos];
		
		System.out.println();
		for(int i = 0; i < nombres.length; i++) {
			System.out.print("Introduzca el nombre del alumno: ");
			nombres[i] = entrada.nextLine();
			do{
				System.out.print("\nIntroduzca la nota: ");
				notasN[i] = Double.parseDouble(entrada.nextLine());
				if(notasN[i] >= 0 && notasN[i] < 5){
					notasS[i] = "Suspenso";
				} else if(notasN[i] >= 5 && notasN[i] < 7) {
					notasS[i] = "Bien";
				} else if(notasN[i] >= 7 && notasN[i] < 9) {
					notasS[i] = "Notable";
				} else if(notasN[i] >= 9 && notasN[i] <= 10) {
					notasS[i] = "Sobresaliente";
				} else {
					notasS[i] = "Nota erronea";
				}
			} while(notasN[i] < 0 || notasN[i] > 10);			
		}
		entrada.close();
		for(int i = 0; i < nombres.length; i++) {
			System.out.println("La nota de " +  nombres[i] +  " es " + notasN[i] + ", " + notasS[i]);
		}
	}
	