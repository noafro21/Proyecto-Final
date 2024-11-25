package proyectofinal;

public class ProyectoFinal {

    public static void main(String[] args) {

      
      Usuario miValidacion = new Usuario();
      Usuario.ingresarDatos ();
      miValidacion.validarUsuario();
      
      MenuPrincipal miMenu = new MenuPrincipal();
        miMenu.menuSistema();

    }

}
