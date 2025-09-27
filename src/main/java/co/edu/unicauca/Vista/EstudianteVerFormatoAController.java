package co.edu.unicauca.Vista;

import co.edu.unicauca.Models.Estudiante;
import co.edu.unicauca.Models.FormatoA;
import co.edu.unicauca.Models.Profesor;
import co.edu.unicauca.main.Main;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 *
 * @author LEFO
 */
public class EstudianteVerFormatoAController {
    
    @FXML
    private Label textAreaObjetivoGeneral;

    @FXML
    private Label textAreaObjetivosEspecificos;

    @FXML
    private Label textFieldDirector;
    
    @FXML
    private Label textFieldCodirector;

    @FXML
    private Label textFieldEstudiante;

    @FXML
    private Label textFieldEstudiante1;

    @FXML
    private Label textFieldModalidad;

    @FXML
    private Label textFieldTituloProyecto;
    
    @FXML
    private TextArea textAreaObservaciones;
    
    
    
    private FormatoA formato;


    

    @FXML
    public void verDocumento(ActionEvent event) {
        if (formato != null && formato.getArchivoAdjunto() != null) {
            try {
                File file = new File(formato.getArchivoAdjunto()); 

                if (!file.exists()) {
                    System.out.println("No se encontró el archivo en: " + file.getAbsolutePath());
                    return;
                }

                // Abrir con el visor de PDF predeterminado del SO
                Desktop.getDesktop().open(file);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    
    public void setFormato(FormatoA formato) {
        this.formato = formato;
        textFieldTituloProyecto.setText(formato.getTitulo());
        textFieldModalidad.setText(formato.getTipo().name());
        textAreaObjetivoGeneral.setText(formato.getObjetivo());
        textAreaObjetivosEspecificos.setText(formato.getObjetivoEspecifico());
        List<Profesor> prof = formato.getProfesores();
        if (!prof.isEmpty()) textFieldDirector.setText(prof.get(0).getNombre());
        if (prof.size() > 1) textFieldCodirector.setText(prof.get(1).getNombre());

        List<Estudiante> ests = formato.getEstudiantes();
        if (!ests.isEmpty()) textFieldEstudiante.setText(ests.get(0).getNombre());
        if (ests.size() > 1) textFieldEstudiante1.setText(ests.get(1).getNombre());
  
    }
    @FXML
    public void cerrarSesion(ActionEvent event) {
        Main.goLogin();
    }
    public void salir()
    {
        Main.goEstudianteFormatos();
    }
}
