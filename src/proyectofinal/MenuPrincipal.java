package proyectofinal;

import javax.swing.JOptionPane;

public class MenuPrincipal {

//Menú principal del sistema
    public static void menuSistema() {
        boolean seguir = true;
        String opcS;

        while (Validaciones.numeroV) {
            while (seguir) {
                opcS = JOptionPane.showInputDialog(null, "Seleccione la opcion: \n"
                        + "1. Control Inventario \n"
                        + "2. Control Ventas \n"
                        + "3. Control Usuario \n"
                        + "4. Salir ", " Menú principal del sistema", JOptionPane.INFORMATION_MESSAGE);

                Validaciones.validarNumeros(opcS);
                switch (opcS) {
                    case "1":
                        menuControlInventario();
                        break;
                    case "2":
                        Ventas.mostrarMenu();
                        break;
                    case "3":
                        if (Usuario.rolActive == Roles.Administrador) {
                            menuControlUsuario();
                        } else {
                            JOptionPane.showMessageDialog(null, "No tiene los permisos para acceder a este modulo.", "Acceso Restringido", JOptionPane.ERROR_MESSAGE);
                        }
                        break;
                    case "4":
                        seguir = false;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Ingrese una opcion valida", "Error de ingreso", JOptionPane.ERROR_MESSAGE);
                        Validaciones.numeroV = true;
                        break;
                }
            }
        }
    }

    //Menú que controla el Inventario
    public static void menuControlInventario() {
        Inventario registrar = new Inventario();
        boolean seguir = true;
        String opcI;

        while (seguir) {
            opcI = JOptionPane.showInputDialog(null, "Seleccione la opción:\n"
                    + "1. Registrar Producto\n"
                    + "2. Modificar Producto\n"
                    + "3. Consultar Inventario\n"
                    + "4. Regresar");
            switch (opcI) {
                case "1":
                    registrar.registrarArticulo();
                    break;
                case "2":
                    registrar.modificarArticulo();
                    break;
                case "3":
                    registrar.consultarInventario();
                    break;
                case "4":
                    seguir = false;
                    //JOptionPane.showMessageDialog(null, "Regresando al menú principal");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Intente de nuevo.");
                    break;
            }
        }
    }

    //Menú que controla las acciones que se le realizan al usuario
    public static void menuControlUsuario() {
        boolean seguirU = true;
        String opcU;

        while (seguirU) {
            opcU = JOptionPane.showInputDialog(null, "Seleccione la opcion: \n"
                    + "1. Registrar Usuario \n"
                    + "2. Modificar Usuario \n"
                    + "3. Consultar Usuario \n"
                    + "4. Regresar ");
            switch (opcU) {
                case "1":
                    Usuario.registrarUsuario();
                    break;
                case "2":
                    Usuario.modificarDatoDelUsuario();
                    break;
                case "3":
                    Usuario.consultarUsuario();
                    break;
                case "4":
                    seguirU = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Ingrese una opcion valida", "Error de ingreso", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
    }
}
