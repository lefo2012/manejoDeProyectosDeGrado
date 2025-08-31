/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.Vista;


import co.edu.unicauca.Models.Persona;
import co.edu.unicauca.Repository.Implementation.RepositoryFactory;
import co.edu.unicauca.Services.PersonaService;
import co.edu.unicauca.Util.Cargo;
import co.edu.unicauca.main.Main;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 *
 * @author PixelBot Gaming
 */
public class UserRegisterController {
    @FXML
    TextField textFieldCorreoElectronico,textFieldNombre,textFieldApellido,textFieldCelular;
    @FXML
    PasswordField passwordFieldContrasenia;
    @FXML
    ComboBox<Cargo> comboBoxCargo;
    @FXML
    Text textoAviso;
    
    public void irALogin() throws IOException
    {
    
        Main.setRoot("UserLogin");
    
    }
    public void initialize() {

        
        // NO hacer: comboBoxCargo = new ComboBox<>();
        comboBoxCargo.setItems(FXCollections.observableArrayList(Cargo.values()));
        comboBoxCargo.getSelectionModel().selectFirst(); // opcional
    }
    private void limpiarCampos() {
      textFieldCorreoElectronico.clear();
      textFieldNombre.clear();
      textFieldApellido.clear();
      textFieldCelular.clear();
      passwordFieldContrasenia.clear();
      textoAviso.setText(""); 

      if (comboBoxCargo.getItems() != null && !comboBoxCargo.getItems().isEmpty()) {
          comboBoxCargo.getSelectionModel().selectFirst(); 
      }

      textFieldCorreoElectronico.setStyle("-fx-alignment: CENTER;");
      textFieldNombre.setStyle("-fx-alignment: CENTER;");
      textFieldApellido.setStyle("-fx-alignment: CENTER;");
      textFieldCelular.setStyle("-fx-alignment: CENTER;");
      passwordFieldContrasenia.setStyle("-fx-alignment: CENTER;");
    }
    public void registrarse() throws UnsupportedEncodingException, IOException
    {
        int errores=0;
        String resultado="";
        PersonaService personaService = new PersonaService(RepositoryFactory.getInstance("SQLite"));
        if("".equals(textFieldNombre.getText()) )
        {
            textFieldNombre.setStyle("-fx-prompt-text-fill: red;-fx-alignment: center;");
            resultado+="FALTA EL CAMPO DE NOMBRE\n";
            errores++;
        }
        if("".equals(textFieldApellido.getText()) )
        {
            textFieldApellido.setStyle("-fx-prompt-text-fill: red;-fx-alignment: center;");
            resultado+="FALTA EL CAMPO DE APELLIDO\n";
            errores++;
        }
        if("".equals(textFieldCorreoElectronico.getText()))
        {
            textFieldCorreoElectronico.setStyle("-fx-prompt-text-fill: red;-fx-alignment: center;");
            resultado+="FALTA EL CAMPO DE CORREO ELECTRONICO\n";
            errores++;
        }
        if("".equals(passwordFieldContrasenia.getText()))
        {
            passwordFieldContrasenia.setStyle("-fx-prompt-text-fill: red;-fx-alignment: center;");
            resultado+="FALTA EL CAMPO DE CONTRASEÑA\n";
            errores++;
        }
        if (errores==0)
        {
            Persona personaARegistrar = new Persona(
                textFieldNombre.getText(),
                textFieldApellido.getText(),
                textFieldCelular.getText(),            
                textFieldCorreoElectronico.getText(),
                passwordFieldContrasenia.getText(),
                new LinkedList<>()//facultad o falcultades
            );
            
            resultado = personaService.registrar(personaARegistrar, comboBoxCargo.getValue());
            if(resultado.equals("Registro completado"))
            {
                textoAviso.setText(resultado);
                
                limpiarCampos();
                
                Main.setRoot("UserLogin");
                
            }if(resultado.equals("Formato de contrasenia invalido recuerde que debe llevar por lo menos un caracter especial una mayuscula y un digito"))
            {
                passwordFieldContrasenia.setStyle("-fx-text-fill: red;-fx-alignment: center;");
                textoAviso.setText(resultado);
            }if(resultado.equals("Correo invalido"))
            {
                textFieldCorreoElectronico.setStyle("-fx-text-fill: red;-fx-alignment: center;");
                textoAviso.setText(resultado);
            }
            
        }
        
        textoAviso.setText(resultado);
    }
}
