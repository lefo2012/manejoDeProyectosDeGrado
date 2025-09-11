/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.Models;

import java.util.LinkedList;

/**
 *
 * @author LEFO
 */
public class Coordinador extends Persona {
    
    Programa programa;
    LinkedList<Project> listaProyectos;

    public Coordinador(Programa programa, LinkedList<Project> listaProyectos, String nombre, String apellido, String celular, String correoElectronico, String contrasenia) {
        super(nombre, apellido, celular, correoElectronico, contrasenia, programa.getDepartamento());
        this.programa = programa;
        this.listaProyectos = listaProyectos;
    }

    

    
    
    
}
