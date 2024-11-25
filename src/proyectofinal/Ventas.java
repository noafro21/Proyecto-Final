
package proyectofinal;

import javax.swing.JOptionPane;

public class Ventas {
    private static String[] articulos = {"Teléfono celular", "Cargador", "Audífonos"};
    private static double[] precios = {500.00, 20.00, 15.00};
    private static int[] stock = {10, 20, 15};

    
   
    public static void mostrarMenu() {
        int[] carrito = new int[articulos.length]; 
        boolean salir = false;

        while (!salir) {
            String[] opciones = {"Teléfonos celulares", "Accesorios", "Finalizar compra"};
            String seleccion = (String) JOptionPane.showInputDialog(
                null, 
                "Seleccione un área:", 
                "Menú principal", 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                opciones, 
                opciones[0]);

            if (seleccion == null || seleccion.equals("Finalizar compra")) {
                finalizarCompra(carrito);
                salir = true;
            } else {
                mostrarArticulos(seleccion, carrito);
            }
        }
    }

  
    private static void mostrarArticulos(String categoria, int[] carrito) {
        String[] opciones;
        int inicio, fin;

        if (categoria.equals("Teléfonos celulares")) {
            inicio = 0;
            fin = 1; 
        } else {
            inicio = 1;
            fin = articulos.length; 
        }

        opciones = new String[fin - inicio];
        for (int i = inicio; i < fin; i++) {
            opciones[i - inicio] = articulos[i];
        }

        String articulo = (String) JOptionPane.showInputDialog(
            null, 
            "Seleccione un artículo:", 
            "Artículos disponibles", 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            opciones, 
            opciones[0]);

        if (articulo != null) {
            agregarAlCarrito(articulo, carrito);
        }
    }

   
    private static void agregarAlCarrito(String articulo, int[] carrito) {
        int index = -1;
        for (int i = 0; i < articulos.length; i++) {
            if (articulos[i].equals(articulo)) {
                index = i;
                break;
            }
        }

        if (index == -1) return; 

        String cantidadStr = JOptionPane.showInputDialog(
            null, 
            "Ingrese la cantidad (Stock: " + stock[index] + "):", 
            "Agregar al carrito", 
            JOptionPane.QUESTION_MESSAGE);

        if (cantidadStr == null) return; 
        try {
            int cantidad = Integer.parseInt(cantidadStr);

            if (cantidad > stock[index]) {
                JOptionPane.showMessageDialog(null, "No hay suficiente stock disponible.");
                return;
            }

            if (index == 0 && (carrito[index] + cantidad > 2)) {
                JOptionPane.showMessageDialog(null, "Solo se pueden comprar hasta 2 teléfonos celulares.");
                return;
            }

            carrito[index] += cantidad;
            stock[index] -= cantidad;

            JOptionPane.showMessageDialog(null, "Artículo agregado al carrito.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cantidad inválida.");
        }
    }

    private static void finalizarCompra(int[] carrito) {
        boolean carritoVacio = true;
        for (int cantidad : carrito) {
            if (cantidad > 0) {
                carritoVacio = false;
                break;
            }
        }

        if (carritoVacio) {
            JOptionPane.showMessageDialog(null, "El carrito está vacío. ¡Gracias por su visita!");
            return;
        }

        String nombre = JOptionPane.showInputDialog("Ingrese su nombre:");
        String cedula = JOptionPane.showInputDialog("Ingrese su cédula:");
        String correo = JOptionPane.showInputDialog("Ingrese su correo electrónico:");

        StringBuilder recibo = new StringBuilder("Detalles de la compra:\n");
        double total = 0;

        for (int i = 0; i < articulos.length; i++) {
            if (carrito[i] > 0) {
                recibo.append(articulos[i])
                      .append(" x")
                      .append(carrito[i])
                      .append(" - $")
                      .append(precios[i] * carrito[i])
                      .append("\n");
                total += precios[i] * carrito[i];
            }
        }

        double iva = total * 0.13;
        recibo.append("\nSubtotal: $").append(total);
        recibo.append("\nIVA (13%): $").append(iva);
        recibo.append("\nTotal: $").append(total + iva);

        recibo.append("\n\nCliente: ").append(nombre).append("\nCédula: ").append(cedula).append("\nCorreo: ").append(correo);

        JOptionPane.showMessageDialog(null, recibo.toString(), "Recibo", JOptionPane.INFORMATION_MESSAGE);
    }
    
}
