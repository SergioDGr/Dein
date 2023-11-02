package controllers;

import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import model.Aeropuerto;
import model.Avion;

public class AniadirAvionController extends AvionModalController{
	
    @FXML
    void click_guardar(ActionEvent event) {
    	String msgError = validar();
    	if(!msgError.isEmpty()) {
    		txtError.setText(msgError);
    		return;
    	}
    	txtError.setText("");
    	
    	Avion avion = new Avion(0, tfModelo.getText(), Integer.parseInt(tfAsientos.getText()), Double.parseDouble(tfVelMax.getText()), rbActivado.isSelected());
    	int index = cmbAeropuerto.getSelectionModel().getSelectedIndex();
		Aeropuerto aeropuerto = lstAeropuertos.get(index);
		
		if(aeropuertoController.insertarAvion(avion,aeropuerto)) {
			tfAsientos.setText("");
			tfModelo.setText("");
			tfVelMax.setText("");
		}
    }
    
    /**
     * Valida todos los campos text field si estan correctos
     * @return devuelve un {@link String} vacio si todo esta valido en caso contrario guardar el mesaje del error
     */
	private String validar() {
    	if(tfModelo.getText().isEmpty() || tfAsientos.getText().isEmpty() || tfVelMax.getText().isEmpty()) {
    		return "Alguno de los campos esta vacio";
    	}
    	
    	if(!esValidarModelo())
    		return "El modelo elegido ya existe en el aeropuerto";
    	
    	String enteroFormat = "^\\d+$";
    	String doubleFormat = "^\\d+(\\.\\d{2})?$|^\\d+(,\\d{2})?$";
    	if(!tfAsientos.getText().matches(enteroFormat) || !tfVelMax.getText().matches(doubleFormat))
    		return "Alguno de los campos no tienen el formato valido";
    	 return "";
    }
	
	private boolean esValidarModelo() {
		int index = cmbAeropuerto.getSelectionModel().getSelectedIndex();
		Aeropuerto aeropuerto = lstAeropuertos.get(index);
		System.out.println(aeropuerto);
		ObservableList<Avion> lstAvion = aeropuerto.aviones;
		
		for(Avion avion : lstAvion) {
			if(avion.getModelo().equals(tfModelo.getText())) {
				return false;
			}
		}
		
		return true;
	}
}
