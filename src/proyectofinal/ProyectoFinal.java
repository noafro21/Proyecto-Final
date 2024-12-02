package proyectofinal;

public class ProyectoFinal {

    public static void main(String[] args) {

       //MenuPrincipal miMenu = new MenuPrincipal();
      Usuario miValidacion = new Usuario();
   
      Usuario.ingresarDatos ();
      miValidacion.cargaDatosArchivo();
      miValidacion.validarUsuario();
      
      //miInventario.guardarArticulo();
     // miInventario.registrarArticulo();
      
     
      

    }

}
