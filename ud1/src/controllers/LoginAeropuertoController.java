package controllers;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginAeropuertoController {
	
	@FXML
    private Button btnLogin;

    @FXML
    private PasswordField pfPass;

    @FXML
    private TextField tfUsuario;
    
    @FXML
    private Text txtError;
    
    @FXML
    void click_login(ActionEvent event) {

    }
    
}
