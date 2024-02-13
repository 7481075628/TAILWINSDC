

int [] valores = {};

    int positivos = 0, suma = 0;

    for (int i = 0; i < valores.length; i++) {
      
        System.out.println(i + ":" + valores[i]);
    
        if (valores[i] > 0)
            suma += valores[i];
    }
    
    System.out.println("Sumatoria de numeros positivos: " + suma);