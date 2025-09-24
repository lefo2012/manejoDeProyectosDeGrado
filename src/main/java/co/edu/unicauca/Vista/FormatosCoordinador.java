package co.edu.unicauca.Vista;


import co.edu.unicauca.Factorys.RepositoryFactory;
import co.edu.unicauca.Models.Coordinador;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FormatosCoordinador implements Initializable, Observer{
    @FXML
    private VBox contactsLayout;
    private Coordinador coordinador=null;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
    }

    public void salir() throws IOException  {
        Main.goLogin();
    }
    @Override
    public void update(Object o) {
        if (o instanceof PersonaService personaService) {
            if (personaService.getPersona() instanceof Coordinador) {
            System.out.println("update 1");
            this.coordinador = (Coordinador) personaService.getPersona();
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
            List<FormatoA> formatos = proyectoService.obtenerProyectosCoordinador(coordinador.getId());

            for (FormatoA formato : formatos) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DatosFormatoCoordinador.fxml"));
                HBox hBox = loader.load();

                DatosFormatoController datosFormatosContoller = loader.getController();
                datosFormatosContoller.setData(formato,coordinador.getId());
                contactsLayout.getChildren().add(hBox);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }
}
