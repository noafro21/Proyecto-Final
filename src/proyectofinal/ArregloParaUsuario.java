
package proyectofinal;

public class ArregloParaUsuario {
      public String nombre, apellidos;
    public String cedula;
    public String password;
    public Roles rol;

    public ArregloParaUsuario(String nombre, String apellidos, String cedula, String password, Roles rol) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.password = password;
        this.rol = rol;
    }
    
}
