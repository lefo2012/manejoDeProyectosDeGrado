package co.edu.unicauca.Services;

import co.edu.unicauca.Models.Programa;
import co.edu.unicauca.Repository.ProgramaRepository;
import java.util.List;

/**
 *
 * @author LEFO
 */
public class ProgramaService {
    ProgramaRepository programaRepository;

    public ProgramaService(ProgramaRepository programaRepository) {
        this.programaRepository = programaRepository;
    }
    
    public List<Programa> obtenerTodos() throws Exception
    {
        return programaRepository.getAll();
    
    }
}
