package controllers;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

public class AniadirAnimalController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private RadioButton rbtnHombre;

    @FXML
    private RadioButton rbtnMujer;

    @FXML
    private TextField tfEdad;

    @FXML
    private TextField tfEspecie;

    @FXML
    private TextField tfNombre;

    @FXML
    private TextField tfPeso;

    @FXML
    private TextField tfRaza;

    @FXML
    private ToggleGroup tgSexo;

    @FXML
    private Text txtError;

    @FXML
    private Text txtTitulo;

    @FXML
    void click_cancelar(ActionEvent event) {

    }

    @FXML
    void click_guardar(ActionEvent event) {

    }

}
