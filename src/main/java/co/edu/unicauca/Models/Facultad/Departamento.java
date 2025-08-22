package co.edu.unicauca.Models.Facultad;

import co.edu.unicauca.Models.Persona;
import java.util.LinkedList;

public class Departamento extends Facultad {
    private int codigoDepartamento;
    private String nombreDepartamento;
    private LinkedList<Persona> personasEnElDepartamento;

    public Departamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
        this.personasEnElDepartamento = new LinkedList<>();
    }

    public Departamento(int codigoDepartamento, String nombreDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
        this.nombreDepartamento = nombreDepartamento;
        this.personasEnElDepartamento = new LinkedList<>();
    }

    public int getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(int codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public LinkedList<Persona> getPersonasEnElDepartamento() {
        return personasEnElDepartamento;
    }

    public void setPersonasEnElDepartamento(LinkedList<Persona> personasEnElDepartamento) {
        this.personasEnElDepartamento = personasEnElDepartamento;
    }
}
