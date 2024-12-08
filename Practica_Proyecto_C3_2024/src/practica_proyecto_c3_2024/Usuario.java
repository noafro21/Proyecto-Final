package practica_proyecto_c3_2024;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;


public class Usuario {
    
    public static Arreglo_Usuarios RegistroU[] = new Arreglo_Usuarios[10];
    
    public Roles RolAsignado;
    
    public static Roles RolActive;
    
    public void Carga_Datos_Archivo()
    {
        FileReader lectorArchivo;
        try
        {
            lectorArchivo = new FileReader("./src/practica_proyecto_c3_2024/Usuarios_Registrados.txt");
        }
        catch (FileNotFoundException err)
        {
            JOptionPane.showMessageDialog(null, "Archivo no encontrado \n " + err);
            return;
        }
        
        BufferedReader textoArchivo;
        textoArchivo = new BufferedReader(lectorArchivo);
        
        for(int i=0; i<RegistroU.length;i++)
        {
            if(RegistroU[i].Cedula == 0)
            {
                String LineaTxt;
                
                try
                {
                    LineaTxt = textoArchivo.readLine();
                }
                catch(IOException err)
                {
                    JOptionPane.showMessageDialog(null, err);
                    return;
                }
                
                if(LineaTxt == null)
                {
                    break;
                }
                
                String Valores [] = LineaTxt.split(";");
                
                int cedula = Integer.parseInt(Valores[2]);
                
                if(Valores[3].equals("Gerente"))
                {
                    RegistroU[i]= new Arreglo_Usuarios(Valores[0],Valores[1],cedula,Roles.Gerente,Valores[4],Valores[5]);
                }
                
                if(Valores[3].equals("Cajero"))
                {
                    RegistroU[i]= new Arreglo_Usuarios(Valores[0],Valores[1],cedula,Roles.Cajero,Valores[4],Valores[5]);
                }
                
            }
        }
        
        JOptionPane.showMessageDialog(null, "Se registraron los datos del archivo");
        
    }
    
    public void Ingresar_Datos()
    {
        //RegistroU[0] = new Arreglo_Usuarios("Pablo", "Perez Solis", 123456789, Roles.Gerente, "Pablo1234", "admin");
        
        for(int i=0; i<RegistroU.length; i++)
        {
            RegistroU[i] = new Arreglo_Usuarios("Nombre","Apellidos",0,Roles.Sin_Rol,"User","Pass");
        }
    }
    
    public void Validar_Usuario()
    {
        boolean seguir = true;
        
        MenuPrincipal miAcceso = new MenuPrincipal();
        
        while(seguir)
        {
            int contador = 0;
            
            JOptionPane.showMessageDialog(null, "Bienvenido al Sistema TecnoVentas \n"+
                                                "Debe iniciar sesión para ingresar");
            
            String Dato1 = JOptionPane.showInputDialog("Ingrese el usuario:");
            String Dato2 = JOptionPane.showInputDialog("Ingrese la contraseña:");
            
            for(int i=0; i<RegistroU.length; i++)
            {
                if(RegistroU[i].User.equals(Dato1))
                {
                    if(RegistroU[i].Pass.equals(Dato2))
                    {
                        RolActive = RegistroU[i].Rol;
                        miAcceso.Menu_Sistema();
                         
                         break;
                        
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                    }
                   
                }
                else
                {
                    contador++;
                }
            }
            
            if(contador == RegistroU.length)
            {
                JOptionPane.showMessageDialog(null, "Usuario no registrado en el Sistema");
            }
            
            int OtroI = JOptionPane.showConfirmDialog(null, "¿Desea intentar de nuevo?");
            
            if(OtroI == 1 || OtroI == 2)
            {
                JOptionPane.showMessageDialog(null, "Se va cerrar el sistema");
                seguir = false;
            }
            
        }
    }
    
    
    public void Registrar_Usuario()
    {
        boolean seguir = true;
        
        while(seguir)
        {
            int contador = 0;
            
            for(int i=0; i<RegistroU.length; i++)
            {
                if(RegistroU[i].Cedula == 0)
                {
                    JOptionPane.showMessageDialog(null,"Registro Usuario #" +(i+1));
                    
                    String nom = JOptionPane.showInputDialog(null,"Ingrese el nombre:");
                    String ape = JOptionPane.showInputDialog(null,"Ingrese el apellido:");
                    int ced = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese la cédula:"));
                    Roles rol = Asignar_Rol();
                    String user = JOptionPane.showInputDialog(null,"Ingrese el usuario:");
                    String pass = JOptionPane.showInputDialog(null,"Ingrese el contraseña:");
                    RegistroU[i] = new Arreglo_Usuarios(nom, ape, ced, rol, user,pass);
                    
                    int otroR = JOptionPane.showConfirmDialog(null, "¿Desea registrar otro usuario?");
                    
                    if(otroR == 1 || otroR == 2)
                    {
                        seguir = false;
                        break;
                    }
                }
                else
                {
                    contador++;
                }
            }
            
            if(contador == RegistroU.length)
            {
                JOptionPane.showMessageDialog(null, "El registro esta lleno, no se puede registrar mas empleados");
            }
        }
    }
    
