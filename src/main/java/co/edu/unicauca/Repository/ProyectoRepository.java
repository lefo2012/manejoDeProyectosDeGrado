package co.edu.unicauca.Repository;

import co.edu.unicauca.Models.FormatoA;
import java.util.List;

/**
 *
 * @author LEFO
 */
public interface ProyectoRepository extends Repository<FormatoA,Integer>{
    public boolean consultarPosibilidad(FormatoA formatoa) throws Exception;
    public List<FormatoA> getProyectosCoordinador(int idCoordinador) throws Exception;
    public List<FormatoA> getProyectosEstudiante(int idEstudiante) throws Exception;
    public List<FormatoA> getProyectosProfesor(int idProfesor) throws Exception;
    public boolean aceptarProyecto(int idProyecto, int idCoordinador, String comentario, String fecha) throws Exception;
    public boolean rechazarProyecto(FormatoA formato, int idCoordinador, String comentario, String fecha) throws Exception;
    public String obtenerComentarioProyecto(int idProyecto) throws Exception;
}
