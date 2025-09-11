package co.edu.unicauca.Services;

import co.edu.unicauca.Models.Persona;
import co.edu.unicauca.Repository.PersonaRepository;
import co.edu.unicauca.Util.Cargo;
import co.edu.unicauca.Util.Encriptador;
import co.edu.unicauca.Util.Validador;
import java.io.UnsupportedEncodingException;


/**
 *
 * @author LEFO
 */
public class PersonaService {
    PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }
    
    
    
    public Persona iniciarSesion(String correoElectronico, String contrasenia) throws UnsupportedEncodingException, Exception {
        System.out.println(correoElectronico);
        if (!Validador.esCorreoValido("unicauca.edu.co", correoElectronico))
            return null;
        
        Persona persona =  personaRepository.consultarPersonaPorCorreo(correoElectronico);
        
        if (persona == null) {
            return null;
        }

        String clave = "1234567890ABCDEF";  
        byte[] iv = "abcdefghijklmnop".getBytes("UTF-8");

        if (Encriptador.decriptar(clave, iv, persona.getContrasenia()).equals(contrasenia)) {
            return persona;
        }

        return null;
    }
    public String registrar(Persona persona,Cargo cargo) throws UnsupportedEncodingException, Exception {
        if (!Validador.esCorreoValido("unicauca.edu.co", persona.getCorreoElectronico()))
            return "Correo invalido";

        if (!Validador.esContraseniaCorrecta(persona.getContrasenia())) {
            return "Formato de contrasenia invalido recuerde que debe llevar por lo menos un caracter especial una mayuscula y un digito";
        }

        
        
        persona.setContrasenia(Encriptador.encriptar(persona.getContrasenia()));
        
        if(personaRepository.registrar(persona,cargo))
        {
            return "Registro completado";
        }
        
        return "Se encontro un registro con el mismo correo electronico";
    }
}
