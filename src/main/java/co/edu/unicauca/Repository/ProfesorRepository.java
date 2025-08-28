/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.Repository;

import co.edu.unicauca.Models.Profesor;
import co.edu.unicauca.Models.Persona;

/**
 *
 * @author PixelBot Gaming
 */
public interface ProfesorRepository {
    public Profesor buscarPorCorreo(String correoElectronico);
    public boolean registrar(Persona profesor);
}
