package co.edu.unicauca.Models;

import java.util.LinkedList;

/**
 *
 * @author LEFO
 */
public class Profesor extends Persona{
    
    Programa programa;
    LinkedList<Project> listaProyectos;

    public Profesor(Programa programa, String nombre, String apellido, String celular, String correoElectronico, String contrasenia) {
        super(nombre, apellido, celular, correoElectronico, contrasenia, programa.getDepartamento());
        this.programa = programa;
    }

    
  
}