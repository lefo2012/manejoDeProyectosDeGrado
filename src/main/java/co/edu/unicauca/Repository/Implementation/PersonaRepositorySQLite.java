/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.Repository.Implementation;

import co.edu.unicauca.Models.Estudiante;
import co.edu.unicauca.Models.Persona;
import co.edu.unicauca.Models.Profesor;
import co.edu.unicauca.Repository.PersonaRepository;
import co.edu.unicauca.database.ConexionSQLite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

/**
 *
 * @author LEFO
 */
public class PersonaRepositorySQLite implements PersonaRepository {
    
    public Persona consultarPersonaPorCorreo(String correoElectronico) {
        
    String sql = "SELECT * FROM persona WHERE correoElectronico = ?";
    
    try (Connection conn = ConexionSQLite.conectar();   
        PreparedStatement stmPersona = conn.prepareStatement(sql))
        {
        
        stmPersona.setString(1, correoElectronico);

        ResultSet rs = stmPersona.executeQuery();
        String cargo = consultarCargoPorCorreo(correoElectronico);
        
        if (cargo == null) return null;
        
        if (rs.next()) {
            Persona persona = null;
            if(cargo.equals("Estudiante")){
                persona = new Estudiante(
                null,
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("celular"),
                rs.getString("correoElectronico"),
                rs.getString("contrasenia"),
                new LinkedList<>()
                );
            }else if(cargo.equals("Profesor"))
            {
                persona = new Profesor(
                null,
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("celular"),
                rs.getString("correoElectronico"),
                rs.getString("contrasenia"),
                new LinkedList<>()
                );
            }
            return persona;
        }
    } catch (Exception e) {
        System.out.println("Error buscando persona: " + e.getMessage());
    }
    return null;
    }

    
    @Override
    public String consultarCargoPorCorreo(String correoElectronico) {   
    String sqlProfesor = "SELECT COUNT(*) FROM persona inner join profesor on persona.correoElectronico=profesor.correoElectronico where persona.correoElectronico = ?";
    String sqlEstudiante = "SELECT COUNT(*) FROM persona inner join estudiante on persona.correoElectronico=estudiante.correoElectronico where persona.correoElectronico = ?";
    try (Connection conn = ConexionSQLite.conectar();     
        PreparedStatement stmProfesor = conn.prepareStatement(sqlProfesor);
        PreparedStatement stmEstudiante = conn.prepareStatement(sqlEstudiante))        
        {

        stmProfesor.setString(1, correoElectronico);
        stmEstudiante.setString(1, correoElectronico);
        
        ResultSet esEstudiante = stmEstudiante.executeQuery();
        ResultSet esProfesor   = stmProfesor.executeQuery();

        int cEst = 0, cProf = 0;
        
        if (esEstudiante.next()) cEst = esEstudiante.getInt(1);
        if (esProfesor.next())   cProf = esProfesor.getInt(1);

        if (cEst > 0) return "Estudiante";
        if (cProf > 0) return "Profesor";
        return null;
    } catch (Exception e) {
        System.out.println("Error buscando persona: " + e.getMessage());
    }
    return null;
    }
    
    @Override
    public boolean registrar(Persona persona,String cargo) {
    String sqlPersona = "INSERT INTO persona(correoElectronico, contrasenia, nombre, apellido, celular) VALUES(?,?,?,?,?)";
    String sqlEstudiante = "INSERT INTO estudiante(correoElectronico) VALUES(?)";
    String sqlProfesor = "INSERT INTO profesor(correoElectronico) VALUES(?)";
    try (Connection conn = ConexionSQLite.conectar()) {
        
        conn.setAutoCommit(false);

       
        try (PreparedStatement pstmt = conn.prepareStatement(sqlPersona)) {
            pstmt.setString(1, persona.getCorreoElectronico());
            pstmt.setString(2, persona.getContrasenia());
            pstmt.setString(3, persona.getNombre());
            pstmt.setString(4, persona.getApellido());
            pstmt.setString(5, persona.getCelular());
            pstmt.executeUpdate();
        }

        if(cargo.equals("Estudiante")){
            try (PreparedStatement pstmt = conn.prepareStatement(sqlEstudiante)) {
                pstmt.setString(1, persona.getCorreoElectronico());
                pstmt.executeUpdate();
            }
        }else if(cargo.equals("Profesor"))
        {
            try (PreparedStatement pstmt = conn.prepareStatement(sqlProfesor)) {
                pstmt.setString(1, persona.getCorreoElectronico());
                pstmt.executeUpdate();
            }
        
        }
        
        conn.commit();
        return true;

    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    }
    
    
}
