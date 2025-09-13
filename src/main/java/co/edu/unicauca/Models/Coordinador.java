package co.edu.unicauca.Models;

import java.util.List;

/**
 *
 * @author LEFO
 */
public class Coordinador extends Persona {
    
    Programa programa;
    List<Proyecto> listaProyectos;

    public Coordinador(Programa programa, String nombre, String apellido, String celular, String correoElectronico, String contrasenia) {
        super(nombre, apellido, celular, correoElectronico, contrasenia);
        this.programa = programa;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public List<Proyecto> getListaProyectos() {
        return listaProyectos;
    }

    public void setListaProyectos(List<Proyecto> listaProyectos) {
        this.listaProyectos = listaProyectos;
    }

    
    
    

    
    
    
}
