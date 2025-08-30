/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.Repository;

import co.edu.unicauca.Models.Persona;

/**
 *
 * @author PixelBot Gaming
 */
public interface PersonaRepository {
    public Persona consultarPersonaPorCorreo(String correoElectronico);
    public String consultarCargoPorCorreo(String correoElectronico);
    public boolean registrar(Persona persona,String cargo);
}
