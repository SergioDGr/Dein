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
	
	/**
	 * Intenta eliminar el avion seleccionado del aeropuerto seleccionado
	 * @param event
	 */
	@FXML
	private void click_guardar(ActionEvent event) {
		int index = cmbAvion.getSelectionModel().getSelectedIndex();
		Avion avion = aeropuerto.aviones.get(index);
		//Se se podido eliminar el avion
		boolean eliminado = false;
		if(!esEjercicioM)
			eliminado = aeropuertoController.eliminarAvion(avion.getId());
		else
			eliminado = aeropuertoController2.eliminarAvion(avion.getId());
		if(eliminado) {
			aeropuerto.aviones.remove(avion);
			cmbAvion.getSelectionModel().selectFirst();
			cmbAvion.setItems(getAvion(aeropuerto));
			txtError.setText("");
			txtRealizado.setText("Se a eliminado el avión");
		}else { //En caso contrario
			txtRealizado.setText("");
			txtError.setText("No se apodido eliminar el avión");
		}
	}
	
	/**
	 * Dependiendo de la tecla que se preccione si es enter intentara eliminar el avion o 
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
		txtTitulo.setText("BORRAR AVIÓN");
		//Cambia la interfaz
		cambiarInterfaz();
		//Cambia el texto a borrar
		btnGuardar.setText("Borrar");
		//Oculta los radiobutton
		rbActivado.setVisible(false);
		rbDesactivado.setVisible(false);
	}

}
