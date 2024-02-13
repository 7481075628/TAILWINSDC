from random import sample 
# Importamos un M�todo de la biblioteca random para generar listas aleatorias

lista = list(range(100)) # Creamos la lista base con n�meros del 1 al 100

# Creamos una lista aleatoria con sample 
#(8 elementos aleatorios de la lista base)
vectorins = sample(lista,8)

def insertionsort(vectorins): 
    """Esta funci�n ordenara el vector que le pases como argumento con
    el M�todo Insertion Sort"""
    
    # Imprimimos la lista obtenida al principio (Desordenada)
    print("El vector a ordenar con inserci�n es:", vectorins)
    
    largo = 0 # Establecemos un contador del largo
     
    for i in vectorins:
        largo += 1 # Obtenemos el largo del vector
    
    # Recorremos la lista de 1 hasta el largo del vector
    for i in range(1, largo): 
    
        elemento = vectorins[i] 
  
        # Movemos los elementos de vectorins[0...i-1], que son mayores que el elemento
        # a una posici�n adelante de su posici�n actual
        j = i-1
        while j >= 0 and elemento < vectorins[j] : 
                vectorins[j+1] = vectorins[j] 
                j -= 1
        vectorins[j+1] = elemento 
    print("El vector ordenado con inserci�n es: ", vectorins)

insertionsort(vectorins)