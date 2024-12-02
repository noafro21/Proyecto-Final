
package proyectofinal;

import javax.swing.JOptionPane;

public class Validaciones {
    
    public static boolean numeroV= true;
    
    public static boolean validarNumeros(String valor)
    {
        if(valor.matches("[0-9]*"))
        {
            numeroV = false;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Por favor digite unicamente números", "Aviso",JOptionPane.WARNING_MESSAGE);
            numeroV = true;
        }
        
        return numeroV;
    }
    
}
