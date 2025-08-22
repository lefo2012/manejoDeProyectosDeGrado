package co.edu.unicauca.database;

import java.sql.Connection;
import java.sql.Statement;

public class InitDB {
    public static void crearTablas() {
        try (Connection conn = ConexionSQLite.conectar();
             Statement stmt = conn.createStatement()) {

            String sqlPersona = """
                CREATE TABLE IF NOT EXISTS persona (
                    correoElectronico TEXT PRIMARY KEY,
                    contrasenia TEXT NOT NULL,
                    nombre TEXT NOT NULL,
                    apellido TEXT NOT NULL,
                    celular TEXT
                );
                """;

            String sqlProfesor = """
                CREATE TABLE IF NOT EXISTS profesor (
                    correoElectronico TEXT PRIMARY KEY,
                    FOREIGN KEY (correoElectronico) REFERENCES persona(correoElectronico)
                );
                """;

            String sqlEstudiante = """
                CREATE TABLE IF NOT EXISTS estudiante (
                    correoElectronico TEXT PRIMARY KEY,
                    FOREIGN KEY (correoElectronico) REFERENCES persona(correoElectronico)
                );
                """;

            stmt.execute(sqlPersona);
            stmt.execute(sqlProfesor);
            stmt.execute(sqlEstudiante);

            System.out.println("Tablas creadas correctamente.");
        } catch (Exception e) {
            System.out.println("Error creando tablas: " + e.getMessage());
        }
    }
}
