package co.edu.unicauca.Models;


/**
 *
 * @author LEFO
 */
public class Persona
{
    private String nombre;
    private String apellido;
    private String celular;
    private String correoElectronico;
    private String contrasenia;
    private Departamento departamento;
    private Facultad facultad;
    
    private boolean isLogged;

    public Persona() {
    }
    
    
    public Persona(String nombre, String apellido, String celular, String correoElectronico, String contrasenia,Departamento departamento) {
        
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
        this.facultad = new Facultad(1,"Facultad de Ingenieria Electronica y Telecomunicaciones");
        this.departamento = departamento;
        departamento.setFacultad(facultad);
    }
    
    public boolean setContrasenia(String contrasenia)
    {   
        this.contrasenia=contrasenia;
        return true;
    }
    
    
    public boolean setCorreoElectronico(String correoElectronico)
    {
        
        this.correoElectronico=correoElectronico;
        return true;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCelular() {
        return celular;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public boolean isIsLogged() {
        return isLogged;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }
    
    public boolean verificarInicioDeSesion()
    {
        
        return true;
        
    }

}