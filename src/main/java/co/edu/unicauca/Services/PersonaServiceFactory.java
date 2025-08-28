/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.Services;
import co.edu.unicauca.Repository.Implementation.EstudianteRepositorySQLite;
import co.edu.unicauca.Repository.Implementation.ProfesorRepositorySQLite;

import co.edu.unicauca.Models.Persona;
import co.edu.unicauca.Models.Profesor;
/**
 *
 * @author PixelBot Gaming
 */
public class PersonaServiceFactory {

    private static final EstudianteService estudianteService = new EstudianteService(new EstudianteRepositorySQLite());
    private static final ProfesorService profesorService = new ProfesorService(new ProfesorRepositorySQLite());

    public static PersonaService getService(Persona persona) {
        return persona.getClass() == Profesor.class ? profesorService : estudianteService;
    }
}
