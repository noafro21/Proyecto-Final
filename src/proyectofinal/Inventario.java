
package proyectofinal;


import java.util.ArrayList;
import javax.swing.JOptionPane;
public class Inventario {

public  ArrayList<ArregloParaInventario> inventario = new ArrayList<>();
    
    
     public void guardarArticulo() { 
// Inicializar 5 productos de Celulares 
inventario.add(new ArregloParaInventario("COD-CEL-001", "Samsung", "S9", 800.00, 20));
inventario.add(new ArregloParaInventario("COD-CEL-002", "Samsung", "S24Ultra", 1200.00, 15));
inventario.add(new ArregloParaInventario("COD-CEL-003", "iPhone", "11", 1000.00, 10));
inventario.add(new ArregloParaInventario("COD-CEL-004", "iPhone", "12", 1100.00, 12));
inventario.add(new ArregloParaInventario("COD-CEL-005", "Google", "Pixel 7", 900.00, 18)); 
// Inicializar 5 productos de Accesorios
inventario.add(new ArregloParaInventario("COD-ACC-001", "Samsung", "GalaxyBuds", 150.00, 30));
inventario.add(new ArregloParaInventario("COD-ACC-002", "iPhone", "EarPods", 100.00, 25));
inventario.add(new ArregloParaInventario("COD-ACC-003", "JBL", "Headphones", 200.00, 20));
inventario.add(new ArregloParaInventario("COD-ACC-004", "Belkin", "Cargador", 50.00, 40)); 
inventario.add(new ArregloParaInventario("COD-ACC-005", "Spigen", "Protector de Pantalla", 25.00, 50));
     }


    public  void registrarArticulo() {
        String codigo = JOptionPane.showInputDialog("Ingrese el código del artículo (ej: COD-ACC-201):");
        String marca = JOptionPane.showInputDialog("Ingrese la marca del artículo:");
        String modelo = JOptionPane.showInputDialog("Ingrese el modelo del artículo:");
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del artículo:"));
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad del artículo:"));
        inventario.add(new ArregloParaInventario(codigo, marca, modelo, precio, cantidad));
        JOptionPane.showMessageDialog(null, "Artículo registrado con éxito.");
    }

