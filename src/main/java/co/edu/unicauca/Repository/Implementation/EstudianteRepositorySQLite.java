package co.edu.unicauca.Repository.Implementation;

import co.edu.unicauca.Models.Estudiante;
import co.edu.unicauca.Repository.EstudianteRepository;

import co.edu.unicauca.database.ConexionSQLite;
import co.edu.unicauca.dto.PersonaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

/**
 *
 * @author LEFO
 */
public class EstudianteRepositorySQLite implements EstudianteRepository{

    
    public EstudianteRepositorySQLite() {
        
    }
    
    @Override
    public Estudiante buscarPorCorreo(String correoElectronico) {
    String sql = "SELECT * FROM persona WHERE correoElectronico = ?";
    try (Connection conn = ConexionSQLite.conectar();   
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, correoElectronico);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            Estudiante estudiante = new Estudiante(
                null,
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("celular"),
                rs.getString("correoElectronico"),
                rs.getString("contrasenia"),
                new LinkedList<>()
            );
            return estudiante;
        }
    } catch (Exception e) {
        System.out.println("Error buscando persona: " + e.getMessage());
    }
    return null;
    }
    public boolean registrar(PersonaDTO estudiante) {
    String sqlPersona = "INSERT INTO persona(correoElectronico, contrasenia, nombre, apellido, celular) VALUES(?,?,?,?,?)";
    String sqlProfesor = "INSERT INTO estudiante(correoElectronico) VALUES(?)";
    
    try (Connection conn = ConexionSQLite.conectar()) {
        
        conn.setAutoCommit(false);

       
        try (PreparedStatement pstmt = conn.prepareStatement(sqlPersona)) {
            pstmt.setString(1, estudiante.getCorreoElectronico());
            pstmt.setString(2, estudiante.getContrasenia());
            pstmt.setString(3, estudiante.getNombre());
            pstmt.setString(4, estudiante.getApellido());
            pstmt.setString(5, estudiante.getTelefono());
            pstmt.executeUpdate();
        }

        
        try (PreparedStatement pstmt = conn.prepareStatement(sqlProfesor)) {
            pstmt.setString(1, estudiante.getCorreoElectronico());
            pstmt.executeUpdate();
        }

        
        conn.commit();
        return true;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

    
}
