
package co.edu.unicauca.Vista;

import co.edu.unicauca.Factorys.RepositoryFactory;
import co.edu.unicauca.Models.Estudiante;
import co.edu.unicauca.Models.FormatoA;
import co.edu.unicauca.Models.Notificacion;
import co.edu.unicauca.Observer.Observer;
import co.edu.unicauca.Repository.ProyectoRepository;
import co.edu.unicauca.Services.PersonaService;
import co.edu.unicauca.Services.ProyectoService;
import co.edu.unicauca.main.Main;
import java.io.IOException;
import java.util.List;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EstudianteNotificacionesController implements Observer {

    @FXML
    private VBox contactsLayout;
    private Estudiante estudiante=null;
    
    public void cerrarSesion() throws IOException  {
        Main.goLogin();
    }
    
    @Override
    public void update(Object o) {
        if (o instanceof PersonaService personaService) {
            if (personaService.getPersona() instanceof Estudiante) {
            System.out.println("update 1");
            this.estudiante = (Estudiante) personaService.getPersona();
            actualizarNotificaciones();
        }
        }else if (o instanceof ProyectoService proyectoService) {
            actualizarNotificaciones();
            System.out.println("update 2");
        }
        else if (o instanceof FormatoA formato) {
            if (this.estudiante == null) {
                System.out.println("[EstudianteNoti] Ignorando Noti: no hay estudiante logueado");
                return;
            }
            boolean pertenece = false;
            for (Estudiante est : formato.getEstudiantes()) {
                if (est.getId() == estudiante.getId()) 
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
        if (estudiante == null) return;

        RepositoryFactory<ProyectoRepository> repositoryFactory =new RepositoryFactory<>(ProyectoRepository.class);
        ProyectoService proyectoService = new ProyectoService(repositoryFactory.getInstance());

        try {
            if (contactsLayout.getChildren().size() > 1) {
                contactsLayout.getChildren().remove(1, contactsLayout.getChildren().size());
            }

            List<Notificacion> notificaciones = proyectoService.obtenerNotificacionesPorPersona(estudiante.getId());

            for (Notificacion noti : notificaciones) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EstudianteDatosNotificaciones.fxml"));
                HBox hBox = loader.load();

                EstudianteDatosNotificacionesController notiController = loader.getController();
                notiController.setData(noti);

                contactsLayout.getChildren().add(hBox);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void goEstudianteFormatos(){
        Main.goEstudianteFormatos();
    }
    
}
