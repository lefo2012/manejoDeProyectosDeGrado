package co.edu.unicauca.Repository;

import co.edu.unicauca.Models.FormatoA;

/**
 *
 * @author LEFO
 */
public interface ProyectoRepository extends Repository<FormatoA,Integer>{
    public boolean consultarPosibilidad(FormatoA formatoa) throws Exception;
}
