package proyectofinal;

import javax.swing.JOptionPane;

public class MenuPrincipal {

    Inventario registrar = new Inventario();

    public void menuSistema() {

        int opcS = 1, intentoS;

        while (opcS != 4) {
            opcS = Integer.parseInt(JOptionPane.showInputDialog(null, "Seleccione la opcion: \n1. Control Inventario \n2. Control Ventas \n3. Control Usuario \n4. Salir ", " Menú principal del sistema", JOptionPane.INFORMATION_MESSAGE));

            switch (opcS) {
                case 1:
                    registrar.guardarArticulo();
                    menuControlInventario();
                    break;
                case 2:
                    //Ventas.mostrarMenu();
                    break;
                case 3:

                    if (Usuario.rolActive == Roles.Administrador) {
                        Usuario.ingresarDatos();
                        menuControlUsuario();
                        //opcS=4;

                    } else {
                        JOptionPane.showMessageDialog(null, "No tiene los permisos para acceder a este modulo.", "Error de ingreso", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 4:
                    intentoS = (JOptionPane.showConfirmDialog(null, "Está seguro que dese cerrar el sistema", "Cuidado", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE));
                    if (intentoS == 0) {
                        opcS = 4;

                    } else if (intentoS == 1) {
                        opcS = 1;
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Ingrese una opcion valida", "Error de ingreso", JOptionPane.ERROR_MESSAGE);
                    break;

            }
        }
    }

    public static void menuControlUsuario() {
        boolean seguirU = true;
        int opcU;

        while (seguirU) {
            opcU = Integer.parseInt(JOptionPane.showInputDialog(null, "Seleccione la opcion: \n"
                    + "1. Registrar Usuario \n"
                    + "2. Modificar Usuario \n"
                    + "3. Consultar Usuario \n"
                    + "4. Regresar "));

            switch (opcU) {
                case 1:

                    Usuario.registrarUsuario();
                    break;
                case 2:
                    Usuario.modificarDatoDelUsuario();
                    break;
                case 3:
                    Usuario.consultarUsuario();
                    break;
                case 4:
                    seguirU = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Ingrese una opcion valida", "Error de ingreso", JOptionPane.ERROR_MESSAGE);
                    break;

            }
        }
    }

    public void menuControlInventario() {

        boolean seguir = true;
        while (seguir) {
            String opc = JOptionPane.showInputDialog(null, "Seleccione la opción:\n" + "1. Registrar Producto\n" + "2. Modificar Producto\n" + "3. Consultar Inventario\n" + "4. Regresar");
            switch (opc) {
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
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida. Intente de nuevo.");
                    break;
            }
        }
    }
}
