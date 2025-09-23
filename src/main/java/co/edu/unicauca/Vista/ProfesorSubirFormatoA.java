
package co.edu.unicauca.Vista;


import co.edu.unicauca.Builders.Director;
import co.edu.unicauca.Builders.InvestigacionBuilder;
import co.edu.unicauca.Builders.PracticaBuilder;
import co.edu.unicauca.Factorys.RepositoryFactory;
import co.edu.unicauca.Models.Estudiante;
import co.edu.unicauca.Models.FormatoA;
import co.edu.unicauca.Models.Profesor;
import co.edu.unicauca.Observer.Observer;
import co.edu.unicauca.Repository.ProyectoRepository;
import co.edu.unicauca.Services.PersonaService;
import co.edu.unicauca.Services.ProyectoService;
import co.edu.unicauca.Util.ArchivosProyecto;
import co.edu.unicauca.Util.Tipo;
import co.edu.unicauca.Util.Validador;
import co.edu.unicauca.main.Main;

import java.io.File;



import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import javafx.animation.PauseTransition;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Duration;


/**
 *@FXML controller class
 * 
 * @author LEFO
 */
public class ProfesorSubirFormatoA implements Observer{
    @FXML
    Button botonSubirArchivo;
    @FXML
    Pane PanelSubirFormatoA,PaneSeleccionarModalidad,panelInformacionOk,panelInformacion;
    @FXML
    ComboBox<Tipo> comboBoxModalidad;
    @FXML
    Text textNombreArchivo,advertencia;
    @FXML
    ImageView imagenArchivoPlano,imagenPdf;
    @FXML
    TextField textFieldTituloProyecto,textFieldCoodirector,textFieldEstudiante,textFieldEstudiante1;
    @FXML
    TextArea textAreaObjetivosEspecificos,textAreaObjetivoGeneral;
    Profesor profesor=null;
    
    File archivo;
    
    
    
