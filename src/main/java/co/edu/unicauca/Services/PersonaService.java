/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.edu.unicauca.Services;

import co.edu.unicauca.Models.Persona;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author PixelBot Gaming
 */
public interface PersonaService {
    public String iniciarSesion(String correoElectronico, String contrasenia) throws UnsupportedEncodingException;
    public String registrar(Persona persona) throws UnsupportedEncodingException;
}
