package co.edu.unicauca.Services;

import co.edu.unicauca.Models.FormatoA;
import co.edu.unicauca.Observer.Subject;
import co.edu.unicauca.Repository.ProyectoRepository;
import java.util.List;

/**
 *
 * @author LEFO
 */
public class ProyectoService extends Subject {
    ProyectoRepository proyectoRepository;
    Boolean bandera;
    
    public ProyectoService(ProyectoRepository proyectoRepository) {
        this.proyectoRepository = proyectoRepository;
    }
    
    public boolean subirFormato(FormatoA formato) throws Exception
    {
        proyectoRepository.consultarPosibilidad(formato);
        return proyectoRepository.save(formato);
    }
    public boolean consultarPosibilidad(FormatoA formato) throws Exception
    {
        return true;
    }
    public List<FormatoA> obtenerProyectosCoordinador(int idCoordinador) throws Exception{
        return  proyectoRepository.getProyectosCoordinador(idCoordinador);
    }
    public List<FormatoA> obtenerProyectosEstudiante(int idEstudiante) throws Exception
    {
        return proyectoRepository.getProyectosEstudiante(idEstudiante);
    
    }
    public boolean aceptarProyecto(int idProyecto, int idCoordinador, String comentario, String fecha) throws Exception{
        bandera=proyectoRepository.aceptarProyecto(idProyecto, idCoordinador, comentario,fecha);
        System.out.println("[ProyectoService] Proyecto aceptado, notificando observers...");
        this.notifyAllObserves();
        return bandera;
    }
    public boolean rechazarProyecto(FormatoA formato, int idCoordinador, String comentario, String fecha) throws Exception{
        bandera=proyectoRepository.rechazarProyecto(formato, idCoordinador, comentario,fecha);
        System.out.println("[ProyectoService] Proyecto aceptado, notificando observers...");
        this.notifyAllObserves();
        return bandera;
    }
    public String obtenerComentarioProyecto(int idProyecto) throws Exception{
        return proyectoRepository.obtenerComentarioProyecto(idProyecto);
    }
    public boolean verificarEStado(String estado){
        if (!estado.equals("PENDIENTE"))
            return true;
        return false;
    }
}
