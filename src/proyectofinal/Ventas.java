package proyectofinal;

import javax.swing.JOptionPane;

public class Ventas {

    private static Inventario inventario = new Inventario();
    private static int[] carrito = new int[10];

    public static void mostrarMenu() {
        inventario.guardarArticulo();
        boolean salir = false;

        if (Usuario.rolActive == Roles.Sin_Rol) {
            JOptionPane.showMessageDialog(null, "Debe iniciar sesión primero.");
            return;
        }

        while (!salir) {
            String[] opciones = {"Teléfonos celulares", "Accesorios", "Finalizar compra"};
            String seleccion = (String) JOptionPane.showInputDialog(
                    null,
                    "Seleccione un área:",
                    "Menú de ventas",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            if (seleccion == null || seleccion.equals("Finalizar compra")) {
                finalizarCompra();
                salir = true;
            } else {
                mostrarArticulos(seleccion);
            }
        }
    }

    private static void mostrarArticulos(String categoria) {
        String[] opciones;
        int inicio, fin;

        if (categoria.equals("Teléfonos celulares")) {
            inicio = 0;
            fin = 5;
        } else {
            inicio = 5;
            fin = inventario.inventario.size();
        }

        opciones = new String[fin - inicio];
        for (int i = inicio; i < fin; i++) {
            ArregloParaInventario producto = inventario.inventario.get(i);
            opciones[i - inicio] = producto.getMarca() + " " + producto.getModelo();
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
            agregarAlCarrito(categoria, articulo);
        }
    }

    private static void agregarAlCarrito(String categoria, String articulo) {
        int index = -1;
        for (int i = 0; i < inventario.inventario.size(); i++) {
            ArregloParaInventario producto = inventario.inventario.get(i);
            if ((categoria.equals("Teléfonos celulares") && i < 5
                    || categoria.equals("Accesorios") && i >= 5)
                    && (producto.getMarca() + " " + producto.getModelo()).equals(articulo)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            return;
        }

        ArregloParaInventario producto = inventario.inventario.get(index);
        String cantidadStr = JOptionPane.showInputDialog(
                null,
                "Ingrese la cantidad (Stock: " + producto.getCantidad() + "):",
                "Agregar al carrito",
                JOptionPane.QUESTION_MESSAGE);

        if (cantidadStr == null) {
            return;
        }

        try {
            int cantidad = Integer.parseInt(cantidadStr);

            if (cantidad > producto.getCantidad()) {
                JOptionPane.showMessageDialog(null, "No hay suficiente stock disponible.");
                return;
            }

            if (carrito[index] + cantidad > 2 && index < 5) {
                JOptionPane.showMessageDialog(null, "Solo se pueden comprar hasta 2 teléfonos celulares.");
                return;
            }

            carrito[index] += cantidad;
            producto.setCantidad(producto.getCantidad() - cantidad);

            JOptionPane.showMessageDialog(null, "Artículo agregado al carrito.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Cantidad inválida.");
        }
    }

    private static void finalizarCompra() {
        boolean carritoVacio = true;
        for (int cantidad : carrito) {
            if (cantidad > 0) {
                carritoVacio = false;
                break;
            }
        }

        if (carritoVacio) {
            JOptionPane.showMessageDialog(null, "El carrito está vacío. ¡Gracias por su visita!", "¡Atención!", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String nombre = JOptionPane.showInputDialog(null,"Ingrese su nombre:", "Datos del cliente", JOptionPane.INFORMATION_MESSAGE);
        String cedula = JOptionPane.showInputDialog(null,"Ingrese su cédula:", "Datos del cliente", JOptionPane.INFORMATION_MESSAGE);
        String correo = JOptionPane.showInputDialog(null,"Ingrese su correo electrónico:", "Datos del cliente", JOptionPane.INFORMATION_MESSAGE);

        StringBuilder recibo = new StringBuilder("Detalles de la compra:\n");
        double total = 0;

        for (int i = 0; i < inventario.inventario.size(); i++) {
            ArregloParaInventario producto = inventario.inventario.get(i);
            if (carrito[i] > 0) {
                recibo.append(producto.getMarca())
                        .append(" ")
                        .append(producto.getModelo())
                        .append(" x")
                        .append(carrito[i])
                        .append(" - $")
                        .append(producto.getPrecio() * carrito[i])
                        .append("\n");
                total += producto.getPrecio() * carrito[i];
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
