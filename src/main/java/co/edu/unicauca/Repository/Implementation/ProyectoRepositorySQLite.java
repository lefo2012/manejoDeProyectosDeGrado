package co.edu.unicauca.Repository.Implementation;

import co.edu.unicauca.Models.Estudiante;
import co.edu.unicauca.Models.FormatoA;
import co.edu.unicauca.Models.Profesor;
import co.edu.unicauca.Repository.ProyectoRepository;
import co.edu.unicauca.Util.Tipo;
import co.edu.unicauca.database.ConexionSQLite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author LEFO
 */
public class ProyectoRepositorySQLite implements ProyectoRepository{

    @Override
    public List<FormatoA> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public FormatoA getOne(Integer id) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean save(FormatoA object) throws Exception {
        Connection conexionBaseDatos = null;
        try
        {
            conexionBaseDatos = ConexionSQLite.getInstance();
            conexionBaseDatos.setAutoCommit(false);
            PreparedStatement statamentProyecto = conexionBaseDatos.prepareStatement("insert into proyecto(titulo,objetivo,objetivoEspecifico,estado,tipo,fechaDeSubida,archivoAdjunto) values(?,?,?,?,?,?,?)");
            statamentProyecto.setString(1,object.getTitulo());
            statamentProyecto.setString(2, object.getObjetivo());
            statamentProyecto.setString(3, object.getObjetivoEspecifico());
            statamentProyecto.setString(4, object.getEstado());
            statamentProyecto.setString(5, object.getTipo().name());
            statamentProyecto.setString(6, object.getFechaDeSubida());
            statamentProyecto.setString(7, object.getArchivoAdjunto());
            
            int affected = statamentProyecto.executeUpdate();
            if (affected == 0) throw new SQLException("No se insertó proyecto.");
            int idProyecto;
                    try (ResultSet generatedKeys = statamentProyecto.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            idProyecto = generatedKeys.getInt(1);
                        } else {
                            System.out.println("error al obtener id");
                            throw new SQLException("Error al obtener ID del proyecto.");
                        }
                    }
            
            for(Estudiante e : object.getEstudiantes())
            {
                if(e!=null)
                {
                    enlazarEstudiante(idProyecto,e);
                }
            }
            
            enlazarProfesor(idProyecto, object.getProfesores());
            enlazarCoordinador(idProyecto,object.getProfesores().get(0).getDepartamento().getCodigoDepartamento());
            conexionBaseDatos.commit();
            return true;
        }catch(Exception e)
        {
            conexionBaseDatos.rollback();
            
            throw e;
            
        }finally
        {
            ConexionSQLite.desconectar();
        }
        
    }
    public boolean enlazarEstudiante(int idProyecto,Estudiante e) throws SQLException, Exception
    {
        
        Connection conexionBaseDatos = ConexionSQLite.getInstance();
        PreparedStatement statementEstudiante = conexionBaseDatos.prepareStatement("Select idEstudiante from Estudiante where correoElectronico = ?");
        statementEstudiante.setString(1, e.getCorreoElectronico());
        ResultSet rs = statementEstudiante.executeQuery();
        if(!rs.next())
        {
            throw new Exception("Error al obtener el estudiante con correo "+e.getCorreoElectronico());
        }
        e.setId(rs.getInt("idEstudiante"));
        
        PreparedStatement statamentProyecto = conexionBaseDatos.prepareStatement("insert into ProyectosEstudiante(idEstudiante,idProyecto) values(?,?)");
        statamentProyecto.setInt(1,e.getId());
        statamentProyecto.setInt(2, idProyecto);
        
        int filasAfectadas = statamentProyecto.executeUpdate();
        if(filasAfectadas>0)
        {
            return true;
        }else
        {
            throw new Exception("Error al enlazar al estudiante con el proyecto");
        }
    }
    public boolean enlazarCoordinador(int idProyecto,int idDepartamento) throws Exception
    {
        Connection conexionBaseDatos = ConexionSQLite.getInstance();
        PreparedStatement statamentCoordinador = conexionBaseDatos.prepareStatement("select idCoordinador from coordinador where idDepartamento= ? ");
        statamentCoordinador.setInt(1,idDepartamento);
        ResultSet rs = statamentCoordinador.executeQuery();
        if(!rs.next())
        {
            throw new Exception("Error al enlazar al coordinador con el proyecot");
        }
        PreparedStatement statamentProyecto = conexionBaseDatos.prepareStatement("insert into ProyectosCoordinador(idCoordinador,idProyecto) values(?,?)");
        
        statamentProyecto.setInt(1,rs.getInt("idCoordinador"));
        statamentProyecto.setInt(2, idProyecto);
        int filasAfectadas = statamentProyecto.executeUpdate();
        if(filasAfectadas>0)
        {
            return true;
        }else
        {
            throw new Exception("Error al enlazar al coordinador con el proyecto");
        }
    }
    public boolean enlazarProfesor(int idProyecto,List<Profesor> p) throws SQLException, Exception
    {
        Connection conexionBaseDatos = ConexionSQLite.getInstance();
        PreparedStatement statamentProyecto;
        if(p.size()>1)
        {
            PreparedStatement statementEstudiante = conexionBaseDatos.prepareStatement("Select idProfesor from Profesor where correoElectronico = ?");
            statementEstudiante.setString(1, p.get(1).getCorreoElectronico());
            ResultSet rs = statementEstudiante.executeQuery();
            if(!rs.next())
            {
                throw new Exception("Error al obtener el profesor con correo "+p.get(1).getCorreoElectronico());
            }
            p.get(1).setId(rs.getInt("idProfesor"));
            
            statamentProyecto = conexionBaseDatos.prepareStatement("insert into ProyectosProfesor(idDirector,idCodirector,idProyecto) values(?,?,?)");
            statamentProyecto.setInt(1,p.get(0).getId());
            statamentProyecto.setInt(2, p.get(1).getId());
            statamentProyecto.setInt(3, idProyecto);
            statamentProyecto.executeUpdate();
            
            return true;
        }
        
        statamentProyecto = conexionBaseDatos.prepareStatement("insert into ProyectosProfesor(idDirector,idProyecto) values(?,?)");
        statamentProyecto.setInt(1, p.get(0).getId());
        statamentProyecto.setInt(2, idProyecto);
            
        int filasAfectadas = statamentProyecto.executeUpdate();
        if(filasAfectadas>0)
        {
            return true;
        }else
        {
            throw new Exception("Error al enlazar a los profesores con el proyecto");
        }
    
    }
    @Override
    public boolean consultarPosibilidad(FormatoA formatoa) throws Exception
    {
        
        if(formatoa.getTipo().equals(Tipo.PracticaProfesional))
        {
            consultarPosibilidadEstudiante(formatoa.getEstudiantes().get(0),formatoa.getTipo());
        }
        else if(formatoa.getTipo().equals(Tipo.Investigacion))
        {
            System.out.println(formatoa.getEstudiantes().size());
            if(formatoa.getEstudiantes().size()>1)
            {
                consultarPosibilidadEstudiante(formatoa.getEstudiantes().get(0),formatoa.getTipo());
                if(formatoa.getEstudiantes().get(1)!=null)
                {
                    consultarPosibilidadEstudiante(formatoa.getEstudiantes().get(1),formatoa.getTipo());
                    
                }
                
            }
            consultarPosibilidadEstudiante(formatoa.getEstudiantes().get(0),formatoa.getTipo());
        }
        return false;
    }
    
    public void consultarPosibilidadEstudiante(Estudiante estudiante,Tipo tipo) throws Exception
    {
        if(!esNuevo(estudiante)){
            Connection conexionBaseDatos = ConexionSQLite.getInstance();
            PreparedStatement statementEstudiante = conexionBaseDatos.prepareStatement("Select idPersona from persona where correoElectronico = ?");
            statementEstudiante.setString(1, estudiante.getCorreoElectronico());
            ResultSet rs = statementEstudiante.executeQuery();
            if(!rs.next())
            {
                throw new Exception("Error al obtener el estudiante con correo "+estudiante.getCorreoElectronico());
            }
            estudiante.setId(rs.getInt("idPersona"));
            PreparedStatement statementConsultaPosibilidad = conexionBaseDatos.prepareStatement("Select Estudiante.cantidadIntentosPractica,Estudiante.cantidadIntentosInvestigacion,Proyecto.estado from Estudiante inner join ProyectosEstudiante on Estudiante.idEstudiante = ProyectosEstudiante.idEstudiante "
                    + "inner join Proyecto on ProyectosEstudiante.idProyecto = Proyecto.idProyecto where Estudiante.idEstudiante = ?");
            statementConsultaPosibilidad.setInt(1, estudiante.getId());
            rs = statementConsultaPosibilidad.executeQuery();
            if(!rs.next())
            {
            
                throw new Exception("Error al obtener la consulta de verificacion con el correo del estudiante  "+estudiante.getCorreoElectronico());
            
            }
            int cantidadIntentosPractica = rs.getInt("cantidadIntentosPractica");
            int cantidadIntentosInvestigacion = rs.getInt("cantidadIntentosInvestigacion");
            String estado = rs.getString("estado");
            
            if(estado.equals("PENDIENTE"))
                throw new Exception("El estudiante con correo "+estudiante.getCorreoElectronico()+" tiene actualmente un formato pendiente de revisar");
            else if(estado.equals("APROBADO"))
                throw new Exception("El estudiante con correo "+estudiante.getCorreoElectronico()+" tiene actualmente un formato aprobado");
            
            if(tipo.equals(Tipo.PracticaProfesional)){
                if(cantidadIntentosPractica==3)
                {

                    throw new Exception("El estudiante con correo "+estudiante.getCorreoElectronico()+"supero la cantidad de intentos en la modalidad de practica profesional");

                }
            }else if(tipo.equals(Tipo.Investigacion))
            {
                if(cantidadIntentosInvestigacion==3)
                {

                    throw new Exception("El estudiante con correo "+estudiante.getCorreoElectronico()+"supero la cantidad de intentos en la modalidad de investigacion");

                }
            
            }
        }
    }
    public boolean esNuevo(Estudiante estudiante) throws Exception
    {
        Connection conexionBaseDatos = ConexionSQLite.getInstance();
        PreparedStatement statementEstudiante = conexionBaseDatos.prepareStatement("Select idPersona from persona where correoElectronico = ?");
        statementEstudiante.setString(1, estudiante.getCorreoElectronico());
        ResultSet rs = statementEstudiante.executeQuery();
        if(!rs.next())
        {
            throw new Exception("Error al obtener el estudiante con correo "+estudiante.getCorreoElectronico());
        }
        estudiante.setId(rs.getInt("idPersona"));
            PreparedStatement statementConsultaPosibilidad = conexionBaseDatos.prepareStatement("Select * from Estudiante inner join ProyectosEstudiante on Estudiante.idEstudiante = ProyectosEstudiante.idEstudiante where Estudiante.idEstudiante=?");
            statementConsultaPosibilidad.setInt(1, estudiante.getId());
            rs = statementConsultaPosibilidad.executeQuery();
            return !rs.next();
    }
    @Override
    public List<FormatoA> getProyectosCoordinador(int idCoordinador) throws Exception {
        String sql = "SELECT idProyecto FROM ProyectosCoordinador WHERE idCoordinador = ?";
        List<Integer> ids = new ArrayList<>();

        try (Connection conn = ConexionSQLite.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idCoordinador);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) ids.add(rs.getInt("idProyecto"));
            }
        } // <-- aquí se cierran conn/stmt/rs

        List<FormatoA> formatos = new ArrayList<>();
        for (int idProyecto : ids) {
            formatos.add(getProyecto(idProyecto)); // abre su propia conexión interna
        }
        return formatos;
    }
    @Override
    public List<FormatoA> getProyectosEstudiante(int idEstudiante) throws Exception {
        String sql = "SELECT idProyecto FROM ProyectosEstudiante WHERE idEstudiante = ?";
        List<Integer> ids = new ArrayList<>();

        try (Connection conn = ConexionSQLite.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idEstudiante);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) ids.add(rs.getInt("idProyecto"));
            }
        } // <-- aquí se cierran conn/stmt/rs

        List<FormatoA> formatos = new ArrayList<>();
        for (int idProyecto : ids) {
            formatos.add(getProyecto(idProyecto)); // abre su propia conexión interna
        }
        return formatos;
    }
    @Override
    public List<FormatoA> getProyectosProfesor(int idProfesor) throws Exception {
        String sql = "SELECT idProyecto FROM ProyectosProfesor WHERE idDirector = ?";
        List<Integer> ids = new ArrayList<>();

        try (Connection conn = ConexionSQLite.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProfesor);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) ids.add(rs.getInt("idProyecto"));
            }
        } 

        List<FormatoA> formatos = new ArrayList<>();
        for (int idProyecto : ids) {
            formatos.add(getProyecto(idProyecto)); 
        }
        return formatos;
    }
    public FormatoA getProyecto(int idProyecto) throws Exception {
        FormatoA proyecto = null;
        String sql ="SELECT p.idProyecto, p.titulo, p.objetivo, p.objetivoEspecifico, " +
                    "p.estado, p.tipo, p.fechaDeSubida, p.archivoAdjunto, " +
                    "e.idEstudiante, per.nombre || ' ' || per.apellido AS nombreEstudiante, " +
                    "pp.idDirector, perDir.nombre || ' ' || perDir.apellido AS nombreDirector, " +
                    "pp.idCodirector, perCo.nombre || ' ' || perCo.apellido AS nombreCodirector " +
                    "FROM Proyecto p " +
                    "INNER JOIN ProyectosCoordinador pc ON p.idProyecto = pc.idProyecto " +
                    "LEFT JOIN ProyectosEstudiante pe ON p.idProyecto = pe.idProyecto " +
                    "LEFT JOIN Estudiante e ON pe.idEstudiante = e.idEstudiante " +
                    "LEFT JOIN Persona per ON e.idEstudiante = per.idPersona " +
                
                    "LEFT JOIN ProyectosProfesor pp ON p.idProyecto = pp.idProyecto " +
                    "LEFT JOIN Profesor profDir ON pp.idDirector = profDir.idProfesor " +
                    "LEFT JOIN Persona perDir ON profDir.idProfesor = perDir.idPersona " +
                
                    "LEFT JOIN Profesor profCo ON pp.idCodirector = profCo.idProfesor " +
                    "LEFT JOIN Persona perCo ON profCo.idProfesor = perCo.idPersona " +
                    "WHERE pe.idProyecto = ? " +
                    "ORDER BY p.idProyecto";

        try (Connection conn = ConexionSQLite.getInstance();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, idProyecto);
            
            try (ResultSet rs = stmt.executeQuery()) {
                Set<Integer> estAgregados  = new HashSet<>();
                Set<Integer> profAgregados = new HashSet<>();
                while(rs.next()){
                    if (proyecto == null) {
                        proyecto = new FormatoA();
                        proyecto.setIdProyecto(idProyecto);
                        proyecto.setTitulo(rs.getString("titulo"));
                        proyecto.setObjetivo(rs.getString("objetivo"));
                        proyecto.setObjetivoEspecifico(rs.getString("objetivoEspecifico"));
                        proyecto.setEstado(rs.getString("estado"));
                        proyecto.setTipo(Tipo.valueOf(rs.getString("tipo")));
                        proyecto.setFechaDeSubida(rs.getString("fechaDeSubida"));
                        proyecto.setArchivoAdjunto(rs.getString("archivoAdjunto"));
                        proyecto.setEstudiantes(new ArrayList<>());
                        proyecto.setProfesores(new ArrayList<>());
                    
                        
                    }
                    int idEst = rs.getInt("idEstudiante");
                    if (idEst != 0 && estAgregados.add(idEst)) {
                        Estudiante est = new Estudiante();
                        est.setId(idEst);
                        est.setNombre(rs.getString("nombreEstudiante"));
                        proyecto.getEstudiantes().add(est);
                    }
                    
                    int idDirector = rs.getInt("idDirector");
                    if (idDirector != 0 && profAgregados.add(idDirector)) {
                        Profesor director = new Profesor();
                        director.setId(idDirector);
                        director.setNombre(rs.getString("nombreDirector"));
                        proyecto.getProfesores().add(director);
                    }

                    int idCodirector = rs.getInt("idCodirector");
                    if (idCodirector != 0 && profAgregados.add(idDirector)) {
                        Profesor codirector = new Profesor();
                        codirector.setId(idCodirector);
                        codirector.setNombre(rs.getString("nombreCodirector"));
                        proyecto.getProfesores().add(codirector);
                    }
                }
            }
        }

        return proyecto;
    }

    @Override
    public boolean aceptarProyecto(int idProyecto, int idCoordinador, String comentario, String fecha) throws Exception {
        try (Connection conn = ConexionSQLite.getInstance()) {
            conn.setAutoCommit(false);

            String sql = "UPDATE Proyecto SET estado = ?, fechaRevision = ? WHERE idProyecto = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, "APROBADO");
                ps.setString(2, fecha);
                ps.setInt(3, idProyecto);
                ps.executeUpdate();
            }

            insertarComentario(conn, comentario != null ? comentario : "Proyecto aceptado", idProyecto, idCoordinador);

            conn.commit();
            return true;
        } catch (Exception e) {
            throw e;
        }
    }
    
    @Override
    public boolean rechazarProyecto(FormatoA formato, int idCoordinador, String comentario, String fecha) throws Exception {
        try (Connection conn = ConexionSQLite.getInstance()) {
            conn.setAutoCommit(false);

            String sql = "UPDATE Proyecto SET estado = ?, fechaRevision = ? WHERE idProyecto = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, "RECHAZADO");
                ps.setString(2, fecha);
                ps.setInt(3, formato.getIdProyecto());
                ps.executeUpdate();
            }

            String sqlIntentos;
            if (formato.getTipo().equals(Tipo.Investigacion)) {
                sqlIntentos = "UPDATE Estudiante SET cantidadIntentosInvestigacion = cantidadIntentosInvestigacion + 1 " +
                              "WHERE idEstudiante IN (SELECT idEstudiante FROM ProyectosEstudiante WHERE idProyecto = ?)";
            } else {
                sqlIntentos = "UPDATE Estudiante SET cantidadIntentosPractica = cantidadIntentosPractica + 1 " +
                              "WHERE idEstudiante IN (SELECT idEstudiante FROM ProyectosEstudiante WHERE idProyecto = ?)";
            }

            try (PreparedStatement ps2 = conn.prepareStatement(sqlIntentos)) {
                ps2.setInt(1, formato.getIdProyecto());
                ps2.executeUpdate();
            }

            insertarComentario(conn, comentario != null ? comentario : "Proyecto rechazado", formato.getIdProyecto(), idCoordinador);

            conn.commit();
            return true;
        } catch (Exception e) {
            throw e;
        }
    }
    private void insertarComentario(Connection conn, String contenido, int idProyecto, int idCoordinador) throws Exception {
        String sql = "INSERT INTO Comentario (contenido, fecha, idProyecto, idCoordinador) " +
                     "VALUES (?, DATE('now'), ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, contenido);
            ps.setInt(2, idProyecto);
            ps.setInt(3, idCoordinador);
            ps.executeUpdate();
        }
    }
    @Override
    public String obtenerComentarioProyecto(int idProyecto) throws Exception {
        String comentario = null;

        String sql = "SELECT contenido " +
                     "FROM Comentario " +
                     "WHERE idProyecto = ? " +
                     "ORDER BY fecha DESC LIMIT 1"; 

        try (Connection conn = ConexionSQLite.getInstance();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProyecto);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    comentario = rs.getString("contenido");
                }
            }
        }

        return comentario;
    }

    
}
