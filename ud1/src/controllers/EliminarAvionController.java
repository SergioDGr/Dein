package controllers;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import model.Aeropuerto;
import model.Avion;

public class EliminarAvionController extends AvionModalController implements Initializable{
	
	private Aeropuerto aeropuerto;
	
	/**
	 * Al darle a alguno de los aeropuerto carga el combobox de aviones
	 */
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
		
		if(aeropuertoController.eliminarAvion(avion.getId())) {
			aeropuerto.aviones.remove(avion);
			cmbAvion.getSelectionModel().selectFirst();
			cmbAvion.setItems(getAvion(aeropuerto));
			txtError.setText("");
			txtRealizado.setText("Se a eliminado el avión");
		}else {
			txtRealizado.setText("");
			txtError.setText("No se apodido eliminar el avión");
		}
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.initialize(arg0, arg1);
		txtTitulo.setText("BORRAR AVIÓN");
		cambiarInterfaz();
		
		btnGuardar.setText("Borrar");
		
		rbActivado.setVisible(false);
		rbDesactivado.setVisible(false);
	}
}
