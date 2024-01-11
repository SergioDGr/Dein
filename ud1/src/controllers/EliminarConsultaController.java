package controllers;

import java.net.URL;

import java.time.ZoneId;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import javafx.stage.Stage;

import model.Animal;
import model.Consulta;

public class EliminarConsultaController implements Initializable{

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<Consulta> cmbAnimal;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private TextArea taObservacion;

    @FXML
    private Text txtError;

    @FXML
    private Text txtTitulo;
    
    @FXML
    private Text txtCmb;
    
    private AnimalController controller;
    
    private Animal animal;
    
    public void setController(AnimalController controller) {
    	this.controller = controller;
    }
    
    public void setAnimal(Animal animal) {
    	this.animal = animal;
    }
    
    @FXML
    void click_cancelar(ActionEvent event) {
    	Stage stage = (Stage) btnCancelar.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void click_guardar(ActionEvent event) {
    	Consulta consulta = cmbAnimal.getSelectionModel().getSelectedItem();
    	if(controller.eliminarConsulta(consulta)) {
    		animal.delConsulta(consulta);
    		
    		txtError.setStyle("-fx-fill: green;");
    		txtError.setText("Se a eliminado la consulta");
    		cmbAnimal.getSelectionModel().selectFirst();
    	}else {
    		txtError.setStyle("-fx-fill: red;");
    		txtError.setText("No se podio eliminar la consulta");
    	}
    }
    
    @FXML
    void select_Observacion(ActionEvent event) {
    	if(cmbAnimal.getItems().size() > 0) {
	    	System.out.println("Fecha: " + dpFecha.getValue());
	    	Consulta con = cmbAnimal.getSelectionModel().getSelectedItem();
	    	dpFecha.setValue(con.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
	    	taObservacion.setText(con.getObservacion());
    	}else
    		btnGuardar.setDisable(true);
    }
    
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	txtTitulo.setText("Eliminar Consulta");
    	btnGuardar.setText("Eliminar");
    	txtCmb.setText("Consulta");
    	
    	cmbAnimal.setItems(animal.getConsultas());
    	cmbAnimal.getSelectionModel().selectFirst();
    	
    	select_Observacion(null);
    	taObservacion.setDisable(true);
    	dpFecha.setDisable(true);
    }
    
}
