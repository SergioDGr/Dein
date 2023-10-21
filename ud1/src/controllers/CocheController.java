package controllers;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CocheController{
	
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
    
    @FXML
    void click_luz(ActionEvent event) {
    	String url = imgLuz.getImage().getUrl();
    	if(url.contains("Off")) {
    		url = url.replace("Off", "On");
    		imgLuzEncendida.setVisible(true);
    	}else {
    		url = url.replace("On", "Off");
    		imgLuzEncendida.setVisible(false);
    	}
    	//System.out.println(url);
    	imgLuz.setImage(new Image(url));
    }
    
    @FXML
    void click_azul(ActionEvent event) {
    	cambiarColor("ElectricBlue");
    }

    @FXML
    void click_azul_oscuro(ActionEvent event) {
    	cambiarColor("LapisluxuryBlue");
    }

    @FXML
    void click_blanco(ActionEvent event) {
    	cambiarColor("PepperWhite");
    }

    @FXML
    void click_gris(ActionEvent event) {
    	cambiarColor("MoonwalkGrey");
    }

    @FXML
    void click_gris_oscuro(ActionEvent event) {
    	cambiarColor("ThunderGray");
    }

    @FXML
    void click_naranja(ActionEvent event) {
    	cambiarColor("VolcaninOrange");
    }

    @FXML
    void click_negro(ActionEvent event) {
    	cambiarColor("MidnightBlack");
    }

    @FXML
    void click_rojo(ActionEvent event) {
    	cambiarColor("BlazingRed");
    }
    
    /**
     * Cambia el color del coche, haciendo que la imagen actual del coche
     * cambia a otra imagen con coche con el respestivo color
     * @param color Nombre del color para el coche
     */
    private void cambiarColor(String color) {
    	String path_img = imgCoche.getImage().getUrl();
    	String path_folder_img = path_img.substring(0 ,path_img.lastIndexOf("/") + 1);
    	String new_path_img = path_folder_img + "mini" + color + ".png";
    	//System.out.println(new_path_img);
    	imgCoche.setImage(new Image(new_path_img));
    }

}
