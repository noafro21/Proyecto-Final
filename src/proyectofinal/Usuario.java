package proyectofinal;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Usuario {
MenuPrincipal miMenuSistema = new MenuPrincipal ();
    public static ArregloParaUsuario registroUsuario[] = new ArregloParaUsuario[10];
    public static Roles rolAsignado;
    public static Roles rolActive;

    public static void ingresarDatos() {
        int i;
        registroUsuario[0] = new ArregloParaUsuario("Adonis", "Noa Frometa", "123456789", "123*", Roles.Administrador);
        registroUsuario[1] = new ArregloParaUsuario("Joerge", "Roman Martínez", "223456789", "123*", Roles.Trabajador);

        for (i = 2; i < registroUsuario.length; i++) {
            registroUsuario[i] = new ArregloParaUsuario("Nombre", "Apellidos", "Cédula", "Contraseña", Roles.Sin_Rol);
        }
    }

    public  void validarUsuario() {

        int intentoU, i, contador = 0;
        boolean seguir = true;
        String usuario, contrasena;
        while (seguir) {
            JOptionPane.showMessageDialog(null, "Bienvenido al Sistema de ventas de ceulares \n", "Hola", 0, new ImageIcon("C:\\Users\\adonf\\Desktop\\ProyectoFinal\\src\\proyectofinal\\img\\home.png"));
            usuario = JOptionPane.showInputDialog(null, "Ingrese su cédula", "Usuario", JOptionPane.QUESTION_MESSAGE);
            contrasena = JOptionPane.showInputDialog(null, "Ingrese su contraseña", "Usuario", JOptionPane.QUESTION_MESSAGE);

            for (i = 0; i < registroUsuario.length; i++) {
                if (registroUsuario[i].cedula.equals(usuario)) {
                    if (registroUsuario[i].password.equals(contrasena)) {
                        rolActive = registroUsuario[i].rol;
                        miMenuSistema.menuSistema();
                        break;

                    } else {
                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta","Atención",JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    contador++;

                }
            }

            if (contador == registroUsuario.length) {
                JOptionPane.showMessageDialog(null, "Usuario no registrado en el Sistema","Atención",JOptionPane.ERROR_MESSAGE);
            }
            intentoU = (JOptionPane.showConfirmDialog(null, "¿Desea intentarlo de nuevo?", "Atención",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE));
            if (intentoU == 0) {
                seguir = true;

            } else if (intentoU == 1) {
                seguir = false;
            }

        }

    }

    public static void registrarUsuario() {

//Atributos de l método
        int registrar, i, contador = 0;
        boolean seguir = true;
        String nombre1, apellidos1, cedula1, password1;
        Roles rol;

        while (seguir) {

            for (i = 0; i < registroUsuario.length; i++) {
                if (registroUsuario[i].cedula.equals("123456789")) {

                    JOptionPane.showMessageDialog(null, "Registre al usuario # " + (i + 1));
                    nombre1 = JOptionPane.showInputDialog(null, "Ingrese el numbre del usuario:");
                    apellidos1 = JOptionPane.showInputDialog(null, "Ingrese los apellidos");
                    cedula1 = JOptionPane.showInputDialog(null, "Ingrase el número de cédula:");
                    password1 = JOptionPane.showInputDialog(null, "Ingrase la contraseña:");
                    rol = asignarRol();
                    registroUsuario[i] = new ArregloParaUsuario(nombre1, apellidos1, cedula1, password1, rol);

                    registrar = JOptionPane.showConfirmDialog(null, "¿Desea registrar otro usuario");

                    if (registrar == 1 || registrar == 2) {
                        seguir = false;
                        break;
                    }
                } else {
                    contador++;
                }
            }

            if (contador == registroUsuario.length) {
                JOptionPane.showMessageDialog(null, "El registro esta lleno, no se puede registrar mas empleados");
            }
        }

    }

    public static Roles asignarRol() {
        int rolA;

        String[] opciones = {"Administrador", "Trabajador"};

        rolA = JOptionPane.showOptionDialog(null, "Seleccione el rol del usuario:",
                "Asignación Rol", JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        switch (rolA) {
            case 0:
                rolAsignado = Roles.Administrador;
                break;
            case 1:
                rolAsignado = Roles.Trabajador;
                break;
        }
        return rolAsignado;

    }

    public static void modificarDatoDelUsuario() {
        boolean seguir1 = true, seguir2 = true;
        int opcionM, confirmar2, consulta1, i, contador = 0;
        String nombreM, apellidosM, cedulaM, contrasenaM;

        while (seguir1) {
            cedulaM = JOptionPane.showInputDialog("Ingrese la cédula del usuario: ");
            for (i = 0; i < registroUsuario.length; i++) {
                if (registroUsuario[i].cedula.equals(cedulaM)) {
                    while (seguir2) {
                        opcionM = Integer.parseInt(JOptionPane.showInputDialog(null, "Seleccione el dato que desea Modificar: \n1. Nombre \n2. Apellidos  \n3. Cédula \n4. Contraseña \n5. Rol  "));
                        switch (opcionM) {
                            case 1:
                                nombreM = JOptionPane.showInputDialog("Ingrese el nombre a modificar:");
                                registroUsuario[i].nombre = nombreM;
                                break;
                            case 2:
                                apellidosM = JOptionPane.showInputDialog("Ingrese los apellidos modificar:");
                                registroUsuario[i].apellidos = apellidosM;
                                break;
                            case 3:
                                cedulaM = JOptionPane.showInputDialog("Ingrese la cédula a modificar:");
                                registroUsuario[i].cedula = cedulaM;
                                break;
                            case 4:
                                contrasenaM = JOptionPane.showInputDialog("Ingrese la nueva contraseña:");
                                registroUsuario[i].password = contrasenaM;
                                break;
                            case 5:
                                registroUsuario[i].rol = asignarRol();
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Ingrese una opcion valida");
                                break;
                        }
                        confirmar2 = JOptionPane.showConfirmDialog(null, "¿Desea modificar otro dato?");

                        if (confirmar2 == 1 || confirmar2 == 2) {
                            seguir2 = true;
                            break;
                        }
                    }
                    break;
                } else {
                    contador++;
                }
            }
            if (contador == registroUsuario.length) {
                JOptionPane.showMessageDialog(null, "La cédula que desea consultar no se encuentar registrada en el sistema.");
            }
            consulta1 = JOptionPane.showConfirmDialog(null, "¿Desea modificar  otro usuario?");
            if (consulta1 == 1 || consulta1 == 2) {
                seguir1 = false;
            }
        }
    }

    public static void consultarUsuario() {

        String cedulaCons;
        boolean seguir = true;
        int consulta, i, contador = 0;

        while (seguir) {

            cedulaCons = JOptionPane.showInputDialog(null, "Ingrese la cédula del usuario que desea consultar:");

            for (i = 0; i < registroUsuario.length; i++) {
                if (registroUsuario[i].cedula.equals(cedulaCons)) {

                    JOptionPane.showMessageDialog(null, "El vehículo se encuentra en el:\nEstacionamiento # " + (i + 1) + "\nNombre:  " + registroUsuario[i].nombre + "\nApellidos: " + registroUsuario[i].apellidos + "\nCédula: " + registroUsuario[i].cedula + " \nContraseña: " + registroUsuario[i].password + "\n Rol: " + registroUsuario[i].rol);
                    break;

                } else {
                    contador++;
                }

            }
            if (contador == registroUsuario.length) {
                JOptionPane.showMessageDialog(null, "El usuario que desea consultar no ha sido ingresado");

            }
            consulta = JOptionPane.showConfirmDialog(null, "¿Desea consultar por otro usuario?");
            if (consulta == 1 || consulta == 2) {
                seguir = false;

            }

        }

    }
}
