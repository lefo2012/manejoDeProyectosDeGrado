/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.Repository;

import java.util.List;

/**
 *
 * @author PixelBot Gaming
 * @param <T>
 * @param <C>
 */
public interface Repository<T,C> {
    public List<T> getAll()throws Exception;
    public T getOne(C id)throws Exception;
    public boolean save(T object) throws Exception;
}
