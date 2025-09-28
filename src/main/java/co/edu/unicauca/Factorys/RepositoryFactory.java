package co.edu.unicauca.Factorys;


import co.edu.unicauca.Repository.DepartamentoRepository;
import co.edu.unicauca.Repository.Implementation.DepartamentoRepositorySQLite;
import co.edu.unicauca.Repository.Implementation.PersonaRepositorySQLite;
import co.edu.unicauca.Repository.Implementation.ProgramaRepositorySQLite;
import co.edu.unicauca.Repository.Implementation.ProyectoRepositorySQLite;
import co.edu.unicauca.Repository.PersonaRepository;
import co.edu.unicauca.Repository.ProgramaRepository;
import co.edu.unicauca.Repository.ProyectoRepository;
     
import java.util.Locale;
import java.util.ResourceBundle;
/**
 *
 * @author LEFO
 */
public class RepositoryFactory<T>{
    
    private Class<T> repositoryTipe;
    
     public RepositoryFactory(Class<T> repositoryTipe) {
        this.repositoryTipe = repositoryTipe;
    }


    
    public T getInstance()
    {
        try{
            Locale locale = Locale.getDefault();
            ResourceBundle bundle = ResourceBundle.getBundle("properties.database", locale);
            String dataBaseType = bundle.getString("label.databaseType"); 
            switch (dataBaseType) {
                case "SQLite":
                    if(repositoryTipe.equals(PersonaRepository.class))
                    {
                        return (T) new PersonaRepositorySQLite();
                    }else if(repositoryTipe.equals(ProgramaRepository.class))
                    {
                        return (T) new ProgramaRepositorySQLite();
                    }else if(repositoryTipe.equals(DepartamentoRepository.class))
                    {
                        return (T) new DepartamentoRepositorySQLite();
                    }else if(repositoryTipe.equals(ProyectoRepository.class))
                    {
                        return (T) new ProyectoRepositorySQLite();
                    }
                    break;

                default:
                    throw new Exception("ERROR AL ENCONTRAR EL TIPO DE BASE DE DATOS "+dataBaseType);
            }
            return null;
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
        
    }
}
