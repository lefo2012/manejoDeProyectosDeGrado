package co.edu.unicauca.Repository.Implementation;

import co.edu.unicauca.Models.Coordinador;
import co.edu.unicauca.Models.Departamento;
import co.edu.unicauca.Models.Estudiante;
import co.edu.unicauca.Models.Persona;
import co.edu.unicauca.Models.Profesor;
import co.edu.unicauca.Models.Programa;
import co.edu.unicauca.Repository.PersonaRepository;
import co.edu.unicauca.Util.Cargo;
import co.edu.unicauca.database.ConexionSQLite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import org.sqlite.SQLiteException;

/**
 *
 * @author LEFO
 */
public class PersonaRepositorySQLite implements PersonaRepository {
    
    @Override
    public Persona consultarPersonaPorCorreo(String correoElectronico) throws Exception{
        
    String sql = "SELECT * FROM persona WHERE correoElectronico = ?";
    
    try{
        Connection conn = ConexionSQLite.conectar();   
        PreparedStatement stmPersona = conn.prepareStatement(sql);
        stmPersona.setString(1, correoElectronico);

        ResultSet rs = stmPersona.executeQuery();
        Cargo cargo = consultarCargoPorCorreo(correoElectronico);
        
        if (cargo == null) return null;
        
        if (rs.next()) {
            Persona persona = null;
            if(cargo==Cargo.Estudiante){
                persona = new Estudiante(new Programa(1,"Ingenieria de sistemas",new Departamento(1,"SISTEMAS")),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("celular"),
                        rs.getString("correoElectronico"),
                        rs.getString("contrasenia"));  
            }else if(cargo==Cargo.Profesor)
            {
                persona = new Profesor(new Programa(1,"Ingenieria de sistemas",new Departamento(1,"SISTEMAS")),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("celular"),
                        rs.getString("correoElectronico"),
                        rs.getString("contrasenia"));  
            }else if(cargo==Cargo.Coordinador)
            {
                persona = new Coordinador(new Programa(1,"Ingenieria de sistemas",new Departamento(1,"SISTEMAS")),
                        new LinkedList(),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("celular"),
                        rs.getString("correoElectronico"),
                        rs.getString("contrasenia"));  
            }
            return persona;
        }
    } catch (SQLException e) 
    {
        throw(new Exception("Error al encontrar a la persona en labase de datos"));
    }
    
    return null;
    }
    
    // IMPLEMENTAR ESTO PARA USARLO EN LA FUNCION DE ARRIBA
    private Departamento consultarProgramaPorCorreo(String correoElectronico) throws Exception
    {
        String sql = "SELECT * FROM persona inner join Programa on Persona.correoElectronico = Programa.correoElectronico WHERE Persona.correoElectronico = ? ";
        try
        {
            Connection conn = ConexionSQLite.conectar();   
            PreparedStatement stmPersona = conn.prepareStatement(sql);
            stmPersona.setString(1, correoElectronico);
            ResultSet rs = stmPersona.executeQuery();
            
        }catch(Exception e)
        {
            throw(new Exception("Error al conectar a la base de datos"));
        }
        return null;
    }
    
    @Override
    public Cargo consultarCargoPorCorreo(String correoElectronico) {   
    String sqlProfesor = "SELECT COUNT(*) FROM persona inner join profesor on persona.correoElectronico=profesor.correoElectronico where persona.correoElectronico = ?";
    String sqlEstudiante = "SELECT COUNT(*) FROM persona inner join estudiante on persona.correoElectronico=estudiante.correoElectronico where persona.correoElectronico = ?";
    String sqlCoordinador = "SELECT COUNT(*) FROM persona inner join coordinador on persona.correoElectronico=coordinador.correoElectronico where persona.correoElectronico = ?";
    try (Connection conn = ConexionSQLite.conectar();     
        PreparedStatement stmProfesor = conn.prepareStatement(sqlProfesor);
        PreparedStatement stmEstudiante = conn.prepareStatement(sqlEstudiante);
        PreparedStatement stmCoordinador = conn.prepareStatement(sqlCoordinador)
            )    
        {

        stmProfesor.setString(1, correoElectronico);
        stmEstudiante.setString(1, correoElectronico);
        stmCoordinador.setString(1, correoElectronico);
        
        ResultSet esEstudiante = stmEstudiante.executeQuery();
        ResultSet esProfesor   = stmProfesor.executeQuery();
        ResultSet esCoordinador = stmCoordinador.executeQuery();

        int cEst = 0, cProf = 0, cCoord = 0;
        
        if (esEstudiante.next()) cEst = esEstudiante.getInt(1);
        if (esProfesor.next())   cProf = esProfesor.getInt(1);
        if (esCoordinador.next()) cCoord = esCoordinador.getInt(1);

        if (cEst > 0) return Cargo.Estudiante;
        if (cProf > 0) return Cargo.Profesor;
        if (cCoord>0) return Cargo.Coordinador;
        return null;
    } catch (Exception e) {
        System.out.println("Error buscando persona: " + e.getMessage());
    }
    return null;
    }
    
    @Override
    public boolean registrar(Persona persona,Cargo cargo) throws SQLiteException {
    String sqlPersona = "INSERT INTO persona(correoElectronico, contrasenia, nombre, apellido, celular) VALUES(?,?,?,?,?)";
    String sqlEstudiante = "INSERT INTO estudiante(correoElectronico) VALUES(?)";
    String sqlProfesor = "INSERT INTO profesor(correoElectronico) VALUES(?)";
    String sqlCoordinador = "INSERT INTO coordinador(correoElectronico) VALUES(?)";
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
        

        if(cargo==Cargo.Estudiante){
            try (PreparedStatement pstmt = conn.prepareStatement(sqlEstudiante)) {
                pstmt.setString(1, persona.getCorreoElectronico());
                pstmt.executeUpdate();
            }
        }
        if(cargo==Cargo.Profesor)
        {
            try (PreparedStatement pstmt = conn.prepareStatement(sqlProfesor)) {
                pstmt.setString(1, persona.getCorreoElectronico());
                pstmt.executeUpdate();
            }
        
        }
        if(cargo==Cargo.Coordinador)
        {
            try (PreparedStatement pstmt = conn.prepareStatement(sqlCoordinador)) {
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
