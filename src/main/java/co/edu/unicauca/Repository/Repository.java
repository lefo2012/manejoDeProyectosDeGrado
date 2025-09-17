package co.edu.unicauca.Repository;

import java.util.List;

/**
 *
 * @author PixelBot Gaming
 * @param <T> RECIBE EL OBJETO
 * @param <C> RECIBE LA CLAVE DEL OBJETO
 */
public interface Repository<T,C> {
    public List<T> getAll()throws Exception;
    public T getOne(C id)throws Exception;
    public boolean save(T object) throws Exception;
}