    public void modificarArticulo() {
        String codigo = JOptionPane.showInputDialog("Ingrese el código del artículo a modificar:");
        ArregloParaInventario producto = buscarProducto(codigo);
        if (producto != null) {
            String marca = JOptionPane.showInputDialog("Ingrese la nueva marca del artículo:", producto.getMarca());
            String modelo = JOptionPane.showInputDialog("Ingrese el nuevo modelo del artículo:", producto.getModelo());
            double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo precio del artículo:", producto.getPrecio()));
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad del artículo:", producto.getCantidad()));
            producto.setMarca(marca);
            producto.setModelo(modelo);
            producto.setPrecio(precio);
            producto.setCantidad(cantidad);
            JOptionPane.showMessageDialog(null, "Artículo modificado con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "Artículo no encontrado.");
        }
    }

    public void consultarInventario() {
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




    /*public void registrarArticulo() {
        boolean seguir = true;
        String pArticulo, pCodigo, pMarca, pModelo;
        double pPrecio;
        int pCantidad;

        while (seguir) {
            int contador = 0;

            for (int i = 0; i < totalDeArticulos.length; i++) {
                if (totalDeArticulos[i].cantidad==0) {
                    
                    JOptionPane.showMessageDialog(null, "Por favor registre el articulo deseado");
                    Area tipo = asignarArea();
                    pArticulo = JOptionPane.showInputDialog(null, "Ingrese el tipo de artículo:");
                    pCodigo = JOptionPane.showInputDialog(null, "Ingrese el código del articulo(Ej:COD-ACC-201):");
                    pMarca = JOptionPane.showInputDialog(null, "Ingrese la marca:");
                    pModelo = JOptionPane.showInputDialog(null, "Ingrese el modelo:");
                    pPrecio = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el precio del artículo:"));
                    pCantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese cantidad disponible:"));

                    totalDeArticulos[i] = new ArregloParaInventario(tipo, pArticulo, pCodigo, pMarca, pModelo, pPrecio, pCantidad);

                    int otroR = JOptionPane.showConfirmDialog(null, "¿Desea registrar otro usuario?");

                    if (otroR == 1 || otroR == 2) {
                        seguir = false;
                        break;
                    }
                } else {
                    contador++;
                }
            }

            if (contador == totalDeArticulos.length) {
                JOptionPane.showMessageDialog(null, "El registro esta lleno, no se puede registrar mas empleados");
            }
        }
    }*/


   /* public  Area asignarArea() {
        String[] botones = {"Celurares", "Accesorios"};

        int tipoA = JOptionPane.showOptionDialog(null, "Seleccione el área del artículo:",
                "Asignación de area", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, botones, botones[0]);

        switch (tipoA) {
            case 0:
                areaAsignada = Area.Celulares;
                break;
            case 1:
                areaAsignada = Area.Accesorios;
                break;

        }

        return areaAsignada;

    }
    
   /* public void modificarArticulo()
    {
        
        boolean seguir = true;
        boolean seguir2 = true;
       String pArticulo, pCodigo, pMarca, pModelo;
        double pPrecio;
        int pCantidad, opc,contador,i;
        
        while(seguir)
        {
           contador = 0;
            pCodigo = JOptionPane.showInputDialog("Digite el código del articulo al que desea modificarle información\n(Ej:COD-ACC-201):");
            
            for( i=0; i<totalDeArticulos.length; i++)
            {
                if(totalDeArticulos[i] != null &&totalDeArticulos[i].codigo.equals(pCodigo))
                     
                {
                    while(seguir2)
                    {
                        String[] opciones ={"Area","Artículo","Código\n(Ej:COD-ACC-201)","Marca","Modelo","Precio","Cantidad"};
 opc = JOptionPane.showOptionDialog(null, "¿Qué artícolo desea modificar?", "Seleccione la opcion a modificar", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, new ImageIcon("C:\\Users\\adonf\\Desktop\\ProyectoFinal\\src\\proyectofinal\\img\\menu.png"), opciones, opciones[0]);
                        
                       opc = Integer.parseInt(JOptionPane.showInputDialog("Seleccione la información que desea modificar: \n"+
                                                                          "1. Area \n"+
                                                                          "2. Artículo \n"+
                                                                          "3. Código(Ej:COD-ACC-201) \n"+
                                                                          "4. Marca \n"+
                                                                          "5. Modelo \n"+
                                                                          "6. Precio \n"+
                                                                          "7. Cantidad"));
                        switch(opc)
                        {
                            case 0:
                                totalDeArticulos[i].tipo = asignarArea();
                                break;
                            case 1:
                                pArticulo = JOptionPane.showInputDialog("Ingrese el tipo de articulo correcto: ");
                                totalDeArticulos[i].articulos = pArticulo;
                                break;
                           case 2:
                                pCodigo = JOptionPane.showInputDialog("Ingrese el codigo correcto: ");
                                totalDeArticulos[i].codigo = pCodigo;
                                break;
                           case 3:
                              pMarca = JOptionPane.showInputDialog("Ingrese el nombre de la marca que desea modificar: ");
                                totalDeArticulos[i].marca = pMarca;
                               break;
                           case 4:
                               pModelo = JOptionPane.showInputDialog("Ingrese el modelos que desea modificar: ");
                                totalDeArticulos[i].modelo = pModelo;
                                break;
                           case 5:
                             pPrecio = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el precio del artículo al que desea modificar:"));
                                totalDeArticulos[i].precio = pPrecio;
                              
                                break;     
                                 case 6:
                             pCantidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cantidad de artículo disponible:"));
                            
                           totalDeArticulos[i].cantidad = pCantidad;
                         
                           
        
                                break;     
                           default:
                                JOptionPane.showMessageDialog(null, "Seleccione una opción correcta");
                                break; 
                        }
                        
                        int OtroM = JOptionPane.showConfirmDialog(null, "¿Desea modificar más información del usuario?");
                        if(OtroM == 1)
                        {
                            seguir2 = false;
                        }
                    }
                }
                else
                {
                    contador++;
                }
            }
            
            if(contador ==totalDeArticulos.length)
            {
              JOptionPane.showMessageDialog(null, "La cédula consultada no pertenece a ningun usuario registrado");  
            }
            
            int intento = JOptionPane.showConfirmDialog(null, "¿Desea modificar la información de otro usuario?");
            if(intento == 1)
            {
                seguir = false;
            }
        }
    }*/
    
     /*public static void consultarArticulo() {

        String codigoConsulta;
        boolean seguir = true;
        int consulta, i, contador = 0;

        while (seguir) {

            codigoConsulta = JOptionPane.showInputDialog(null, "Ingrese el codigo del articulo que desea consultar:");

            for (i = 0; i < totalDeArticulos.length; i++) {
                if (totalDeArticulos[i].codigo.equals(codigoConsulta)) {

                    JOptionPane.showMessageDialog(null, "Disponibilidad de artículo consultado" +"\nArea:  " + totalDeArticulos[i].tipo + "\nArtículo: " +totalDeArticulos[i].articulos + "\nCódigo: " +totalDeArticulos[i].codigo + " \nMarca: " +totalDeArticulos[i].marca + "\nModelo: " +totalDeArticulos[i].modelo+ "\nPrecio: " +totalDeArticulos[i].precio+ "\nDisponibles: " +totalDeArticulos[i].cantidad);
                    break;

                } else {
                    contador++;
                }

            }
            if (contador == totalDeArticulos.length) {
                JOptionPane.showMessageDialog(null, "El atriculo que desea ingresar no esta en inventario");

            }
            consulta = JOptionPane.showConfirmDialog(null, "¿Desea consultar por otro artícilo?");
            if (consulta == 1 || consulta == 2) {
                seguir = false;

            }

        }

    }*/

