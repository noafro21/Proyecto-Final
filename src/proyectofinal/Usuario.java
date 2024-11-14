package proyectofinal;

import javax.swing.JOptionPane;

public class Usuario {

    public static ArregloParaUsuario registroUsuario[] = new ArregloParaUsuario[10];

    public void ingresarDatos() {
        registroUsuario[0] = new ArregloParaUsuario("Adonis", "Noa Froemta", "123456789", "admin", Roles.Administrador);
    }

    public void validarUsuario() {
        boolean seguir = true;
        int contador = 0, i, intento;
        String usuario, contrasena;
        MenuPrincipal miAcceso = new MenuPrincipal();

        while (seguir) {

            JOptionPane.showMessageDialog(null, "Bienvenido al Sistema de ventas de ceulares \n"
                    + "Inicie sesión para ingresar");

            usuario = JOptionPane.showInputDialog("Ingrese el usuario:");
            contrasena = JOptionPane.showInputDialog("Ingrese la contraseña:");

            for (i = 0; i < registroUsuario.length; i++) {
                if (registroUsuario[i].cedula.equals(usuario)) {
                    if (registroUsuario[i].password.equals(contrasena)) {
                        miAcceso.menuSistema();
                        seguir = false;
                        break;

                    } else {
                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                    }

                } else {
                    contador++;
                }
            }

            if (contador == registroUsuario.length) {
                JOptionPane.showMessageDialog(null, "Usuario no registrado en el Sistema");
            }

            intento = JOptionPane.showConfirmDialog(null, "¿Desea intentar de nuevo?");

            if (intento == 1 || intento == 2) {
                JOptionPane.showMessageDialog(null, "Se va cerrar el sistema");
                seguir = false;
            }

        }
    }
}
