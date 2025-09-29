package co.edu.unicauca.Vista;

import co.edu.unicauca.Factorys.RepositoryFactory;
import co.edu.unicauca.Models.Estudiante;
import co.edu.unicauca.Models.FormatoA;
import co.edu.unicauca.Models.Notificacion;
import co.edu.unicauca.Models.Profesor;
import co.edu.unicauca.Observer.Observer;
import co.edu.unicauca.Repository.ProyectoRepository;
import co.edu.unicauca.Services.PersonaService;
import co.edu.unicauca.Services.ProyectoService;
import co.edu.unicauca.main.Main;
import java.util.List;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Nicolas
 */
public class ProfesorNotificacionesController implements Observer{
    @FXML
    private VBox contactsLayout;
    private Profesor profesor=null;
    
    
    @Override
    public void update(Object o) {
        if (o instanceof PersonaService personaService) {
            if (personaService.getPersona() instanceof Profesor) {
            System.out.println("update 1");
            this.profesor = (Profesor) personaService.getPersona();
            actualizarNotificaciones();
        }
        }else if (o instanceof ProyectoService proyectoService) {
            actualizarNotificaciones();
            System.out.println("update 2");
        }
        else if (o instanceof FormatoA formato) {
            if (this.profesor == null) {
                System.out.println("[ProfesorNoti] Ignorando Noti: no hay profesor logueado");
                return;
            }
            boolean pertenece = false;
            for (Estudiante est : formato.getEstudiantes()) {
                if (est.getId() == profesor.getId()) 
                {
                    pertenece = true;
                    break;
                }
            }
            if (pertenece) 
            {
                Platform.runLater(() -> {
                    actualizarNotificaciones();
                });
            }
        }
    }
    
    public void actualizarNotificaciones() {
        if (profesor == null) return;

        RepositoryFactory<ProyectoRepository> repositoryFactory =new RepositoryFactory<>(ProyectoRepository.class);
        ProyectoService proyectoService = new ProyectoService(repositoryFactory.getInstance());

        try {
            if (contactsLayout.getChildren().size() > 1) {
                contactsLayout.getChildren().remove(1, contactsLayout.getChildren().size());
            }

            List<Notificacion> notificaciones = proyectoService.obtenerNotificacionesPorPersona(profesor.getId());

            for (Notificacion noti : notificaciones) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProfesorDatosNotificaciones.fxml"));
                HBox hBox = loader.load();

                ProfesorDatosNotificacionesController notiController = loader.getController();
                notiController.setData(noti);

                contactsLayout.getChildren().add(hBox);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void goProfesorSubirFormato()
    {
        Main.goProfesorSubirFormato();
    }
    public void goProfesorFormatos()
    {
        Main.goProfesorFormatos();  
    }
    public void cerrarSesion() {
        Main.goLogin();
    }
}
