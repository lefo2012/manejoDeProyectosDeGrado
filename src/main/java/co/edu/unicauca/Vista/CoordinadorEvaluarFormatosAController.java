package co.edu.unicauca.Vista;

import co.edu.unicauca.Models.Estudiante;
import co.edu.unicauca.Models.FormatoA;
import co.edu.unicauca.Models.Profesor;
import co.edu.unicauca.Services.ProyectoService;
import co.edu.unicauca.main.Main;
import java.awt.Desktop;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class CoordinadorEvaluarFormatosAController {

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
    @FXML
    private Button botonAceptarFormato;

    @FXML
    private Button botonRechazarFormato;
    
    private FormatoA formato;
    int idCoordinador;
    Boolean bandera=false;
    LocalDate hoy = LocalDate.now();
    String fecha = hoy.format(DateTimeFormatter.ISO_DATE);
    private ProyectoService proyectoService;

    public void setProyectoService(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    public void initialize()
    {
            
    }
    @FXML
    void aceptarFormato(ActionEvent event) {
        try {
            bandera=proyectoService.aceptarProyecto(formato.getIdProyecto(), idCoordinador, textAreaObservaciones.getText(), fecha);
            System.out.println("Proyecto aceptado con comentario");
            textAreaObservaciones.setText("");
            if(bandera){
                Main.goCoordinador();
            }
        }catch (Exception e) {
           e.printStackTrace();
        }
    }
    
    @FXML
    void rechazarFormato(ActionEvent event) {
        try {
            bandera=proyectoService.rechazarProyecto(formato, idCoordinador, textAreaObservaciones.getText(),fecha);
            System.out.println("Proyecto rechazado con comentario");
            textAreaObservaciones.setText("");
            if(bandera){
                Main.goCoordinador();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void salir(ActionEvent event) {
        Main.goCoordinador();
    }

    @FXML
    void verDocumento(ActionEvent event) {
        if (formato != null && formato.getArchivoAdjunto() != null) {
            try {
                File file = new File(formato.getArchivoAdjunto()); // aquí tienes la ruta completa

                if (!file.exists()) {
                    System.out.println("No se encontró el archivo en: " + file.getAbsolutePath());
                    return;
                }

                // Abrir con el visor de PDF predeterminado del SO
                Desktop.getDesktop().open(file);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    
    public void setFormato(FormatoA formato, int idCoordinador) {
        this.formato = formato;
        this.idCoordinador=idCoordinador;

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
        
        if (proyectoService.verificarEStado(formato.getEstado())) {
            botonAceptarFormato.setVisible(false);
            botonRechazarFormato.setVisible(false);
            try {
                textAreaObservaciones.setText(obtenerComentario());
                textAreaObservaciones.setEditable(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
       }
        else{
            botonAceptarFormato.setVisible(true);
            botonRechazarFormato.setVisible(true);
            textAreaObservaciones.setText("");
            textAreaObservaciones.setEditable(true);
        }
    }
    
    public String obtenerComentario() throws Exception {
        return proyectoService.obtenerComentarioProyecto(formato.getIdProyecto());
    }
    
    

}