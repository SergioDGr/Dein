package controllers;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CocheController implements Initializable{
	
    @FXML
    private Button btnLuz;
    
    @FXML
    private ToggleButton btnAzul;

    @FXML
    private ToggleButton btnAzulOscuro;

    @FXML
    private ToggleButton btnBlanco;

    @FXML
    private ToggleButton btnGris;

    @FXML
    private ToggleButton btnGrisOscuro;
    
    @FXML
    private ToggleButton btnNaranja;

    @FXML
    private ToggleButton btnNegro;

    @FXML
    private ToggleButton btnRojo;

    @FXML
    private ToggleGroup colorFavorito;
    
    @FXML
    private ImageView imgCoche;
    
    @FXML
    private ImageView imgLuz;
    
    @FXML
    private ImageView imgLuzEncendida;
    
    /**
     * Al darle al boton de encender la luz, se cambiara el icono del boton apago o encendido y
     * se visualizara o se ocultara la imagen que muestra como el coche tiene encendido o apagado 
     * las luces. 
     * @param event
     */
    @FXML
    void click_luz(ActionEvent event) {
    	String uri = imgLuz.getImage().getUrl();
    	if(uri.contains("Off")) {
    		uri = uri.replace("Off", "On");
    		imgLuzEncendida.setVisible(true);
    	}else {
    		uri = uri.replace("On", "Off");
    		imgLuzEncendida.setVisible(false);
    	}
    	//System.out.println(url);
    	imgLuz.setImage(new Image(uri));
    }
    
    @FXML
    void click_azul(ActionEvent event) {

    }

    @FXML
    void click_azul_oscuro(ActionEvent event) {

    }

    @FXML
    void click_blanco(ActionEvent event) {

    }

    @FXML
    void click_gris(ActionEvent event) {

    }

    @FXML
    void click_gris_oscuro(ActionEvent event) {

    }

    @FXML
    void click_naranja(ActionEvent event) {

    }

    @FXML
    void click_negro(ActionEvent event) {

    }

    @FXML
    void click_rojo(ActionEvent event) {

    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

}
