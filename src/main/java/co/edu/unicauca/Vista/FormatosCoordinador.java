package co.edu.unicauca.Vista;


import co.edu.unicauca.Models.Prueba;
import co.edu.unicauca.main.Main;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class FormatosCoordinador implements Initializable{
  @FXML
    private VBox contactsLayout;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Prueba> prueba = new ArrayList<>(prueba());
        for (int i = 0; i <prueba.size(); i++) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/DatosFormatoCoordinador.fxml"));
            
        try{
            HBox hBox = loader.load();
            DatosFormatoController dfc= loader.getController();
            dfc.setData(prueba.get(i));
            contactsLayout.getChildren().add(hBox);
            }catch (IOException e){
                e.printStackTrace();
            }
        }     
    }
    
    
    private List<Prueba> prueba(){
        List<Prueba> ls = new ArrayList<>();
        Prueba prueba= new Prueba();
        
        for (int i = 3; i <= 30; i++) {
        Prueba p = new Prueba();
        p.setName("Estudiante " + i);
        p.setId(String.valueOf(i));
        p.setEstado("Pendiente");
        p.setFecha("2025-09-21");
        ls.add(p);
        }

        return ls;
    }

}
