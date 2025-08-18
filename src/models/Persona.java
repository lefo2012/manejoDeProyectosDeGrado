
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public abstract class Persona
{
    private string nombre;
    private string apellido;
    private string celular;
    private string correoElectronico;
    private string contrasenia;
    private LinkedList<Facultad> facultad;

    public String getContasenia(string contrasenia)
    {
        if(contrasenia.length() < 6)
        {
            
            return "La contraseña tiene que por lo menos tener seis (6) caracteres"; 
        }
        String mayusculas = "[A-Z]";
        Pattern pattern = Pattern.compile(mayusculas);
        Matcher matcher = pattern.matcher(contrasenia);
        if (!matcher.find()) {
            return "la contraseña debe tener al menos una letra mayuscula";
        }
        String caracteresEspeciales = "[^a-zA-Z0-9]";
        pattern = Pattern.compile(caracteresEspeciales);
        matcher = pattern.matcher(contrasenia);
        if (!matcher.find()) {
            return "la contraseña debe tener al menos un caracter especial";
        }
        String digitos = "[0-9]";
        pattern = Pattern.compile(digitos);
        matcher = pattern.matcher(contrasenia);
        if (!matcher.find()) {
            return "la contraseña debe tener al menos un digito";
        }
        return "Contraseña correcta";
    }
    public int getCorreoElectronico(string correoElectronico)
    {
        string unicauca= "unicauca.edu.co";
        String[] palabras = correoElectronico.split("@");
        if(palabras[1].equals(unicauca))
        {
            return 1;
        }
        return 0;
    }

}