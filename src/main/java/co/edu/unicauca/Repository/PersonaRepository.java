package co.edu.unicauca.Repository;

import co.edu.unicauca.Models.Persona;

/**
 *
 * @author LEFO
 */
public interface PersonaRepository {
    
    public Persona buscarPorCorreo(String correoElectronico);
    
}
