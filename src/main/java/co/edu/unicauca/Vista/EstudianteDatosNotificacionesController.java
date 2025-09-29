
package co.edu.unicauca.Vista;
import co.edu.unicauca.Factorys.RepositoryFactory;
import co.edu.unicauca.Models.FormatoA;
import co.edu.unicauca.Models.Notificacion;
import co.edu.unicauca.Repository.ProyectoRepository;
import co.edu.unicauca.Services.ProyectoService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EstudianteDatosNotificacionesController {
    
    @FXML
    private Label lblFormato;
    @FXML
    private HBox root;
    private Notificacion notificacion;
    
    public void setData(Notificacion noti) {

        this.notificacion = noti;
        lblFormato.setText("Tu "+ notificacion.getMensaje()+", fecha de revision: "+notificacion.getFecha());
    }
     public void leido(){
        try {
            RepositoryFactory<ProyectoRepository> factory =new RepositoryFactory<>(ProyectoRepository.class);
            ProyectoService service = new ProyectoService(factory.getInstance());

            service.marcarNotificacionComoLeida(notificacion.getIdNotificacion());

            ((VBox) root.getParent()).getChildren().remove(root);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }         
}
