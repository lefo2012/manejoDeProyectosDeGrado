package co.edu.unicauca.Repository.Implementation;

import co.edu.unicauca.Models.Estudiante;
import co.edu.unicauca.Models.FormatoA;
import co.edu.unicauca.Models.Profesor;
import co.edu.unicauca.Repository.ProyectoRepository;
import co.edu.unicauca.database.ConexionSQLite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
        PreparedStatement statementEstudiante = conexionBaseDatos.prepareStatement("Select idPersona from persona where correoElectronico = ?");
        statementEstudiante.setString(1, e.getCorreoElectronico());
        ResultSet rs = statementEstudiante.executeQuery();
        if(!rs.next())
        {
            throw new Exception("Error al obtener el estudiante con correo "+e.getCorreoElectronico());
        }
        e.setId(rs.getInt("idPersona"));
        
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
            PreparedStatement statementEstudiante = conexionBaseDatos.prepareStatement("Select idPersona from persona where correoElectronico = ?");
            statementEstudiante.setString(1, p.get(1).getCorreoElectronico());
            ResultSet rs = statementEstudiante.executeQuery();
            if(!rs.next())
            {
                throw new Exception("Error al obtener el profesor con correo "+p.get(1).getCorreoElectronico());
            }
            p.get(1).setId(rs.getInt("idPersona"));
            
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
    
    
}
