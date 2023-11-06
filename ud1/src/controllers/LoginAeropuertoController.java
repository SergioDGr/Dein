package controllers;

import dao.UsuarioDao;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
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
    
    /**
     * Al darle a login validara los datos y si esta todo correcto mostrara la ventana principal
     * @param event
     */
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
    
    @FXML
    void clic_login_intro(KeyEvent event) {

    }
    
    /**
     * Valida el nombre del usuario y su contraseña, primero si no estan vacios y despues en la base de datos
     * si los datos estan correctos
     * @return devolvera un String vacio si no hay ningun error y en caso contrario devolvera cual es el error
     */
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
