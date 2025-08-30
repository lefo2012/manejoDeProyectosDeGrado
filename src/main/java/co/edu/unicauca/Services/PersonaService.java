/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.Services;

import co.edu.unicauca.Models.Estudiante;
import co.edu.unicauca.Models.Persona;
import co.edu.unicauca.Repository.PersonaRepository;
import co.edu.unicauca.Util.Encriptador;
import co.edu.unicauca.Util.Validador;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author PixelBot Gaming
 */
public class PersonaService {
    PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }
    
    
    
    public Persona iniciarSesion(String correoElectronico, String contrasenia) throws UnsupportedEncodingException {
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
    public String registrar(Persona estudiante,String cargo) throws UnsupportedEncodingException {
        if (!Validador.esCorreoValido("unicauca.edu.co", estudiante.getCorreoElectronico()))
            return "Correo invalido";

        if (!Validador.esContraseniaCorrecta(estudiante.getContrasenia())) {
            return "Formato de contrasenia invalido recuerde que debe llevar por lo menos un caracter especial una mayuscula y un digito";
        }

        String clave = "1234567890ABCDEF";  
        byte[] iv = "abcdefghijklmnop".getBytes("UTF-8");
        
        estudiante.setContrasenia(Encriptador.encriptar(clave, iv, estudiante.getContrasenia()));
        
        personaRepository.registrar(estudiante,cargo);
        return "Registro completado";
    }
}