    public void Modificar_Usuario()
    {
        boolean seguir = true;
        boolean seguir2 = true;
        
        while(seguir)
        {
            int contador = 0;
            int CedulaC = Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula del usuario a modificar:"));
            
            for(int i=0; i<RegistroU.length; i++)
            {
                if(RegistroU[i].Cedula == CedulaC)
                {
                    while(seguir2)
                    {
                        int opc = Integer.parseInt(JOptionPane.showInputDialog("Seleccione el dato que desea modificar: \n"+
                                                                          "1. Nombre \n"+
                                                                          "2. Apellidos \n"+
                                                                          "3. Cédula \n"+
                                                                          "4. Rol \n"+
                                                                          "5. Usuario \n"+
                                                                          "6. Contraseña"));
                        switch(opc)
                        {
                            case 1:
                                String nombre = JOptionPane.showInputDialog("Ingrese el nombre a modificar: ");
                                RegistroU[i].Nombre = nombre;
                                break;
                            case 2:
                                String apellidos = JOptionPane.showInputDialog("Ingrese los apellidos a modificar: ");
                                RegistroU[i].Apellidos = apellidos;
                                break;
                           case 3:
                                int cedula = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cédula a modificar: "));
                                RegistroU[i].Cedula = cedula;
                                break;
                           case 4:
                               RegistroU[i].Rol = Asignar_Rol();
                               break;
                           case 5:
                                String usuario = JOptionPane.showInputDialog("Ingrese el usuario a modificar: ");
                                RegistroU[i].User = usuario;
                                break;
                           case 6:
                                String pass = JOptionPane.showInputDialog("Ingrese la contraseña a modificar: ");
                                RegistroU[i].Pass = pass;
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
            
            if(contador == RegistroU.length)
            {
              JOptionPane.showMessageDialog(null, "La cédula consultada no pertenece a ningun usuario registrado");  
            }
            
            int intento = JOptionPane.showConfirmDialog(null, "¿Desea modificar la información de otro usuario?");
            if(intento == 1)
            {
                seguir = false;
            }
        }
    }
    
    public void Consultar_Usuario()
    {
        boolean seguir = true;
        
        while(seguir)
        {
            int contador = 0;
            
            int CedulaC = Integer.parseInt(JOptionPane.showInputDialog("Digite la cédula del usuario a consultar"));
            
            for(int i=0; i<RegistroU.length;i++)
            {
                if(RegistroU[i].Cedula == CedulaC)
                {
                    JOptionPane.showMessageDialog(null, "La información del usuario consultado es: \n"+
                                                      "Nombre Completo: " + RegistroU[i].Nombre + " "+RegistroU[i].Apellidos+"\n"+
                                                      "Cédula: " + RegistroU[i].Cedula + "\n"+
                                                      "Departamento: " + RegistroU[i].Rol +"\n"+
                                                      "Usuario: " + RegistroU[i].User +"\n"+
                                                      "Contraseña: " + RegistroU[i].Pass);
                    break;
                }
                else
                {
                    contador++;
                }
            }
            
            if(contador == RegistroU.length)
            {
                JOptionPane.showMessageDialog(null, "La cédula consultada no pertenece a ningun usuario registrado");
            }
            
            int OtroC = JOptionPane.showConfirmDialog(null, "¿Desea consultar otro usuario?");
            
            if(OtroC == 1)
            {
                seguir = false;
            }
        }
    
    }
    
    public Roles Asignar_Rol()
    {
        String[] botones = {"Gerente","Cajero"};
        
        int RolS = JOptionPane.showOptionDialog(null,"Seleccione el rol del usuario:",
                                                      "Asignación Rol",JOptionPane.DEFAULT_OPTION,
                                                      JOptionPane.QUESTION_MESSAGE,null,botones,botones[0]);
        
        switch (RolS) 
        {
            case 0:
                RolAsignado = Roles.Gerente;
                break;
            case 1:
                RolAsignado = Roles.Cajero;
                break;
            
                
        }
        
        return RolAsignado;
        
    }
    
}
