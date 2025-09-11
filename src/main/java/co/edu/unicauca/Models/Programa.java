package co.edu.unicauca.Models;

import co.edu.unicauca.Models.Departamento;
/**
 *
 * @author LEFO
 */
public class Programa
{
    private int codigoFacultad;
    private String nombrePrograma;
    private Departamento departamento;

    public Programa(int codigoFacultad, String nombrePrograma, Departamento departamento) {
        this.codigoFacultad = codigoFacultad;
        this.nombrePrograma = nombrePrograma;
        this.departamento = departamento;
    }

    public int getCodigoFacultad() {
        return codigoFacultad;
    }

    public void setCodigoFacultad(int codigoFacultad) {
        this.codigoFacultad = codigoFacultad;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    

}