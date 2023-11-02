package controllers;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import javafx.stage.Stage;

import model.Aeropuerto;

public class AvionModalController implements Initializable{
    
	@FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    protected ComboBox<String> cmbAeropuerto;

    @FXML
    private GridPane gdPane;

    @FXML
    protected RadioButton rbActivado;

    @FXML
    private RadioButton rfDesactivado;

    @FXML
    protected TextField tfAsientos;

    @FXML
    protected TextField tfModelo;

    @FXML
    protected TextField tfVelMax;

    @FXML
    private ToggleGroup tgEstado;

    @FXML
    private Text txtAeropuerto;

    @FXML
    private Text txtAsientos;

    @FXML
    private Text txtModelo;

    @FXML
    private Text txtVelMax;
    
    @FXML
    protected Text txtError;
    
    protected ObservableList<Aeropuerto> lstAeropuertos ;
    
    protected AeropuertoController aeropuertoController;
    
    public void setLstAeropuertos(ObservableList<Aeropuerto> lstAeropuertos) {
    	this.lstAeropuertos = lstAeropuertos;
    }
    
    public void setAeropuertoController(AeropuertoController controller) {
    	aeropuertoController = controller;
    }
    
    @FXML
    void click_cancelar(ActionEvent event) {
    	Stage stage = (Stage) btnCancelar.getScene().getWindow();
    	stage.close();
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	ObservableList<String> listaNombreAeropuertos = FXCollections.observableArrayList();
    	lstAeropuertos.forEach(aeropuerto ->{
    		listaNombreAeropuertos.add(aeropuerto.getNombre());
    	});
    	
    	cmbAeropuerto.setItems(listaNombreAeropuertos);
    	cmbAeropuerto.getSelectionModel().selectFirst();
    }
}   
	