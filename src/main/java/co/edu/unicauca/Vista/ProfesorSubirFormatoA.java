
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
import co.edu.unicauca.Util.Tipo;
import co.edu.unicauca.Util.Validador;

import java.io.File;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;


/**
 *@FXML controller class
 * 
 * @author LEFO
 */
public class ProfesorSubirFormatoA implements Observer{
    @FXML
    Button botonSubirArchivo;
    @FXML
    Pane PanelSubirFormatoA,PaneSeleccionarModalidad;
    @FXML
    ComboBox<Tipo> comboBoxModalidad;
    @FXML
    Text textNombreArchivo;
    @FXML
    ImageView imagenArchivoPlano,imagenPdf;
    @FXML
    TextField textFieldTituloProyecto,textFieldCoodirector,textFieldObjetivoGeneral,textFieldEstudiante,textFieldEstudiante1;
    @FXML
    TextArea textAreaObjetivosEspecificos;
    Profesor profesor=null;
    File archivo;
    
    
    private File archivoSubido;
    
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
        String nombreArchivo = "";
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
        if(textFieldEstudiante.getText() == "")
        {
            textFieldEstudiante.setStyle("-fx-prompt-text-fill: red;-fx-alignment: center;");
            bandera = false;
            
        }else
        {
            estudiante1 = new Estudiante();
            estudiante1.setId(Integer.parseInt( textFieldEstudiante.getText()));
        }
        
        if(textFieldEstudiante1.getText() != "")
        {
            estudiante2 = new Estudiante();
            
            estudiante2.setId(Integer.parseInt(textFieldEstudiante1.getText()));
        }
        
        if(textFieldCoodirector.getText()!="")
        {
            coodirector = new Profesor();
            
            coodirector.setId(Integer.parseInt(textFieldCoodirector.getText()));
        }
        if(archivo!=null)
        {
            nombreArchivo = archivo.getName();
        }
        if(bandera)
        {
            director.build(textFieldTituloProyecto.getText(), this.profesor, coodirector, fecha, textFieldObjetivoGeneral.getText(), textAreaObjetivosEspecificos.getText(), estudiante1, estudiante2, comboBoxModalidad.getValue(),nombreArchivo);
            proyectoService.subirFormato(director.getObject());
        }
        
        archivo = null;
        imagenArchivoPlano.setVisible(true);
        imagenPdf.setVisible(false);
        textNombreArchivo.setText("Agrega un archivo PDF de maximo 20MB");
        vaciarCampos();
    }
    public void vaciarCampos()
    {
        textFieldTituloProyecto.setText("");
        textFieldObjetivoGeneral.setText("");
        textAreaObjetivosEspecificos.setText("");
        textFieldCoodirector.setText("");
        textFieldEstudiante.setText("");
        textFieldEstudiante1.setText("");
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
    @Override
    public void update(Object o) {
        
        PersonaService personaService = (PersonaService) o;
            if(personaService.getPersona() instanceof Profesor){
            profesor = (Profesor) personaService.getPersona();
        }
        
    }

    
} 
