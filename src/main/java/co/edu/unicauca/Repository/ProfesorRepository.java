/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.Repository;

import co.edu.unicauca.Models.Profesor;
import co.edu.unicauca.dto.PersonaDTO;

/**
 *
 * @author PixelBot Gaming
 */
public interface ProfesorRepository {
    public Profesor buscarPorCorreo(String correoElectronico);
    public boolean registrar(PersonaDTO profesor);
}
