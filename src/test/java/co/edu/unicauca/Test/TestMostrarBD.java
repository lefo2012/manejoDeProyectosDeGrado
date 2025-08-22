package co.edu.unicauca.Test;

import co.edu.unicauca.database.ConexionSQLite;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestMostrarBD {
    public static void main(String[] args) {
        String sql = "SELECT * FROM persona";
        
        try (Connection conn = ConexionSQLite.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                System.out.println(
                    rs.getString("correoElectronico") + " | " +
                    rs.getString("nombre") + " | " +
                    rs.getString("apellido") + " | " +
                    rs.getString("telefono")
                );
            }
        } catch (Exception e) {
            System.out.println("Error mostrando BD: " + e.getMessage());
        }
    }
}
