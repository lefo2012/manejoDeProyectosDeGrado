package co.edu.unicauca.Builders;

import co.edu.unicauca.Models.Estudiante;
import co.edu.unicauca.Models.FormatoA;
import co.edu.unicauca.Models.Profesor;
import co.edu.unicauca.Util.Tipo;

/**
 *
 * @author LEFO
 */
public class Director{
    
    private Builder builder;
    public void SetBuilder(Builder builder)
    {
        
        this.builder=builder;
    
    }

    public Builder getBuilder() {
        return builder;
    }
    public FormatoA getObject()
    {
        return builder.getObject();
    }
    public void build(String titulo,Profesor director,Profesor coodirector,String fecha,String objetivo,String objetivoEspecifico,Estudiante est1,Estudiante est2,Tipo tipo,String archivoAdjunto) throws Exception
    {
        
        builder.crearNuevoFormato();
        builder.setTituloProyecto(titulo);
        builder.setDirector(director);
        builder.setCoodirector(coodirector);
        builder.setFecha(fecha);
        builder.setObjetivo(objetivo);
        builder.setObjetivoEspecifico(objetivoEspecifico);
        builder.setModalidad(tipo);
        builder.setArchivoAdjunto(archivoAdjunto);
        builder.setEstudiantes(est1, est2);
        
    }
    
    
}
