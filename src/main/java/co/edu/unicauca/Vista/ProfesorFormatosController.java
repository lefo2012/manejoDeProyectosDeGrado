package co.edu.unicauca.Vista;

import co.edu.unicauca.Factorys.RepositoryFactory;
import co.edu.unicauca.Models.Estudiante;
import co.edu.unicauca.Models.FormatoA;
import co.edu.unicauca.Models.Profesor;
import co.edu.unicauca.Observer.Observer;
import co.edu.unicauca.Repository.ProyectoRepository;
import co.edu.unicauca.Services.PersonaService;
import co.edu.unicauca.Services.ProyectoService;
import co.edu.unicauca.main.Main;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author LEFO
 */
public class ProfesorFormatosController implements Observer {
    @FXML
    private VBox contactsLayout;
    private Profesor profesor=null;
   
    public void initialize(URL url, ResourceBundle rb) {

        
    }

    public void salir() throws IOException  {
        Main.goLogin();
    }
    public void update(Object o) {
        
        if (o instanceof PersonaService personaService) {
            if (personaService.getPersona() instanceof Profesor) {
            System.out.println("update 1");
            this.profesor = (Profesor) personaService.getPersona();
            actualizarFormatos();
        }
        }else if (o instanceof ProyectoService proyectoService) {
            actualizarFormatos();
            System.out.println("update 2");
        }
    }
    
    public void actualizarFormatos(){
        RepositoryFactory<ProyectoRepository> repositoryProgramaFactory = new RepositoryFactory(ProyectoRepository.class);
        ProyectoService proyectoService = new ProyectoService(repositoryProgramaFactory.getInstance("SQLite"));
            
        try {
            if (contactsLayout.getChildren().size() > 1) {
            contactsLayout.getChildren().remove(1, contactsLayout.getChildren().size());
            }
            List<FormatoA> formatos = proyectoService.obtenerProyectosProfesor(profesor.getId());

            for (FormatoA formato : formatos) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProfesorDatosFormato.fxml"));
                HBox hBox = loader.load();

                ProfesorDatosFormatoController datosFormatosContoller = loader.getController();
                datosFormatosContoller.setData(formato);
                contactsLayout.getChildren().add(hBox);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
    
}
