
package proyectofinal;

public class ArregloParaUsuario {
      public String nombre, apellidos;
    public int cedula;
    public String password;
    public Roles rol;

    public ArregloParaUsuario(String nombre, String apellidos, int cedula, String password, Roles rol) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.password = password;
        this.rol = rol;
    }
    
}
