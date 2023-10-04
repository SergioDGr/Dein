package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class EncuestaController {
	@FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private ComboBox<?> cmbEdad;

    @FXML
    private Group gSexo;

    @FXML
    private ListView<?> lvDeportes;

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
    private CheckBox xbPracDeport;

    @FXML
    void click_Cancelar(ActionEvent event) {
    	
    }

    @FXML
    void click_aceptar(ActionEvent event) {

    }

}
