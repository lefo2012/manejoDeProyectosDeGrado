/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.Vista;

import co.edu.unicauca.Factorys.RepositoryFactory;
import co.edu.unicauca.Models.Estudiante;
import co.edu.unicauca.Models.FormatoA;
import co.edu.unicauca.Observer.Observer;
import co.edu.unicauca.Repository.ProyectoRepository;
import co.edu.unicauca.Services.PersonaService;
import co.edu.unicauca.Services.ProyectoService;
import co.edu.unicauca.main.Main;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 *
 * @author PixelBot Gaming
 */
public class EstudianteFormatosController implements Observer{
    @FXML
    private VBox contactsLayout;
    @FXML
    private Pane panelFormatoNoti;
    @FXML
    private StackPane panelFormatoNotiOk;
    
    private Estudiante estudiante=null;
   
    public void initialize(URL url, ResourceBundle rb) {

        
    }

    public void salir() throws IOException  {
        Main.goLogin();
    }
    public void update(Object o) {
        
        if (o instanceof PersonaService personaService) {
            if (personaService.getPersona() instanceof Estudiante) {
            System.out.println("update 1");
            this.estudiante = (Estudiante) personaService.getPersona();
            actualizarFormatos();
        }
        }else if (o instanceof ProyectoService proyectoService) {
            actualizarFormatos();
            System.out.println("update 2");
        }
        else if (o instanceof FormatoA formato) {
            if (this.estudiante == null) {
                System.out.println("[EstudianteFormatos] Ignorando FormatoA: no hay estudiante logueado");
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
                    actualizarFormatos();
                    informacionFormato();
                });
            }
        }
    }
    
    public void actualizarFormatos(){
        if(estudiante == null)
        {
            return;
        }
        RepositoryFactory<ProyectoRepository> repositoryProgramaFactory = new RepositoryFactory(ProyectoRepository.class);
        ProyectoService proyectoService = new ProyectoService(repositoryProgramaFactory.getInstance());
            
        try {
            if (contactsLayout.getChildren().size() > 1) {
            contactsLayout.getChildren().remove(1, contactsLayout.getChildren().size());
            }
            List<FormatoA> formatos = proyectoService.obtenerProyectosEstudiante(estudiante.getId());

            for (FormatoA formato : formatos) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EstudianteDatosFormato.fxml"));
                HBox hBox = loader.load();

                EstudianteDatosFormatoController datosFormatosContoller = loader.getController();
                datosFormatosContoller.setData(formato);
                contactsLayout.getChildren().add(hBox);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    public void informacionFormato() {
        panelFormatoNoti.setManaged(true);
        panelFormatoNotiOk.setManaged(true);

        panelFormatoNoti.setVisible(true);
        panelFormatoNotiOk.setVisible(true);

        panelFormatoNoti.toFront();
        panelFormatoNotiOk.toFront();

        PauseTransition delay = new PauseTransition(Duration.seconds(1.5));
        delay.setOnFinished(e -> {
            panelFormatoNoti.setVisible(false);
            panelFormatoNotiOk.setVisible(false);
            panelFormatoNoti.setManaged(false);
            panelFormatoNotiOk.setManaged(false);
        });
        delay.play();
    }
    
    public void goEstudianteNotificaciones(){
        Main.goEstudianteNotificaciones();
    }
}
