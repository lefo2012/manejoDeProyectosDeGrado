/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.Models;

import co.edu.unicauca.Models.Facultad.Facultad;
import co.edu.unicauca.Models.Facultad.Programa;
import java.util.LinkedList;

/**
 *
 * @author PixelBot Gaming
 */
public class Estudiante extends Persona {
    Programa programa;
    public Estudiante(Programa programa,String nombre, String apellido, String celular, String correoElectronico, String contrasenia, LinkedList<Facultad> facultad) {
        super(nombre, apellido, celular, correoElectronico, contrasenia, facultad);
        this.programa=programa;
    }
    
}
