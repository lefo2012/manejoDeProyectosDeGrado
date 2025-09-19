package co.edu.unicauca.Repository;

import co.edu.unicauca.Models.Persona;



/**
 *
 * @author LEFO
 * @param <Persona>
 * @param <String>
 */
public interface PersonaRepository extends Repository<Persona,String> {
    
    public Persona getOne(int id);
    
}
