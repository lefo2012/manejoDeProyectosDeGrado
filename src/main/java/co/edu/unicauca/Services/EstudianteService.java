/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.Services;

import co.edu.unicauca.Models.Estudiante;
import co.edu.unicauca.Repository.EstudianteRepository;
import co.edu.unicauca.Util.Validador;
import co.edu.unicauca.Util.Encriptador;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Lefo
 */
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    // Iniciar sesión
    public Estudiante iniciarSesion(String correoElectronico, String contrasenia) throws UnsupportedEncodingException {

        if (!Validador.esCorreoValido("@unicauca.edu.co", correoElectronico))
            return null;

        Estudiante estudiante = estudianteRepository.buscarPorCorreo(correoElectronico);
        if (estudiante == null) {
            return null;
        }

        String clave = "1234567890ABCDEF";  
        byte[] iv = "abcdefghijklmnop".getBytes("UTF-8");

        if (Encriptador.decriptar(clave, iv, estudiante.getContrasenia()).equals(contrasenia)) {
            return estudiante;
        }

        return null;
    }

    // Registrar estudiante
    public String registrar(Estudiante estudiante) throws UnsupportedEncodingException {

        if (!Validador.esCorreoValido("@unicauca.edu.co", estudiante.getCorreoElectronico()))
            return "Correo invalido";

        if (!Validador.esContraseniaCorrecta(estudiante.getContrasenia())) {
            return "Formato de contrasenia invalido recuerde que debe llevar por lo menos un caracter especial una mayuscula y un digito";
        }

        String clave = "1234567890ABCDEF";  
        byte[] iv = "abcdefghijklmnop".getBytes("UTF-8");

        estudiante.setContrasenia(Encriptador.encriptar(clave, iv, estudiante.getContrasenia()));

        estudianteRepository.registrar(estudiante);
        return "Registro completado";
    }
}
