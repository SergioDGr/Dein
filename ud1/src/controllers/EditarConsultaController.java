package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class EditarConsultaController {

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<?> cmbAnimal;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private TextArea taObservacion;

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
