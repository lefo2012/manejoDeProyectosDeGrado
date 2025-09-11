package co.edu.unicauca.Repository;

import co.edu.unicauca.Models.Persona;
import co.edu.unicauca.Util.Cargo;

/**
 *
 * @author LEFO
 */
public interface PersonaRepository {
    public Persona consultarPersonaPorCorreo(String correoElectronico) throws Exception;
    public Cargo consultarCargoPorCorreo(String correoElectronico);
    public boolean registrar(Persona persona,Cargo cargo) throws Exception;
}
