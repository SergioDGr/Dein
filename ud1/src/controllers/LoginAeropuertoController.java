package controllers;

import dao.UsuarioDao;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginAeropuertoController {
	
	@FXML
    private Button btnLogin;

    @FXML
    private PasswordField pfPass;

    @FXML
    private TextField tfUsuario;
    
    @FXML
    private Text txtError;
    
    public boolean logeado;
    
    @FXML
    void click_login(ActionEvent event) {
    	String esValido = validar();
    	if(!esValido.isEmpty()) {
    		txtError.setText(esValido);
    		logeado = false;
    		return;
    	}
    	txtError.setText("");
    	Stage stage = (Stage) btnLogin.getScene().getWindow();
    	stage.close();
    	logeado = true;
    }
    
    private String validar() {
    	if(tfUsuario.getText().isEmpty() || pfPass.getText().isEmpty())
    		return "Algun campo esta vacio";
    	UsuarioDao usuarioDao = new UsuarioDao();
    	if(!usuarioDao.validarUsuario(tfUsuario.getText(), pfPass.getText())) {
    		return "El usuario o la contraseña no es valido";
    	}
    	
    	return "";
    }
    
}
