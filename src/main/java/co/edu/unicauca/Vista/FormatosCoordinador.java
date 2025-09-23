package co.edu.unicauca.Vista;


import co.edu.unicauca.Factorys.RepositoryFactory;
import co.edu.unicauca.Models.Coordinador;
import co.edu.unicauca.Models.FormatoA;
import co.edu.unicauca.Observer.Observer;
import co.edu.unicauca.Repository.ProyectoRepository;
import co.edu.unicauca.Services.PersonaService;
import co.edu.unicauca.Services.ProyectoService;
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

    @Override
    public void update(Object o) {
        PersonaService personaService = (PersonaService) o;
            if(personaService.getPersona() instanceof Coordinador){
            this.coordinador = (Coordinador) personaService.getPersona();
            RepositoryFactory<ProyectoRepository> repositoryProgramaFactory = new RepositoryFactory(ProyectoRepository.class);
            ProyectoService proyectoService = new ProyectoService(repositoryProgramaFactory.getInstance("SQLite"));

            try {
                System.out.println(""+coordinador.getId());
                List<FormatoA> formatos = proyectoService.obtenerProyectosCoordinador(coordinador.getId());

                for (FormatoA formato : formatos) {
                    System.out.println(""+formato.getTitulo());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DatosFormatoCoordinador.fxml"));
                    HBox hBox = loader.load();

                    DatosFormatoController dfc = loader.getController();
                    dfc.setData(formato);

                    contactsLayout.getChildren().add(hBox);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
}
