package co.edu.unicauca.Repository.Implementation;


import co.edu.unicauca.Repository.PersonaRepository;

/**
 *
 * @author LEFO
 */
public class RepositoryFactory {
    
    public static PersonaRepository getInstance(String baseDatos)
    {
    
        if(baseDatos.equals("SQLite"))
        {
            return new PersonaRepositorySQLite();
        }
        return null;
    }
}
