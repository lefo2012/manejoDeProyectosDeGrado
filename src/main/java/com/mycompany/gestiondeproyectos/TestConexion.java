package com.mycompany.gestiondeproyectos;

import co.edu.unicauca.database.ConexionSQLite;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestConexion {
    public static void main(String[] args) {
        try (Connection conn = ConexionSQLite.conectar()) {
            if (conn != null) {
                System.out.println("Conexión establecida con SQLite");

                // Probar con un SELECT simple
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT 1 as test");
                if (rs.next()) {
                    System.out.println("Resultado de prueba: " + rs.getInt("test"));
                }
            } else {
                System.out.println("No se pudo conectar");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}