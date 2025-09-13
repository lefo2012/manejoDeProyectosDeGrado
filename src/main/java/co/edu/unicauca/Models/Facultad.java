package co.edu.unicauca.Models;

public class Facultad
{   
    private String codigoFacultad;
    private String nombreFacultad;

    public Facultad(String codigoFacultad, String nombreFacultad) {
        this.codigoFacultad = codigoFacultad;
        this.nombreFacultad = nombreFacultad;
    }

    public String getCodigoFacultad() {
        return codigoFacultad;
    }

    public void setCodigoFacultad(String codigoFacultad) {
        this.codigoFacultad = codigoFacultad;
    }

    public String getNombreFacultad() {
        return nombreFacultad;
    }

    public void setNombreFacultad(String nombreFacultad) {
        this.nombreFacultad = nombreFacultad;
    }
    
} 