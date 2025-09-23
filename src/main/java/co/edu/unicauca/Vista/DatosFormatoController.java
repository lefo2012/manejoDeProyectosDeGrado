package co.edu.unicauca.Vista;

import co.edu.unicauca.Models.Estudiante;
import co.edu.unicauca.Models.FormatoA;
import co.edu.unicauca.Models.Prueba;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;

public class DatosFormatoController implements Initializable{

    @FXML
    private Label lblDetalles;

    @FXML
    private Label lblEstado;

    @FXML
    private Label lblFecha;

    @FXML
    private Label lblFormato;

    @FXML
    private Label lblName;

    public void setData(FormatoA formato) {
        lblFormato.setText(String.valueOf(formato.getIdProyecto()));
        lblFecha.setText(formato.getFechaDeSubida());
        lblEstado.setText(formato.getEstado());

        StringBuilder nombres = new StringBuilder();
        for (Estudiante est : formato.getEstudiantes()) {
            if (nombres.length() > 0) {
                nombres.append("\n");
            }
            nombres.append(est.getNombre());
        }
    lblName.setText(nombres.toString());


        lblEstado.getStyleClass().removeAll("label-pendiente", "label-revision", "label-aprobado", "label-rechazado");
        switch (formato.getEstado().toLowerCase()) {
            case "pendiente" -> lblEstado.getStyleClass().add("label-pendiente");
            case "en revisión" -> lblEstado.getStyleClass().add("label-revision");
            case "aprobado" -> lblEstado.getStyleClass().add("label-aprobado");
            case "rechazado" -> lblEstado.getStyleClass().add("label-rechazado");
            default -> {} // sin estilo extra
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
