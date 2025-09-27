package co.edu.unicauca.main;



import co.edu.unicauca.Factorys.RepositoryFactory;
import co.edu.unicauca.Models.FormatoA;
import co.edu.unicauca.Repository.PersonaRepository;
import co.edu.unicauca.Repository.ProyectoRepository;
import co.edu.unicauca.Services.PersonaService;
import co.edu.unicauca.Services.ProyectoService;
import co.edu.unicauca.Vista.EstudianteFormatosController;
import co.edu.unicauca.Vista.EstudianteVerFormatoAController;
import co.edu.unicauca.Vista.CoordinadorEvaluarFormatosAController;
import co.edu.unicauca.Vista.CoordinadorFormatosController;
import co.edu.unicauca.Vista.ProfesorFormatosController;
import co.edu.unicauca.Vista.ProfesorSubirFormatoAController;
import co.edu.unicauca.Vista.ProfesorVerFormatoAController;
import co.edu.unicauca.Vista.UserLoginController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import co.edu.unicauca.database.InitDB;

public class Main extends Application {

    private static Scene scene;
    private static Parent profesorRoot;
    private static Parent loginRoot;
    private static Parent registerRoot;

    private static Parent profesorFormatosRoot;
    private static Parent profesorVerFormatoA;
    
    private static Parent cordinadorEvaluarRoot;
    private static Parent cordinadorRoot;

    private static Parent estudianteFormatos;
    private static Parent estudianteVerFormatoA;
    
    private ProfesorSubirFormatoAController profesorSubirFormatoCrtl;
    private CoordinadorFormatosController coordinadorController;
    private static CoordinadorEvaluarFormatosAController coordinadorEvaluar;
    private static EstudianteFormatosController estudianteController;
    private static EstudianteVerFormatoAController estudianteVerFormatoAController;
    private static ProfesorVerFormatoAController profesorVerFormatoAController;
    private static ProfesorFormatosController profesorFormatosController;
    
    public static Parent getProfesorRoot() {
        return profesorRoot;
    }
    //victor@unicauca.edu.co
    //123ASD.
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(new javafx.scene.Group(), 1920, 1080);
        scene.getStylesheets().add(getClass().getResource("/css/labelFondo.css").toExternalForm());
        stage.setScene(scene);
        stage.setScene(scene);
        stage.show();

        InitDB.crearTablas();
        RepositoryFactory<PersonaRepository> factoryPersona = new RepositoryFactory<>(PersonaRepository.class);
        PersonaService personaService = new PersonaService(factoryPersona.getInstance("SQLite"));
        
        RepositoryFactory<ProyectoRepository> repoFactory = new RepositoryFactory<>(ProyectoRepository.class);
        ProyectoService proyectoService = new ProyectoService(repoFactory.getInstance("SQLite"));
        
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/ProfesorSubirFormatoA.fxml"));
        profesorRoot = loader.load();          
        profesorSubirFormatoCrtl = loader.getController();
        personaService.addObserver(profesorSubirFormatoCrtl);
        
        
        loader = new FXMLLoader(Main.class.getResource("/fxml/CoordinadorFormatos.fxml"));
        cordinadorRoot = loader.load();  
        coordinadorController=loader.getController();
        personaService.addObserver(coordinadorController);
        proyectoService.addObserver(coordinadorController);
        
        loader = new FXMLLoader(Main.class.getResource("/fxml/CoordinadorEvaluarFormatosA.fxml"));
        cordinadorEvaluarRoot = loader.load(); 
        coordinadorEvaluar=loader.getController();
        coordinadorEvaluar.setProyectoService(proyectoService);
        
        loader = new FXMLLoader(Main.class.getResource("/fxml/UserLogin.fxml"));
        loginRoot = loader.load();              
        UserLoginController uc = loader.getController();
        uc.setPersonaService(personaService);
        
        loader = new FXMLLoader(Main.class.getResource("/fxml/UserRegister.fxml"));
        registerRoot = loader.load(); 
        

        loader = new FXMLLoader(Main.class.getResource("/fxml/ProfesorFormatos.fxml"));
        profesorFormatosRoot = loader.load();  
        profesorFormatosController = loader.getController( );
        personaService.addObserver(profesorFormatosController);
        proyectoService.addObserver(profesorFormatosController);
        
        
        loader = new FXMLLoader(Main.class.getResource("/fxml/EstudianteFormatos.fxml"));
        estudianteFormatos = loader.load();  
        estudianteController=loader.getController();
        personaService.addObserver(estudianteController);
        proyectoService.addObserver(estudianteController);
        
        loader = new FXMLLoader(Main.class.getResource("/fxml/EstudianteVerFormatoA.fxml"));
        estudianteVerFormatoA = loader.load();
        estudianteVerFormatoAController = loader.getController();
        
        loader = new FXMLLoader(Main.class.getResource("/fxml/ProfesorVerFormatoA.fxml"));
        profesorVerFormatoA = loader.load();
        profesorVerFormatoAController = loader.getController();
        
        scene.setRoot(loginRoot);

    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    public static void setRoot(Parent root)
    {
        scene.setRoot(root);
    }
    public static void goProfesor()
    {
        scene.setRoot(profesorRoot);
    }
    public static void goLogin()
    {
        scene.setRoot(loginRoot);
    }
    public static void goRegister()
    {
        scene.setRoot(registerRoot);
    }
    public static void goProfesorFormatos()
    {
        scene.setRoot(profesorFormatosRoot);
    }
    public static void goCoordinador()
    {
        scene.setRoot(cordinadorRoot);
    }
    public static void goCoordinadorEvaluar(FormatoA formato, int idCoordinador) throws IOException
    {  

        coordinadorEvaluar.setFormato(formato,idCoordinador);
        scene.setRoot(cordinadorEvaluarRoot);

    }
    public static void goVerFormatoA(FormatoA formato)
    {
        estudianteVerFormatoAController.setFormato(formato);
        scene.setRoot(estudianteVerFormatoA);
    }
    public static void goVerFormatoAProfesor(FormatoA formato)
    {
        profesorVerFormatoAController.setFormato(formato);
        scene.setRoot(profesorVerFormatoA);
    }
    public static void goEstudianteFormatos()
    {
        scene.setRoot(estudianteFormatos);
    }
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws IOException {
        
        launch();
        
    }

}