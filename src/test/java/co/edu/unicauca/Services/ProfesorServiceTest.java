/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package co.edu.unicauca.Services;

import co.edu.unicauca.Services.PersonaServiceFactory;
import co.edu.unicauca.Models.Persona;
import co.edu.unicauca.Models.Profesor;
import java.io.UnsupportedEncodingException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author J. Fernando
 */
public class ProfesorServiceTest {
    
    public ProfesorServiceTest() {
    }
    


    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void registrarEstudianteValidoTest() throws UnsupportedEncodingException
    {
        Persona persona = new Profesor();
        persona.setNombre("Pepito");
        persona.setApellido("Perez");
        persona.setCorreoElectronico("peperez@unicauca.edu.co");
        persona.setCelular("3007654321");
        persona.setContrasenia("123ABCabc.");
        
        
        PersonaService service = PersonaServiceFactory.getService(persona);
        String resultado  = service.registrar(persona);
        assertEquals(resultado,"Registro completado");
        
        
    }
    
    @Test
    public void registrarEstudianteCorreoInvalidoTest() throws UnsupportedEncodingException
    {
        Persona persona = new Profesor();
        persona.setNombre("Pepito");
        persona.setApellido("Perez");
        persona.setCorreoElectronico("peperez@mail.edu.co");
        persona.setCelular("3007654321");

        persona.setContrasenia("123ABCabc.");
        
        PersonaService service = PersonaServiceFactory.getService(persona);
        String resultado  = service.registrar(persona);
        assertEquals(resultado,"Correo invalido");
        
        
    }
    
    @Test
     public void registrarEstudianteContraseniaInvalidoTest() throws UnsupportedEncodingException
    {
        Persona persona = new Profesor();
        persona.setNombre("Pepito");
        persona.setApellido("Perez");
        persona.setCorreoElectronico("peperez@unicauca.edu.co");
        persona.setCelular("3007654321");

        persona.setContrasenia("123ABCabc");
        
        PersonaService service = PersonaServiceFactory.getService(persona);
        String resultado  = service.registrar(persona);
        assertEquals(resultado,"Formato de contrasenia invalido recuerde que debe llevar por lo menos un caracter especial una mayuscula y un digito");
        
        
    }
    
    
@Test
public void iniciarSesionValidoTest() throws UnsupportedEncodingException
    {
        Persona persona = new Profesor();
        persona.setCorreoElectronico("peperez@unicauca.edu.co");
        persona.setContrasenia("123ABCabc.");
        
        PersonaService service = PersonaServiceFactory.getService(persona);
        String resultado  = service.iniciarSesion(persona.getCorreoElectronico(), persona.getContrasenia());
        assertEquals(resultado,"BIENVENIDO");
        
        
    }    
@Test
public void iniciarSesionInvalidoTest() throws UnsupportedEncodingException
    {
        Persona persona = new Profesor();
        persona.setCorreoElectronico("peperez@unicauca.edu.co");
        persona.setContrasenia("123ABCab.");
        
        PersonaService service = PersonaServiceFactory.getService(persona);
        String resultado  = service.iniciarSesion(persona.getCorreoElectronico(), persona.getContrasenia());
        assertEquals(resultado,"CONTRASEÑA INCORRECTA o CORREO INCORRECTO");
        
        
    }    
}
