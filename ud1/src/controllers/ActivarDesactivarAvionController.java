package controllers;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Aeropuerto;
import model.Avion;


public class ActivarDesactivarAvionController extends AvionModalController implements Initializable {
	
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
	
	/**
	 * Al darle a guardar actualiza el estado de activo del avion seleccionado en la base de datos
	 * @param event
	 */
	@FXML
	private void click_guardar(ActionEvent event) {
		int index = cmbAvion.getSelectionModel().getSelectedIndex();
		Avion avion = aeropuerto.aviones.get(index);
		avion.setActivo(rbActivado.isSelected());
		if(!esEjercicioM)
			aeropuertoController.activar_desactivar_avion(avion);
		else
			aeropuertoController2.activar_desactivar_avion(avion);

		if(avion.isActivo()) {
			txtRealizado.setText("Avion activado" );
			txtError.setText("");
		}else {
			txtError.setText("Avion desactivado");
			txtRealizado.setText("");
		}
	}
	
	/**
	 * Dependiendo de la tecla que se preccione si es enter intentara activar o deactivar el avion o 
	 * se preciona escape cerrara la ventana
	 * @param key tecla del teclado
	 */
	@FXML
    void click_key_word(KeyEvent key) {
    	if(key.getCode().equals(KeyCode.ENTER)) {
    		click_guardar(null);
    		return;
    	}
    	if(key.getCode().equals(KeyCode.ESCAPE)) {
    		click_cancelar(null);
    	}
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		super.initialize(arg0, arg1);
		//Cambia el titulo
		txtTitulo.setText("ACTIVAR/DESACTIVAR AVIÓN");
		//Cambia la interfaz
		cambiarInterfaz();
	}

}
