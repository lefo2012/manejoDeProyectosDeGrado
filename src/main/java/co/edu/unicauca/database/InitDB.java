package co.edu.unicauca.database;

import java.sql.Connection;
import java.sql.Statement;

public class InitDB {
  public static void crearTablas() {
    try (Connection conn = ConexionSQLite.conectar();
         Statement stmt = conn.createStatement()) {

        stmt.execute("PRAGMA foreign_keys = ON;");

        String sqlFacultad = """
            CREATE TABLE IF NOT EXISTS Facultad (
                idFacultad TEXT PRIMARY KEY,
                nombre     TEXT NOT NULL
            );
            """;

        String sqlDepartamento = """
            CREATE TABLE IF NOT EXISTS Departamento (
                idDepartamento TEXT PRIMARY KEY,
                nombre         TEXT NOT NULL,
                idFacultad     TEXT NOT NULL,
                FOREIGN KEY (idFacultad) REFERENCES Facultad(idFacultad)
            );
            """;

        String sqlPrograma = """
            CREATE TABLE IF NOT EXISTS Programa (
                idPrograma     TEXT PRIMARY KEY,
                nombre         TEXT NOT NULL,
                idDepartamento TEXT NOT NULL,
                FOREIGN KEY (idDepartamento) REFERENCES Departamento(idDepartamento)
            );
            """;

        String sqlPersona = """
            CREATE TABLE IF NOT EXISTS Persona (
                idPersona  TEXT PRIMARY KEY,
                nombre     TEXT NOT NULL,
                apellido   TEXT NOT NULL,
                correoElectronico TEXT NOT NULL UNIQUE,
                contrasenia TEXT NOT NULL,
                telefono   TEXT
            );
            """;

        String sqlEstudiante = """
            CREATE TABLE IF NOT EXISTS Estudiante (
                idEstudiante  TEXT PRIMARY KEY,
                idPrograma    TEXT NOT NULL,
                cantidadIntentosInvestigacion INTEGER DEFAULT 0,
                cantidadIntentosPractica      INTEGER DEFAULT 0,
                FOREIGN KEY (idEstudiante) REFERENCES Persona(idPersona),
                FOREIGN KEY (idPrograma)   REFERENCES Programa(idPrograma)
            );
            """;

        String sqlProfesor = """
            CREATE TABLE IF NOT EXISTS Profesor (
                idProfesor     TEXT PRIMARY KEY,
                idDepartamento TEXT NOT NULL,
                FOREIGN KEY (idProfesor)     REFERENCES Persona(idPersona),
                FOREIGN KEY (idDepartamento) REFERENCES Departamento(idDepartamento)
            );
            """;

        String sqlCoordinador = """
            CREATE TABLE IF NOT EXISTS Coordinador (
                idCoordinador TEXT PRIMARY KEY,
                idPrograma    TEXT NOT NULL,
                FOREIGN KEY (idCoordinador) REFERENCES Persona(idPersona),
                FOREIGN KEY (idPrograma)    REFERENCES Programa(idPrograma)
            );
            """;

        String sqlProyecto = """
            CREATE TABLE IF NOT EXISTS Proyecto (
                idProyecto         TEXT PRIMARY KEY,
                titulo             TEXT NOT NULL,
                objetivo           TEXT,
                objetivoEspecifico TEXT,
                estado             TEXT NOT NULL,
                tipo               TEXT NOT NULL,
                fechaDeSubida      TEXT,
                fechaRevision      TEXT,
                archivoAdjunto     TEXT
            );
            """;

        String sqlComentario = """
            CREATE TABLE IF NOT EXISTS Comentario (
                idComentario  TEXT PRIMARY KEY,
                contenido     TEXT NOT NULL,
                fecha         TEXT NOT NULL,
                idProyecto    TEXT NOT NULL,
                idCoordinador TEXT NOT NULL,
                FOREIGN KEY (idProyecto)    REFERENCES Proyecto(idProyecto),
                FOREIGN KEY (idCoordinador) REFERENCES Coordinador(idCoordinador)
            );
            """;

        String sqlProyectosEstudiante = """
            CREATE TABLE IF NOT EXISTS ProyectosEstudiante (
                idEstudiante TEXT NOT NULL,
                idProyecto   TEXT NOT NULL,
                PRIMARY KEY (idEstudiante, idProyecto),
                FOREIGN KEY (idEstudiante) REFERENCES Estudiante(idEstudiante),
                FOREIGN KEY (idProyecto)   REFERENCES Proyecto(idProyecto)
            );
            """;

        String sqlProyectosProfesor = """
            CREATE TABLE IF NOT EXISTS ProyectosProfesor (
                idDirector   TEXT NOT NULL,
                idCodirector TEXT,
                idProyecto   TEXT NOT NULL,
                PRIMARY KEY (idDirector, idProyecto),
                FOREIGN KEY (idCodirector) REFERENCES Profesor(idProfesor),
                FOREIGN KEY (idDirector)   REFERENCES Profesor(idProfesor),
                FOREIGN KEY (idProyecto)   REFERENCES Proyecto(idProyecto)
            );
            """;

        String sqlProyectosCoordinador = """
            CREATE TABLE IF NOT EXISTS ProyectosCoordinador (
                idCoordinador TEXT NOT NULL,
                idProyecto    TEXT NOT NULL,
                PRIMARY KEY (idCoordinador, idProyecto),
                FOREIGN KEY (idCoordinador) REFERENCES Coordinador(idCoordinador),
                FOREIGN KEY (idProyecto)    REFERENCES Proyecto(idProyecto)
            );
            """;

        String trgBeforeInsertPE = """
            CREATE TRIGGER IF NOT EXISTS trg_pe_before_insert
            BEFORE INSERT ON ProyectosEstudiante
            FOR EACH ROW
            BEGIN
              SELECT CASE
                WHEN (SELECT tipo FROM Proyecto WHERE idProyecto = NEW.idProyecto) = 'Investigacion'
                     AND (SELECT COUNT(*) FROM ProyectosEstudiante WHERE idProyecto = NEW.idProyecto) >= 2
                  THEN RAISE(ABORT, 'Proyecto de Investigacion permite maximo 2 estudiantes')
                WHEN (SELECT tipo FROM Proyecto WHERE idProyecto = NEW.idProyecto) = 'Practica laboral'
                     AND (SELECT COUNT(*) FROM ProyectosEstudiante WHERE idProyecto = NEW.idProyecto) >= 1
                  THEN RAISE(ABORT, 'Proyecto de Practica laboral permite maximo 1 estudiante')
              END;
            END;
            """;

        String trgBeforeUpdateTipo = """
            CREATE TRIGGER IF NOT EXISTS trg_proyecto_tipo_before_update
            BEFORE UPDATE OF tipo ON Proyecto
            FOR EACH ROW
            BEGIN
              SELECT CASE
                WHEN NEW.tipo = 'Practica laboral'
                     AND (SELECT COUNT(*) FROM ProyectosEstudiante WHERE idProyecto = OLD.idProyecto) > 1
                  THEN RAISE(ABORT, 'No se puede cambiar a Practica laboral: hay >1 estudiante asignado')
                WHEN NEW.tipo = 'Investigacion'
                     AND (SELECT COUNT(*) FROM ProyectosEstudiante WHERE idProyecto = OLD.idProyecto) > 2
                  THEN RAISE(ABORT, 'No se puede cambiar a Investigacion: hay >2 estudiantes asignados')
              END;
            END;
            """;

        stmt.execute(sqlFacultad);
        stmt.execute(sqlDepartamento);
        stmt.execute(sqlPrograma);
        stmt.execute(sqlPersona);
        stmt.execute(sqlEstudiante);
        stmt.execute(sqlProfesor);
        stmt.execute(sqlCoordinador);
        stmt.execute(sqlProyecto);
        stmt.execute(sqlComentario);
        stmt.execute(sqlProyectosEstudiante);
        stmt.execute(sqlProyectosProfesor);
        stmt.execute(sqlProyectosCoordinador);
        stmt.execute(trgBeforeInsertPE);
        stmt.execute(trgBeforeUpdateTipo);

        System.out.println("Esquema creado (sin BLOBs) y triggers de cupo activos.");
        } catch (Exception e) {
            System.out.println("Error creando tablas: " + e.getMessage());
        }
  }
}

    

