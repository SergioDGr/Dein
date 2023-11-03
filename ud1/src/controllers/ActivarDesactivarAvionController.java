package controllers;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.layout.GridPane;

import model.Aeropuerto;
import model.Avion;


public class ActivarDesactivarAvionController extends AvionModalController implements Initializable {
	
	private Aeropuerto aeropuerto;
	
	@FXML
	void change_cmbAeropuerto(ActionEvent event) {
		int index = cmbAeropuerto.getSelectionModel().getSelectedIndex();
		aeropuerto = lstAeropuertos.get(index);
		cmbAvion.setItems(getAvion(aeropuerto));
		cmbAvion.getSelectionModel().selectFirst();
	}
	
	@FXML
	private void click_guardar(ActionEvent event) {
		int index = cmbAvion.getSelectionModel().getSelectedIndex();
		Avion avion = aeropuerto.aviones.get(index);
		avion.setActivo(rbActivado.isSelected());
		aeropuertoController.activar_desactivar_avion(avion);
		if(avion.isActivo()) {
			txtRealizado.setText("Avion activado" );
			txtError.setText("");
		}else {
			txtError.setText("Avion desactivado");
			txtRealizado.setText("");
		}
	}
	 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.initialize(arg0, arg1);
		
		txtTitulo.setText("ACTIVAR/DESACTIVAR AVIÓN");
		
		change_cmbAeropuerto(null);
		
		GridPane.setRowIndex(txtAeropuerto, 1);
		GridPane.setRowIndex(cmbAeropuerto, 1);
		
		GridPane.setRowSpan(rbActivado, 2);
		GridPane.setRowSpan(rbDesactivado, 2);
		
		GridPane.setRowSpan(txtError, 1);
		
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
