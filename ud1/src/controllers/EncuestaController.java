package controllers;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class EncuestaController implements Initializable{
	
	@FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private ComboBox<String> cmbEdad;

    @FXML
    private Group gSexo;

    @FXML
    private ListView<String> lvDeportes;

    @FXML
    private RadioButton rbHombre;

    @FXML
    private RadioButton rbMujer;

    @FXML
    private RadioButton rbOtro;

    @FXML
    private Slider sldCine;

    @FXML
    private Slider sldCompras;

    @FXML
    private Slider sldTv;

    @FXML
    private TextField tfNumsHermanos;

    @FXML
    private TextField tfProfesion;

    @FXML
    private CheckBox cxbPracDeport;
    
    @FXML
    void check_deporte(ActionEvent event) {
    	if (cxbPracDeport.isSelected())
    		lvDeportes.setDisable(false);
    	else
    		lvDeportes.setDisable(true);
    }
    
    @FXML
    void click_Cancelar(ActionEvent event) {
    	
    }

    @FXML
    void click_aceptar(ActionEvent event) {

    }
    
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	ObservableList<String> itemsCmb = FXCollections.observableArrayList("Menores de 18",
    			"Entre 18 y 30", "Entre 31 y 50", "Entre 51 y 70", "Mayores de 70" );
    	cmbEdad.setItems(itemsCmb);
    	cmbEdad.getSelectionModel().selectFirst();
    	ObservableList<String> itemsListVIew = FXCollections.observableArrayList("Tenis", "Fútbol", "Baloncesto",
    			"Natación", "Ciclismo", "Otro");
    	lvDeportes.setItems(itemsListVIew);
    }
    

}
