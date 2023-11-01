package controllers;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import model.Aeropuerto;
import model.AeropuertoPrivado;
import model.AeropuertoPublico;
import model.Direccion;

public class EditarAeropuertoController extends AeropuertoModalController implements Initializable{
	
	private Aeropuerto aeropuerto;
	
	@FXML
    private void click_guardar(ActionEvent event) {
		//Hace la validacion y lo guarda en mensaje
    	String strError = validar();
    	//En caso de que haya algo no valido
    	if(!strError.isEmpty()) {
    		txtError.setText(strError);
    		return;
    	}
    	Aeropuerto aeropuerto;
    	
    	Direccion direccion = new Direccion(this.aeropuerto.getDireccion().getId(), tfPais.getText(), tfCiudad.getText(), tfCalle.getText(), Integer.parseInt(tfNumero.getText()));
    	
    	if(rbtnPrivado.isSelected()) {
    		aeropuerto = new AeropuertoPrivado(this.aeropuerto.getId(), tfNombre.getText(), Integer.parseInt(tfAnio.getText()), direccion, Integer.parseInt(tfCapacidad.getText()));
    		AeropuertoPrivado aPrivado = (AeropuertoPrivado) aeropuerto;
    		aPrivado.setSocios(Integer.parseInt(tfNumSocios.getText()));
    		if(aPrivado.equals((AeropuertoPrivado) this.aeropuerto)) {
    			txtError.setText("No se ha cambiado ningun dato");
    			return;
    		}
    		if(!aeropuertoController.modificarAeropuertoPrivado(aPrivado)) {
    			txtError.setText("No se apodido modificar el aeropuerto");
    			return;
    		}
    	}else{
    		aeropuerto = new AeropuertoPublico(this.aeropuerto.getId(), tfNombre.getText(), Integer.parseInt(tfAnio.getText()), direccion, Integer.parseInt(tfCapacidad.getText()));
    		AeropuertoPublico aPublico = (AeropuertoPublico) aeropuerto;
    		aPublico.setFinanciacion(Double.parseDouble(tfFinanciacion.getText()));
    		aPublico.setTrabajadores(Integer.parseInt(tfNumTrabajadores.getText()));
    		if(aPublico.equals((AeropuertoPublico) this.aeropuerto)) {
    			txtError.setText("No se ha cambiado ningun dato");
    			return;
    		}
    		if(!aeropuertoController.modificarAeropuertoPublico(aPublico)) {
    			txtError.setText("No se apodido modificar el aeropuerto");
    			return;
    		}
    	}
    	txtError.setText("");
    	click_cancelar(null);
	}
	
	 @Override
	    public void initialize(URL arg0, ResourceBundle arg1) {
		 	aeropuerto = aeropuertoController.getAeropuerto();
		 	
		 	if(esPublico)
		 		click_publico(null);
		 	else
		 		click_privado(null);
		 	
	    	
	    	rbtnPrivado.setDisable(true);
	    	rbtnPublico.setDisable(true);
	    	
	    	tfNombre.setText(aeropuerto.getNombre());
	    	tfPais.setText(aeropuerto.getDireccion().getPais());
	    	tfCiudad.setText(aeropuerto.getDireccion().getCiudad());
	    	tfCalle.setText(aeropuerto.getDireccion().getCalle());
	    	tfNumero.setText(aeropuerto.getDireccion().getNum() + "");
	    	tfAnio.setText(aeropuerto.getAnio() + "");
	    	tfCapacidad.setText(aeropuerto.getCapacidad() + "");
	    	
	    	if(aeropuerto instanceof AeropuertoPrivado) {
	    		AeropuertoPrivado aPrivado = (AeropuertoPrivado) aeropuerto;
	    		tfNumSocios.setText(aPrivado.getSocios() + "" );
	    	}else{
	    		AeropuertoPublico aPublico = (AeropuertoPublico) aeropuerto;
	    		tfFinanciacion.setText(aPublico.getFinanciacion() + "");
	    		tfNumTrabajadores.setText(aPublico.getTrabajadores() + "");
	    	}
	    	
	    }
}
