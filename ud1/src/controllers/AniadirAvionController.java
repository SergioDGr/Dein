package controllers;

import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import model.Aeropuerto;
import model.Avion;

public class AniadirAvionController extends AvionModalController{
	
	/**
	 * Valida los campos textfield y despues intenta añadir el avion en la base de datos
	 * @param event
	 */
    @FXML
    void click_guardar(ActionEvent event) {
    	txtRealizado.setText("");
    	String msgError = validar();
    	//Si hay algun error
    	if(!msgError.isEmpty()) {
    		txtError.setText(msgError);
    		return;
    	}
    	txtError.setText("");
    	//Crea el avion
    	Avion avion = new Avion(0, tfModelo.getText(), Integer.parseInt(tfAsientos.getText()), Double.parseDouble(tfVelMax.getText()), rbActivado.isSelected());
    	int index = cmbAeropuerto.getSelectionModel().getSelectedIndex();
		Aeropuerto aeropuerto = lstAeropuertos.get(index);
		
		//Si se puede insetar el avion
		if(aeropuertoController.insertarAvion(avion,aeropuerto)) {
			tfAsientos.setText("");
			tfModelo.setText("");
			tfVelMax.setText("");
			txtRealizado.setText("Se añadido el avion");
		}
    }
    
    /**
     * Valida todos los campos text field si estan correctos
     * @return devuelve un {@link String} vacio si todo esta valido en caso contrario guardar el mesaje del error
     */
	private String validar() {
		//Si el campo esta vacio
    	if(tfModelo.getText().isEmpty() || tfAsientos.getText().isEmpty() || tfVelMax.getText().isEmpty()) {
    		return "Alguno de los campos esta vacio";
    	}
    	//Si el modelo esta repetido
    	if(!esValidarModelo())
    		return "El modelo elegido ya existe en el aeropuerto";
    	
    	String enteroFormat = "^\\d+$";
    	String doubleFormat = "^\\d+(\\.\\d{2})?$|^\\d+(,\\d{2})?$";
    	//Si el formato de los numeros no esta bien
    	if(!tfAsientos.getText().matches(enteroFormat) || !tfVelMax.getText().matches(doubleFormat))
    		return "Alguno de los campos no tienen el formato valido";
    	 return "";
    }
	
	/**
	 * Valida el modelo si en el aeropuerto de los aviones que hay mira si hay alguno que tenga el mismo modelo
	 * @return devuelve <code>true</code> si no se ha encontrado el modelo o <code>false</code> si esta el modelo
	 */
	private boolean esValidarModelo() {
		int index = cmbAeropuerto.getSelectionModel().getSelectedIndex();
		Aeropuerto aeropuerto = lstAeropuertos.get(index);
		ObservableList<Avion> lstAvion = aeropuerto.aviones;
		
		for(Avion avion : lstAvion) {
			if(avion.getModelo().equals(tfModelo.getText())) {
				return false;
			}
		}
		
		return true;
	}
}