    public void initialize()
    {
        try{
            
            comboBoxModalidad.setItems(FXCollections.observableArrayList(Tipo.values()));
            comboBoxModalidad.getSelectionModel().selectFirst();
            
        }catch(Exception e)
        {
            System.out.println("Error en Profesor subir formato");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public void cambiarPaginaFormatoA()
    {
        comboBoxModalidad.setValue(comboBoxModalidad.getValue());
        PanelSubirFormatoA.setVisible(true);
        PaneSeleccionarModalidad.setVisible(false);
    }
    
    public void enviarFormato() throws Exception
    {
        Boolean bandera=true;
        Estudiante estudiante1 = null;
        Estudiante estudiante2 = null;
        Profesor coodirector = null;
        String nombreNuevoArchivo=null;
        Director director = new Director();
        if(comboBoxModalidad.getValue().equals(Tipo.Investigacion))
        {
            director.SetBuilder(new InvestigacionBuilder());
        }else if(comboBoxModalidad.getValue().equals(Tipo.PracticaProfesional))
        {
            director.SetBuilder(new PracticaBuilder());
        }
        
        RepositoryFactory<ProyectoRepository> repositoryFactory = new RepositoryFactory(ProyectoRepository.class);
        ProyectoService proyectoService = new ProyectoService(repositoryFactory.getInstance("SQLite"));
        LocalDate hoy = LocalDate.now();
        String fecha = hoy.format(DateTimeFormatter.ISO_DATE);
        if(textFieldTituloProyecto.getText()=="")
        {
            textFieldTituloProyecto.setStyle("-fx-prompt-text-fill: red;-fx-alignment: center;");
            
            bandera = false;
        }
        if(textFieldEstudiante.getText() == "" && bandera)
        {
            textFieldEstudiante.setStyle("-fx-prompt-text-fill: red;-fx-alignment: center;");
            
            bandera = false;
            
        }else
        {
            estudiante1 = new Estudiante();
            estudiante1.setCorreoElectronico(textFieldEstudiante.getText().toLowerCase());
        }
        if(textAreaObjetivoGeneral.getText()=="" && bandera)
        {
            textAreaObjetivoGeneral.setStyle("-fx-prompt-text-fill: red;-fx-alignment: center;");
            bandera=false;
        }
        if(textAreaObjetivosEspecificos.getText().isEmpty() && bandera)
        {
            textAreaObjetivosEspecificos.setStyle("-fx-prompt-text-fill: red;-fx-alignment: center;");
            bandera=false;
        }
        if(!textFieldEstudiante1.getText().isEmpty() && bandera)
        {
            estudiante2 = new Estudiante();
            
            estudiante2.setCorreoElectronico(textFieldEstudiante1.getText().toLowerCase());
        }
        
        if(!textFieldCoodirector.getText().isEmpty() && bandera)
        {
            coodirector = new Profesor();
            
            coodirector.setCorreoElectronico(textFieldCoodirector.getText().toLowerCase());
        }
        if(bandera)
        {
            
            
            try{
            director.build(textFieldTituloProyecto.getText(), this.profesor, coodirector, fecha, textAreaObjetivoGeneral.getText(), textAreaObjetivosEspecificos.getText(), estudiante1, estudiante2, comboBoxModalidad.getValue(),nombreNuevoArchivo);
            proyectoService.subirFormato(director.getObject());
            if(archivo!=null)
            {
                nombreNuevoArchivo = profesor.getId()+estudiante1.getCorreoElectronico();
                nombreNuevoArchivo=ArchivosProyecto.guardarArchivoEnProyecto(archivo, nombreNuevoArchivo, "src/main/resources/documentos");
            }
            informacionOk();
            archivo = null;
            
            vaciarCampos();
            }catch(Exception e)
            {
                
                advertencia.setText(e.getMessage());
                e.printStackTrace();
            }
            
        }
        
        
    }
    public void vaciarCampos()
    {
        textFieldTituloProyecto.setText("");
        textAreaObjetivoGeneral.setText("");
        textAreaObjetivosEspecificos.setText("");
        textFieldCoodirector.setText("");
        textFieldEstudiante.setText("");
        textFieldEstudiante1.setText("");
        imagenArchivoPlano.setVisible(true);
        imagenPdf.setVisible(false);
        textNombreArchivo.setText("Agrega un archivo PDF de maximo 20MB");
        advertencia.setText("");
    }
    public void informacionOk()
    {
        panelInformacion.setVisible(true);
        panelInformacionOk.setVisible(true);

        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(e -> {
            panelInformacion.setVisible(false);
            panelInformacionOk.setVisible(false);
            panelInformacion.setManaged(false);
            panelInformacionOk.setManaged(false);
        });
        delay.play();
    
    }
    public void verificarProyecto()
    {
    
        if(comboBoxModalidad.getValue().equals(Tipo.PracticaProfesional))
        {
            textFieldEstudiante1.setText("");
            textFieldEstudiante1.setEditable(false);
            textFieldEstudiante1.setMouseTransparent(true);
            textFieldEstudiante1.setFocusTraversable(false);
            textFieldEstudiante1.setPromptText("En practica profesional solo puede haber un estudiante");
        }
        if(comboBoxModalidad.getValue().equals(Tipo.Investigacion))
        {
            textFieldEstudiante1.setText(null);
            textFieldEstudiante1.setEditable(true);
            textFieldEstudiante1.setMouseTransparent(false);
            textFieldEstudiante1.setFocusTraversable(true);
            textFieldEstudiante1.setPromptText("Estudiante");
        }
        
    }
    public void subirDocumento()
    {
        FileChooser fc = new FileChooser();
        
        fc.setTitle("Selecciona un archivo");
        
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
        
        File archivo = fc.showOpenDialog(botonSubirArchivo.getScene().getWindow());
        
        if (!Validador.validarArchivo(archivo)){
            
            System.out.println("El archivo elegido no cumple con los parametros requeridos");
        }else
        {
            textNombreArchivo.setText(archivo.getName());
            imagenArchivoPlano.setVisible(false);
            imagenPdf.setVisible(true);
            
            this.archivo=archivo;
        }
    }
    public void goProfesorFormatos()
    {
        Main.goFormatosProfesor();
    }
    @Override
    public void update(Object o) {
        
        PersonaService personaService = (PersonaService) o;
            if(personaService.getPersona() instanceof Profesor){
            profesor = (Profesor) personaService.getPersona();
        }
        
    }

    
} 
