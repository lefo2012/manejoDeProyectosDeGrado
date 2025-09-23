package co.edu.unicauca.Services;

import co.edu.unicauca.Models.FormatoA;
import co.edu.unicauca.Repository.ProyectoRepository;

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
    
}
