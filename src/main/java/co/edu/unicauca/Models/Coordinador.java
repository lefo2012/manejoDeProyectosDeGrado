package co.edu.unicauca.Models;

import java.util.List;

/**
 *
 * @author LEFO
 */
public class Coordinador extends Persona {
    
    Departamento departamento;
    List<Proyecto> listaProyectos;

    public Coordinador(Departamento departamento, String nombre, String apellido, String celular, String correoElectronico, String contrasenia) {
        super(nombre, apellido, celular, correoElectronico, contrasenia);
        this.departamento = departamento;
    }

    public Coordinador() {
       
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Proyecto> getListaProyectos() {
        return listaProyectos;
    }

    public void setListaProyectos(List<Proyecto> listaProyectos) {
        this.listaProyectos = listaProyectos;
    }

    
    
    

    
    
    
}
