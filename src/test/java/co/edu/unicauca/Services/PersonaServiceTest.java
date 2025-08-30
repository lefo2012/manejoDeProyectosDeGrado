/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package co.edu.unicauca.Services;

import co.edu.unicauca.Models.Estudiante;
import co.edu.unicauca.Models.Persona;
import co.edu.unicauca.Repository.Implementation.RepositoryFactory;
import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author J. Fernando
 * @author LEFO
 */
public class PersonaServiceTest {
    
    @Test
    public void registrarEstudianteValidoTest() throws UnsupportedEncodingException
    {
        Persona persona = new Estudiante();
        persona.setNombre("Pepito");
        persona.setApellido("Perez");
        persona.setCorreoElectronico("peperez@unicauca.edu.co");
        persona.setCelular("3007654321");
        persona.setContrasenia("123ABCabc.");
        
        PersonaService personaService = new PersonaService(RepositoryFactory.getInstance("SQLite"));
        
        String resultado = personaService.registrar(persona,"Estudiante");
        
        assertEquals(resultado,"Registro completado");
        
        
    }
    
    @Test
    public void registrarEstudianteCorreoInvalidoTest() throws UnsupportedEncodingException
    {
        Persona persona = new Estudiante();
        persona.setNombre("Pepito");
        persona.setApellido("Perez");
        persona.setCorreoElectronico("peperez@mail.edu.co");
        persona.setCelular("3007654321");

        persona.setContrasenia("123ABCabc.");
        
        PersonaService personaService = new PersonaService(RepositoryFactory.getInstance("SQLite"));
        
        String resultado = personaService.registrar(persona,"Estudiante");
        assertEquals(resultado,"Correo invalido");
        
        
    }
    
    @Test
     public void registrarEstudianteContraseniaInvalidoTest() throws UnsupportedEncodingException
    {
        Persona persona = new Estudiante();
        persona.setNombre("Pepito");
        persona.setApellido("Perez");
        persona.setCorreoElectronico("peperez@unicauca.edu.co");
        persona.setCelular("3007654321");
        persona.setContrasenia("123ABCabc");
        
        PersonaService personaService = new PersonaService(RepositoryFactory.getInstance("SQLite"));
        
        String resultado = personaService.registrar(persona,"Estudiante");
        assertEquals(resultado,"Formato de contrasenia invalido recuerde que debe llevar por lo menos un caracter especial una mayuscula y un digito");
        
        
    }
    
    
@Test
public void iniciarSesionValidoTest() throws UnsupportedEncodingException
    {
        Persona persona = new Estudiante();
        persona.setCorreoElectronico("peperez@unicauca.edu.co");
        persona.setContrasenia("123ABCabc.");
        
        PersonaService personaService = new PersonaService(RepositoryFactory.getInstance("SQLite"));
        
        persona = personaService.iniciarSesion(persona.getCorreoElectronico(),persona.getContrasenia());
        
        Persona personaEsperada = new Estudiante();
        personaEsperada.setNombre("Pepito");
        personaEsperada.setApellido("Perez");
        personaEsperada.setCorreoElectronico("peperez@unicauca.edu.co");
        personaEsperada.setCelular("3007654321");
        
        //PARA NO TENER QUE VOLVER A ENCRIPTAR SETEO LA MISMA CONTRASENIA EN EL RESULTADO Y EN LA ESPERADA
        persona.setContrasenia("123ABCabc.");
        personaEsperada.setContrasenia("123ABCabc.");
        
        assertEquals(persona,personaEsperada);
        
        
    }    
@Test
public void iniciarSesionInvalidoTest() throws UnsupportedEncodingException
    {
        Persona persona = new Estudiante();
        persona.setCorreoElectronico("peperez@unicauca.edu.co");
        persona.setContrasenia("123ABCab.");
        
        PersonaService personaService = new PersonaService(RepositoryFactory.getInstance("SQLite"));
        
        persona = personaService.iniciarSesion(persona.getCorreoElectronico(),persona.getContrasenia());
        
        assertEquals(persona,null);
        
        
    }    
}
