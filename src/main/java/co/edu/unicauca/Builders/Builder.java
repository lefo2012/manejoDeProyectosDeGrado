package co.edu.unicauca.Builders;

import co.edu.unicauca.Models.Estudiante;
import co.edu.unicauca.Models.FormatoA;
import co.edu.unicauca.Models.Profesor;
import co.edu.unicauca.Util.Tipo;

/**
 *
 * @author PixelBot Gaming
 * @param <T>
 */
public abstract class Builder{
    
    protected FormatoA formato;
    
    public FormatoA getObject()
    {
        return formato;
    }
    
    public void crearNuevoFormato()
    {
    
        formato = new FormatoA();
    
    }
    public void setTituloProyecto(String titulo)
    {
        formato.setTitulo(titulo);
    }
    public void setFecha(String fecha)
    {
        formato.setFechaDeSubida(fecha);
    }
      public void setDirector(Profesor p) {
        if (p == null) throw new IllegalArgumentException("El director es obligatorio");
        if (formato.getProfesores().isEmpty()) {
            formato.getProfesores().add(p);
        } else {
            formato.getProfesores().set(0, p);
        }
    }
    public void setCoodirector(Profesor p) {
        if (p == null) return; 
        if (formato.getProfesores().size() < 2) {
            
            if (formato.getProfesores().size() == 0) formato.getProfesores().add(null); 
            formato.getProfesores().add(p); 
        } else {
            formato.getProfesores().set(1, p);
        }
    
    }
    public void setObjetivoEspecifico(String objetivo)
    {
    
        formato.setObjetivoEspecifico(objetivo);
    
    }
    public void setObjetivo(String objetivo)
    {
        formato.setObjetivo(objetivo);
    }
    public void setArchivoAdjunto(String archivoAdjunto)
    {
        formato.setArchivoAdjunto(archivoAdjunto);
    }
    public abstract void setEstudiantes(Estudiante est1,Estudiante est2)  throws Exception;
    public abstract void setModalidad(Tipo tipo)  throws Exception;
}
