package co.edu.unicauca.Models;

import co.edu.unicauca.Models.Departamento;
/**
 *
 * @author LEFO
 */
public class Programa
{
    private String codigoDePrograma;
    private String nombrePrograma;
    private Departamento departamento;

    public Programa(String codigoDePrograma, String nombrePrograma, Departamento departamento) {
        this.codigoDePrograma = codigoDePrograma;
        this.nombrePrograma = nombrePrograma;
        this.departamento = departamento;
    }

    public String getCodigoDePrograma() {
        return codigoDePrograma;
    }

    public void setCodigoDePrograma(String codigoDePrograma) {
        this.codigoDePrograma = codigoDePrograma;
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