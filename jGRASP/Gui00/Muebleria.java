





public class Ventas 
public static void main (string [] args )
    }    
        self.tipo = new tipo ();
        self.fecha = new fecha ();
        self.tipo_pago = new tipo_pago();
        self.monto =new  monto();
    {

# Lista para almacenar las ventas
ventas = []

# Función para agregar una venta
def agregar_venta(tipo, fecha, tipo_pago, monto):
    nueva_venta = Venta(tipo, fecha, tipo_pago, monto)
    ventas.append(nueva_venta)
}
# Función para realizar una consulta de ventas
def consultar_ventas(tipo=None, fecha=None, tipo_pago=None);
    resultados = []
    for venta in ventas:
        if (tipo is None or venta.tipo == tipo) and \)
           (fecha is None or venta.fecha == fecha) and \)
           (tipo_pago is None or venta.tipo_pago == tipo_pago);
            resultados.append(venta)
    return resultados

# Ejemplo de uso
agregar_venta("silla", "2023-11-16", "contado", 100)
}
agregar_venta("mesa", "2023-11-15", "crédito", 150)
agregar_venta("ropero", "2023-11-14", "transferencia", 120)

# Consulta de ventas por tipo
ventas_dama = consultar_ventas(tipo="silla")
print("Ventas de muebleria silla :", len(ventas_dama))

# Consulta de ventas por fecha
ventas_fecha = consultar_ventas(fecha="2023-11-15")
print("Ventas del 15 de noviembre:", len(ventas_fecha))

# Consulta de ventas por tipo de pago
ventas_contado = consultar_ventas(tipo_pago="contado")
print("Ventas al contado:", len(ventas_contado))
}