
package co.edu.unicauca.Util;

import java.util.regex.Pattern;
/**
 *
 * @author LEFO
 */
public class Validador {
    
    public static boolean esCorreoValido(String formatoDeCorreo,String correoElectronico)
    {
        
        if(correoElectronico == null)
            return false;

        String[] palabras = correoElectronico.split("@");
        if(palabras.length < 2)
            return false;

        if(palabras[1].equals(formatoDeCorreo))
        {
            return true;
        }
        return false;
    }
    
    public static boolean esContraseniaCorrecta(String contrasenia) {
       if(!esLongitudValida(contrasenia) || !tieneMayuscula(contrasenia) || !tieneCaracterEspecial(contrasenia) || !tieneDigito(contrasenia))
           return false;
        return true;
    }
    
    private static boolean esLongitudValida(String contrasenia) {
        return contrasenia.length() >= 6;
    }

    private static boolean tieneMayuscula(String contrasenia) {
        return Pattern.compile("[A-Z]").matcher(contrasenia).find();
    }

    private static boolean tieneCaracterEspecial(String contrasenia) {
        return Pattern.compile("[^a-zA-Z0-9]").matcher(contrasenia).find();
    }

    private static boolean tieneDigito(String contrasenia) {
        return Pattern.compile("[0-9]").matcher(contrasenia).find();
    }
}
