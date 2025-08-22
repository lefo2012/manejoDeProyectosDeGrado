/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.Services;


import co.edu.unicauca.Models.Profesor;
import co.edu.unicauca.Repository.ProfesorRepository;
import co.edu.unicauca.Util.Validador;
import co.edu.unicauca.Util.Encriptador;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Lefo
 */
public class ProfesorService {
    

    
    private final ProfesorRepository profesorRepository;

    public ProfesorService(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }
    
    //Hay que tener en cuenta que la decision de retornar una persona es para que cuando el usuario ingrese sea mas personalizada la experiencia y mas facil de acceder a estos datos
    public Profesor iniciarSesion(String correoElectronico,String contrasenia) throws UnsupportedEncodingException
    {
        
        if(!Validador.esCorreoValido("@unicauca.edu.co",correoElectronico))
            return null;
        
        /*EJEMPLO TOMADO DE CHATGPT PREGUNTAR AL PROFESOR DONDE DEBE DE IR LA CLAVE Y EL IV*/
        //fragmento codigo de sqlite uso de personaRepository basicamente.
        Profesor profesor = profesorRepository.buscarPorCorreo(correoElectronico);
         // Clave de 16 caracteres (128 bits)
        if(profesor == null)
        {
            return null;
        }
        
        String clave = "1234567890ABCDEF";  

        // IV de 16 bytes (ejemplo fijo, pero normalmente se genera aleatorio)
        byte[] iv = "abcdefghijklmnop".getBytes("UTF-8");  

        
        if(Encriptador.decriptar(clave, iv, profesor.getContrasenia()).equals(contrasenia))
        {
            return profesor;
        }

        return null;
    }
    public String registrar(Profesor profesor) throws UnsupportedEncodingException
    {
        if(!Validador.esCorreoValido("@unicauca.edu.co",profesor.getCorreoElectronico()))
            return "Correo invalido";
        if(!Validador.esContraseniaCorrecta(profesor.getContrasenia()))
        {
            return "Formato de contrasenia invalido recuerde que debe llevar por lo menos un caracter especial una mayuscula y un digito";
        }
        
        String clave = "1234567890ABCDEF";  

        // IV de 16 bytes (ejemplo fijo, pero normalmente se genera aleatorio)
        byte[] iv = "abcdefghijklmnop".getBytes("UTF-8");  
        
        profesor.setContrasenia(Encriptador.encriptar(clave, iv, profesor.getContrasenia()));
        
        profesorRepository.registrar(profesor);
        return "Registro completado";
    }
}
