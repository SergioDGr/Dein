package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;

public class ActivarDesactivarAvionController extends AvionModalController implements Initializable {
	
	 @FXML
	 private void click_guardar(ActionEvent event) {
		 
	 }
	
	 @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.initialize(arg0, arg1);
		
		GridPane.setRowIndex(txtAeropuerto, 1);
		GridPane.setRowIndex(cmbAeropuerto, 1);
		
		txtAvion.setVisible(true);
		cmbAvion.setVisible(true);
		
		txtAsientos.setVisible(false);
		tfAsientos.setVisible(false);
		txtModelo.setVisible(false);
		tfModelo.setVisible(false);
		txtVelMax.setVisible(false);
		tfVelMax.setVisible(false);
	}
}
