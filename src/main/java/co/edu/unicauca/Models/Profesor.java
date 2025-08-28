package co.edu.unicauca.Models;

import java.util.LinkedList;

public class Profesor extends Persona{
    
    
    private Departamento departamento;

    public Profesor() {
    }

    
    
    public Profesor(Departamento departamento, String nombre, String apellido, String celular, String correoElectronico, String contrasenia, LinkedList<Facultad> facultad) {
        super(nombre, apellido, celular, correoElectronico, contrasenia, facultad);
        this.departamento = departamento;
    }
    
    public Departamento getDepartamento()
    {
        return this.departamento;
    }
    public boolean setDepartamento(Departamento departamento)
    {
        this.departamento = departamento;
        return true;
    }
    

}