package co.edu.unicauca.Models;

import co.edu.unicauca.Models.Facultad.Facultad;


import java.util.LinkedList;

public abstract class Persona
{
    private String nombre;
    private String apellido;
    private String celular;
    private String correoElectronico;
    private String contrasenia;
    private LinkedList<Facultad> facultad;
    private boolean isLogged;
    
    
    
    
    

    public Persona(String nombre, String apellido, String celular, String correoElectronico, String contrasenia, LinkedList<Facultad> facultad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
        this.facultad = facultad;
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

    public LinkedList<Facultad> getFacultad() {
        return facultad;
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

    public void setFacultad(LinkedList<Facultad> facultad) {
        this.facultad = facultad;
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }
    
    public boolean verificarInicioDeSesion()
    {
        
        return true;
        
    }

}