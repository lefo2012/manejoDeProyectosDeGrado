package co.edu.unicauca.Models;

import co.edu.unicauca.Models.Programa;

/**
 *
 * @author LEFO
 */
public class Estudiante extends Persona {
    
    Programa programa;
    Project proyecto;
    public Estudiante() {
    }

    public Estudiante(Programa programa, String nombre, String apellido, String celular, String correoElectronico, String contrasenia) {
        super(nombre, apellido, celular, correoElectronico, contrasenia, programa.getDepartamento());
        this.programa = programa;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public Project getProyecto() {
        return proyecto;
    }

    public void setProyecto(Project proyecto) {
        this.proyecto = proyecto;
    }
    
    
    
}
