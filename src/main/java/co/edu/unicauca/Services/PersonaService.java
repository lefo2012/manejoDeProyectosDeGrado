/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.Services;

import co.edu.unicauca.Models.Persona;
import co.edu.unicauca.Repository.PersonaRepository;
import co.edu.unicauca.Util.Validador;
import co.edu.unicauca.Util.Encriptador;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Lefo
 */
public class PersonaService {
    

    
    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }
    
    //Hay que tener en cuenta que la decision de retornar una persona es para que cuando el usuario ingrese sea mas personalizada la experiencia y mas facil de acceder a estos datos
    public Persona iniciarSesion(String correoElectronico,String contrasenia) throws UnsupportedEncodingException
    {
        
        if(!Validador.esCorreoValido("@unicauca.edu.co",correoElectronico))
            return null;
        
        
        /*EJEMPLO TOMADO DE CHATGPT PREGUNTAR AL PROFESOR DONDE DEBE DE IR LA CLAVE Y EL IV*/
        //fragmento codigo de sqlite uso de personaRepository basicamente.
        Persona persona = personaRepository.buscarPorCorreo(correoElectronico);
         // Clave de 16 caracteres (128 bits)
        String clave = "1234567890ABCDEF";  

        // IV de 16 bytes (ejemplo fijo, pero normalmente se genera aleatorio)
        byte[] iv = "abcdefghijklmnop".getBytes("UTF-8");  

        
        if(Encriptador.decriptar(clave, iv, persona.getContrasenia()).equals(contrasenia))
        {
            return persona;
        }

        return null;
    }
}
