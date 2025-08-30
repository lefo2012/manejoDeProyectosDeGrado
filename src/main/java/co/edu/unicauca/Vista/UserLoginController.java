/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package co.edu.unicauca.Vista;


import co.edu.unicauca.Models.Persona;
import co.edu.unicauca.Repository.Implementation.RepositoryFactory;


import co.edu.unicauca.Services.PersonaService;

import co.edu.unicauca.main.Main;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


/**
 * FXML Controller class
 *
 * @author LEFO
 */
public class UserLoginController  {
    
    @FXML
    TextField textFieldCorreoElectronico;
    @FXML
    PasswordField passwordFieldContrasenia;
    @FXML
    Text textCorreoOContraseniaIncorrecto;
    @FXML
    public void irARegistrarse() throws IOException {
        
        Main.setRoot("UserRegister");
        
    }    
    @FXML
    public void iniciarSesion() throws UnsupportedEncodingException, IOException
    {

    
        
        PersonaService personaService = new PersonaService(RepositoryFactory.getInstance("SQLite"));
        
        Persona persona = personaService.iniciarSesion(textFieldCorreoElectronico.getText(), passwordFieldContrasenia.getText());
        
        if(persona == null)
        {
            textCorreoOContraseniaIncorrecto.setText("CORREO O CONTRASEÑA INCORRECTOS");
        }else
        {
            textCorreoOContraseniaIncorrecto.setText("BIENVENIDO BRO");
        }
        
        
    }
    
}
