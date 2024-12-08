package proyectofinal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Usuario {

    public static ArregloParaUsuario registroUsuario[] = new ArregloParaUsuario[10];
    public static Roles rolAsignado;
    public static Roles rolActive;

    @SuppressWarnings("resource")
    public void cargaDatosArchivo() {
ImageIcon miRegistro = new ImageIcon("src\\proyectofinal\\img\\registro.png");
        FileReader lectorArchivo;
        try {
            lectorArchivo = new FileReader("./src/proyectofinal/ListaDeUsuarios.txt");
        } catch (FileNotFoundException err) {
            JOptionPane.showMessageDialog(null, "Archivo no encontrado \n " + err);
            return;
        }

        BufferedReader textoArchivo;
        textoArchivo = new BufferedReader(lectorArchivo);

        for (int i = 0; i < registroUsuario.length; i++) {
            if (registroUsuario[i].cedula == 0) {
                String LineaTxt;

                try {
                    LineaTxt = textoArchivo.readLine();
                } catch (IOException err) {
                    JOptionPane.showMessageDialog(null, err);
                    return;
                }

                if (LineaTxt == null) {
                    break;
                }

                String Valores[] = LineaTxt.split(";");

                int cedula;
                cedula = Integer.parseInt(Valores[2]);

                if (Valores[4].equals("Administrador")) {
                    registroUsuario[i] = new ArregloParaUsuario(Valores[0], Valores[1], cedula, Valores[3], Roles.Administrador);
                }

                if (Valores[4].equals("Trabajador")) {
                    registroUsuario[i] = new ArregloParaUsuario(Valores[0], Valores[1], cedula, Valores[3], Roles.Trabajador);
                }

            }
        }

        JOptionPane.showMessageDialog(null, "Se registraron los datos del archivo\n","Registro de Usuario",1,miRegistro);

    }

    public static void ingresarDatos() {
        int i;
        for (i = 0; i < registroUsuario.length; i++) {
            registroUsuario[i] = new ArregloParaUsuario("Nombre", "Apellidos", 0, "Contraseña", Roles.Sin_Rol);
        }
    }

    public void validarUsuario() {
        ImageIcon miIcons = new ImageIcon("src\\proyectofinal\\img\\home.png");

        int intentoU, i, contador = 0, usuario;
        boolean seguir = true;
        String contrasena;

        while (seguir) {
            JOptionPane.showMessageDialog(null, "Bienvenido a la tienda Virtual\n          ACCELVAROF", "Hola", 1, miIcons);
            usuario = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese su cédula (Sin Guiones)", "Acceso al Sistema", JOptionPane.QUESTION_MESSAGE));
            contrasena = JOptionPane.showInputDialog(null, "Ingrese su contraseña", "Acceso al Sistema", JOptionPane.QUESTION_MESSAGE);

            for (i = 0; i < registroUsuario.length; i++) {
                if (registroUsuario[i].cedula == usuario) {
                    if (registroUsuario[i].password.equals(contrasena)) {
                        rolActive = registroUsuario[i].rol;
                        MenuPrincipal.menuSistema();
                        break;

                    } else {
                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Atención", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    contador++;

                }
            }

            if (contador == registroUsuario.length) {
                JOptionPane.showMessageDialog(null, "Usuario no registrado en el Sistema", "Atención", JOptionPane.ERROR_MESSAGE);
            }
            intentoU = (JOptionPane.showConfirmDialog(null, "¿Desea intentarlo de nuevo?", "Atención", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE));
            if (intentoU == 1 || intentoU == 2) {
                JOptionPane.showMessageDialog(null, "Se va cerrar el sistema", "Atención", JOptionPane.WARNING_MESSAGE);
                seguir = false;
            }

        }

    }

    public static void registrarUsuario() {
ImageIcon miUser = new ImageIcon("src\\proyectofinal\\img\\user.png");
//Atributos de l método
        int registrar, i, contador = 0, cedula1;
        boolean seguir = true;
        String nombre1, apellidos1, password1;
        Roles rol;

        while (seguir) {

            for (i = 2; i < registroUsuario.length; i++) {
                if (registroUsuario[i].cedula == 0) {

                    JOptionPane.showMessageDialog(null, "Ingrese al usuario # "+ (i + 1),"Datos del Usuario", 1,miUser);
                    nombre1 = JOptionPane.showInputDialog(null, "Ingrese el nombre del usuario:","Datos del Usuario",JOptionPane.INFORMATION_MESSAGE);
                    apellidos1 = JOptionPane.showInputDialog(null, "Ingrese los apellidos","Datos del Usuario",JOptionPane.INFORMATION_MESSAGE);
                    cedula1 = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrase el número de cédula:","Datos del Usuario",JOptionPane.INFORMATION_MESSAGE));
                    password1 = JOptionPane.showInputDialog(null, "Ingrase la contraseña:","Datos del Usuario",JOptionPane.INFORMATION_MESSAGE);
                    rol = asignarRol();
                    registroUsuario[i] = new ArregloParaUsuario(nombre1, apellidos1, cedula1, password1, rol);

                    registrar = JOptionPane.showConfirmDialog(null, "¿Desea registrar otro usuario","Nuevo Ingreso", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                    if (registrar == 1 || registrar == 2) {
                        seguir = false;
                        break;
                    }
                } else {
                    contador++;
                }
            }

            if (contador == registroUsuario.length) {
                JOptionPane.showMessageDialog(null, "El registro esta lleno, no se puede registrar mas empleados","Atención", JOptionPane.WARNING_MESSAGE);
            }
        }

    }

    public static Roles asignarRol() {
        int rolA;
        
        String[] opciones = {"Administrador", "Trabajador"};

        rolA = JOptionPane.showOptionDialog(null, "Seleccione el rol del usuario:",
                "Asignación Rol",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.OK_CANCEL_OPTION, null, opciones, opciones[0]);
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
        int opcionM, confirmar2, consulta1, i, contador = 0, cedulaM;
        String nombreM, apellidosM, contrasenaM;

        while (seguir1) {
            cedulaM = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese la cédula del usuario: ","Modificar datos" ,JOptionPane.WARNING_MESSAGE));
            for (i = 0; i < registroUsuario.length; i++) {
                if (registroUsuario[i].cedula == cedulaM) {
                    while (seguir2) {
                        opcionM = Integer.parseInt(JOptionPane.showInputDialog(null, "Seleccione el dato que desea Modificar: \n1. Nombre \n2. Apellidos  \n3. Cédula \n4. Contraseña \n5. Rol  ","Modificar datos" ,JOptionPane.WARNING_MESSAGE));
                        switch (opcionM) {
                            case 1:
                                nombreM = JOptionPane.showInputDialog(null,"Ingrese el nombre a modificar:","Modificar datos" ,JOptionPane.WARNING_MESSAGE);
                                registroUsuario[i].nombre = nombreM;
                                break;
                            case 2:
                                apellidosM = JOptionPane.showInputDialog(null,"Ingrese los apellidos modificar:","Modificar datos" ,JOptionPane.WARNING_MESSAGE);
                                registroUsuario[i].apellidos = apellidosM;
                                break;
                            case 3:
                                cedulaM = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese la cédula a modificar:","Modificar datos" ,JOptionPane.WARNING_MESSAGE));
                                registroUsuario[i].cedula = cedulaM;
                                break;
                            case 4:
                                contrasenaM = JOptionPane.showInputDialog(null,"Ingrese la nueva contraseña:","Modificar datos" ,JOptionPane.WARNING_MESSAGE);
                                registroUsuario[i].password = contrasenaM;
                                break;
                            case 5:
                                registroUsuario[i].rol = asignarRol();
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Ingrese una opcion valida","Atención" ,JOptionPane.ERROR_MESSAGE);
                                break;
                        }
                        confirmar2 = JOptionPane.showConfirmDialog(null, "¿Desea modificar otro dato?","Modificar nuevo dato" ,JOptionPane.WARNING_MESSAGE);

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
                JOptionPane.showMessageDialog(null, "La cédula que desea consultar no se encuentar registrada en el sistema.","Atención" ,JOptionPane.ERROR_MESSAGE);
            }
            consulta1 = JOptionPane.showConfirmDialog(null, "¿Desea modificar  otro usuario?","Atención" ,JOptionPane.WARNING_MESSAGE);
            if (consulta1 == 1 || consulta1 == 2) {
                seguir1 = false;
            }
        }
    }

    public static void consultarUsuario() {
ImageIcon miUser = new ImageIcon("src\\proyectofinal\\img\\user.png");
        boolean seguir = true;
        int consulta, i, contador = 0, cedulaConsult;

        while (seguir) {

            cedulaConsult = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la cédula del usuario que desea consultar:","Consulta de Usuario",JOptionPane.WARNING_MESSAGE));

            for (i = 0; i < registroUsuario.length; i++) {
                if (registroUsuario[i].cedula == cedulaConsult) {

                    JOptionPane.showMessageDialog(null, "Datos del usuario consultado " + (i + 1) + "\nNombre:  " + registroUsuario[i].nombre + "\nApellidos: " + registroUsuario[i].apellidos + "\nCédula: " + registroUsuario[i].cedula + " \nContraseña: " + registroUsuario[i].password + "\n Rol: " + registroUsuario[i].rol,"Consulta de Usuario",1,miUser);
                    break;

                } else {
                    contador++;
                }

            }
            if (contador == registroUsuario.length) {
                JOptionPane.showMessageDialog(null, "El usuario que desea consultar no ha sido ingresado","Atención",JOptionPane.WARNING_MESSAGE);

            }
            consulta = JOptionPane.showConfirmDialog(null, "¿Desea consultar por otro usuario?","Atención",JOptionPane.WARNING_MESSAGE);
            if (consulta == 1 || consulta == 2) {
                seguir = false;

            }

        }

    }
}
