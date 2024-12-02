
package proyectofinal;


import java.util.ArrayList;
import javax.swing.JOptionPane;
public class Inventario {
public  ArrayList<ArregloParaInventario> inventario = new ArrayList<>();

 public void guardarArticulo() { 
     
// Se inicializar 5 productos de Celulares 
inventario.add(new ArregloParaInventario("COD-CEL-001", "Samsung", "S9", 800.00, 20));
inventario.add(new ArregloParaInventario("COD-CEL-002", "Samsung", "S24Ultra", 1200.00, 15));
inventario.add(new ArregloParaInventario("COD-CEL-003", "iPhone", "11", 1000.00, 10));
inventario.add(new ArregloParaInventario("COD-CEL-004", "iPhone", "12", 1100.00, 12));
inventario.add(new ArregloParaInventario("COD-CEL-005", "Google", "Pixel 7", 900.00, 18)); 
// Se inicializar 5 productos de Accesorios
inventario.add(new ArregloParaInventario("COD-ACC-001", "Samsung", "GalaxyBuds", 150.00, 30));
inventario.add(new ArregloParaInventario("COD-ACC-002", "iPhone", "EarPods", 100.00, 25));
inventario.add(new ArregloParaInventario("COD-ACC-003", "JBL", "Headphones", 200.00, 20));
inventario.add(new ArregloParaInventario("COD-ACC-004", "Belkin", "Cargador", 50.00, 40)); 
inventario.add(new ArregloParaInventario("COD-ACC-005", "Spigen", "Protector de Pantalla", 25.00, 50));
     
 }
// Método que permite el registro de articulos al inventario
   public  void registrarArticulo() {
        String codigo, marca, modelo;
        double precio;
        int cantidad;
        //Ingres de los datos del artículo
        codigo = JOptionPane.showInputDialog("Ingrese el código del artículo (ej: COD-ACC-201):");
        marca = JOptionPane.showInputDialog("Ingrese la marca del artículo:");
        modelo = JOptionPane.showInputDialog("Ingrese el modelo del artículo:");
        precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del artículo:"));
        cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad del artículo:"));
        
        inventario.add(new ArregloParaInventario(codigo, marca, modelo, precio, cantidad));
        JOptionPane.showMessageDialog(null, "Artículo registrado con éxito.");
    }
    // Método que permite modificar carácteristicas de un artículo existente en inventario 
    public void modificarArticulo() {
        //Atributos del método
        String codigo, marca, modelo;
        double precio;
        int cantidad;

        codigo = JOptionPane.showInputDialog("Ingrese el código del artículo a modificar:");
        ArregloParaInventario producto = buscarProducto(codigo);

        if (producto != null) {
            marca = JOptionPane.showInputDialog("Ingrese la nueva marca del artículo:", producto.getMarca());
            modelo = JOptionPane.showInputDialog("Ingrese el nuevo modelo del artículo:", producto.getModelo());
            precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo precio del artículo:", producto.getPrecio()));
            cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad del artículo:", producto.getCantidad()));
            producto.setMarca(marca);
            producto.setModelo(modelo);
            producto.setPrecio(precio);
            producto.setCantidad(cantidad);
            JOptionPane.showMessageDialog(null, "Artículo modificado con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "Artículo no encontrado.");
        }
    }
// Método para consultar inventario
    public void consultarInventario() {
        guardarArticulo();
        
        if (inventario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El inventario está vacío.");
        } else {
            StringBuilder inventarioStr = new StringBuilder("Inventario:\n");
            for (ArregloParaInventario producto : inventario) {
                inventarioStr.append(producto).append("\n\n");
            }
            JOptionPane.showMessageDialog(null, inventarioStr.toString());
        }
    }

    public ArregloParaInventario buscarProducto(String codigo) {
        for (ArregloParaInventario producto : inventario) {
            if (producto.getCodigo().equalsIgnoreCase(codigo)) {
                return producto;
            }
        }
        return null;
    }
  }




    