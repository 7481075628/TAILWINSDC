import java.util.Scanner;

       public class Ejercicio05 {
               public static void main(String[] args) {
                        Scanner entrada = new Scanner(System.in);
                        char letra;
                        double base, altura, lado, operacion, radio;
                        System.out.print(«Ingresar letra(a-d): «);
                        letra = entrada.next().charAt(0);
                        switch(letra)
                        {
                              case ‘a’: case ‘A’:
                                       System.out.println(«Area del Triangulo»);
                                       System.out.print(«Ingresar base: «);
                                       base = entrada.nextDouble();
                                       System.out.print(«Ingresar altura: «);
                                       altura = entrada.nextDouble();
                                       operacion = (base*altura)/2;
                                       System.out.println(«El area del triangulo es: »
                                                                                          + operacion);
                                       break;
                              case ‘b’: case ‘B’:
                                       System.out.println(«Area del Cuadrado»);
                                       System.out.print(«Ingresar lado: «);
                                       lado = entrada.nextDouble();
                                       operacion = Math.pow(lado,2);
                                       System.out.println(«El area del cuadrado es: »
                                                                                          + operacion);
                                       break;
                              case ‘c’: case ‘C’:
                                       System.out.println(«Area del Circulo»);
                                       System.out.print(«Ingresar radio: «);
                                       radio = entrada.nextDouble();
                                       operacion = Math.PI*Math.pow(radio,2);
                                       System.out.println(«El area del circulo es: »
                                                                                           + operacion);
                                       break;
                                       default:
                                       System.out.println(«opcion incorrecto»);
                        }
               }
       }
