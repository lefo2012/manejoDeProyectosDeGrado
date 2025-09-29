package co.edu.unicauca.Repository;

import co.edu.unicauca.Models.FormatoA;
import co.edu.unicauca.Models.Notificacion;
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
    public boolean aceptarProyecto(FormatoA formato, int idCoordinador, String comentario, String fecha) throws Exception;
    public boolean rechazarProyecto(FormatoA formato, int idCoordinador, String comentario, String fecha) throws Exception;
    public String obtenerComentarioProyecto(int idProyecto) throws Exception;
    public List<Notificacion> obtenerNotificacionesPorPersona(int idPersona) throws Exception;
    public void marcarNotificacionComoLeida(int idNotificacion) throws Exception;
}
