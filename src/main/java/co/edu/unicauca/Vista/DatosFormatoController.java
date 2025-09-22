package co.edu.unicauca.Vista;

import co.edu.unicauca.Models.Prueba;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

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

    public void  setData(Prueba prueba){
        lblFormato.setText(prueba.getId());
        lblName.setText(prueba.getName());
        lblFecha.setText(prueba.getFecha());
        lblEstado.setText(prueba.getEstado());
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
