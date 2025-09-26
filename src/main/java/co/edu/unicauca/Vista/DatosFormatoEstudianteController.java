/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.Vista;

import co.edu.unicauca.Models.Estudiante;
import co.edu.unicauca.Models.FormatoA;
import co.edu.unicauca.main.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 *
 * @author PixelBot Gaming
 */
public class DatosFormatoEstudianteController {
    

    @FXML
    private Label lblEstado;

    @FXML
    private Label lblFecha;

    @FXML
    private Label lblFormato;

    @FXML
    private Label lblName,lblName1;
    private FormatoA formatoSeleccionado;
    
    
   
    public void setData(FormatoA formato) {

        this.formatoSeleccionado = formato;
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
        if(formato.getEstudiantes().size()>1)
        {
            if(formato.getEstudiantes().get(1)!=null)
            {
               lblName.setText("1. "+formato.getEstudiantes().get(0).getNombre());
               lblName1.setText("2. "+formato.getEstudiantes().get(1).getNombre());
            }
        }else
        {
            lblName1.setManaged(false);
            lblName.setLayoutY(25);
            lblName.setText("1. "+formato.getEstudiantes().get(0).getNombre());
        }
        


        lblEstado.getStyleClass().removeAll("label-pendiente", "label-revision", "label-aprobado", "label-rechazado");
        switch (formato.getEstado().toLowerCase()) {
            case "pendiente" -> lblEstado.getStyleClass().add("label-pendiente");
            case "en revisión" -> lblEstado.getStyleClass().add("label-revision");
            case "aprobado" -> lblEstado.getStyleClass().add("label-aprobado");
            case "rechazado" -> lblEstado.getStyleClass().add("label-rechazado");
            default -> {} // sin estilo extra
        }
    }
    
    
    public void verDetalles() throws IOException{
        Main.goVerFormatoA(formatoSeleccionado);
    }
    
}
