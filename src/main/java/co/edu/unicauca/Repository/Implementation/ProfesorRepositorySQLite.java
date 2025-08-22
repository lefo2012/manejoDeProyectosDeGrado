package co.edu.unicauca.Repository.Implementation;

import co.edu.unicauca.Models.Profesor;
import co.edu.unicauca.Repository.ProfesorRepository;
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
public class ProfesorRepositorySQLite implements ProfesorRepository{

    
    public ProfesorRepositorySQLite() {
        
    }
    
    @Override
    public Profesor buscarPorCorreo(String correoElectronico) {
    String sql = "SELECT * FROM persona WHERE correoElectronico = ?";
    try (Connection conn = ConexionSQLite.conectar();   
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, correoElectronico);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            Profesor profesor = new Profesor(
                null,
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("celular"),
                rs.getString("correoElectronico"),
                rs.getString("contrasenia"),
                new LinkedList<>()
            );
            return profesor;
        }
    } catch (Exception e) {
        System.out.println("Error buscando persona: " + e.getMessage());
    }
    return null;
    }
    public boolean registrar(PersonaDTO profesor) {
    String sqlPersona = "INSERT INTO persona(correoElectronico, contrasenia, nombre, apellido, celular) VALUES(?,?,?,?,?)";
    String sqlProfesor = "INSERT INTO profesor(correoElectronico) VALUES(?)";
    
    try (Connection conn = ConexionSQLite.conectar()) {
        
        conn.setAutoCommit(false);

       
        try (PreparedStatement pstmt = conn.prepareStatement(sqlPersona)) {
            pstmt.setString(1, profesor.getCorreoElectronico());
            pstmt.setString(2, profesor.getContrasenia());
            pstmt.setString(3, profesor.getNombre());
            pstmt.setString(4, profesor.getApellido());
            pstmt.setString(5, profesor.getTelefono());
            pstmt.executeUpdate();
        }

        
        try (PreparedStatement pstmt = conn.prepareStatement(sqlProfesor)) {
            pstmt.setString(1, profesor.getCorreoElectronico());
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
