package co.edu.unicauca.Models;

import co.edu.unicauca.Models.Programa;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author LEFO
 */
public class Estudiante extends Persona {
    
    Programa programa;
    List<Proyecto> proyectos;
    int cantidadIntentosPractica;
    int cantidadIntentosInvestigacion;
    public Estudiante() {
    }

    public Estudiante(Programa programa, int cantidadIntentosPractica, int cantidadIntentosInvestigacion, String nombre, String apellido, String celular, String correoElectronico, String contrasenia) {
        super(nombre, apellido, celular, correoElectronico, contrasenia);
        this.programa = programa;
        this.cantidadIntentosPractica = cantidadIntentosPractica;
        this.cantidadIntentosInvestigacion = cantidadIntentosInvestigacion;
        proyectos = new LinkedList();
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }

   


    
    
    
}
