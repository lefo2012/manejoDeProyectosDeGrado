package co.edu.unicauca.Services;

import co.edu.unicauca.Models.FormatoA;
import co.edu.unicauca.Repository.ProyectoRepository;
import java.util.List;

/**
 *
 * @author LEFO
 */
public class ProyectoService {
    ProyectoRepository proyectoRepository;

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
    
}
