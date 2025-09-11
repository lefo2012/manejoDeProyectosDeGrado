package co.edu.unicauca.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSQLite {
    
    private static final String URL = "jdbc:sqlite:mi_base.db"; 
    // Se creará en la raíz del proyecto
    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Error al conectar: " + e.getMessage());
            return null;
        }
    }
}
