package controllers;

import java.io.InputStream;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import model.Aeropuerto;
import model.AeropuertoPrivado;
import model.AeropuertoPublico;
import model.Direccion;

public class EditarAeropuertoController extends AeropuertoModalController implements Initializable{
	
	private Aeropuerto aeropuerto;
	private InputStream imageBinary = null;
	
	/**
	 * Al darle a guardar valida los datos y si todo esta correcto intenta modificar un aeropuerto en
	 * la base de datos
	 * @param event
	 */
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
    	//Se guarda la direccion
    	Direccion direccion = new Direccion(this.aeropuerto.getDireccion().getId(), tfPais.getText(), tfCiudad.getText(), tfCalle.getText(), Integer.parseInt(tfNumero.getText()));
    	
    	//en caso de aeropuerto privado
    	if(rbtnPrivado.isSelected()) {
    		//Se guarda la aeropuerto
    		aeropuerto = new AeropuertoPrivado(this.aeropuerto.getId(), tfNombre.getText(), Integer.parseInt(tfAnio.getText()), direccion, Integer.parseInt(tfCapacidad.getText()));
    		AeropuertoPrivado aPrivado = (AeropuertoPrivado) aeropuerto;
    		aPrivado.setImage(imageBinary);
    		aPrivado.setSocios(Integer.parseInt(tfNumSocios.getText()));
    		//Si todos los datos son identicos
    		if(aPrivado.equals((AeropuertoPrivado) this.aeropuerto) && imageBinary.equals(this.aeropuerto.getImage())) {
    			txtError.setText("No se ha cambiado ningun dato");
    			return;
    		}
    		//intenta modificar el aeropuerto
    		boolean modificado = false;
    		if(!esEjercicioM)
    			modificado = aeropuertoController.modificarAeropuertoPrivado(aPrivado);
    		else
    			modificado = aeropuertoController2.modificarAeropuertoPrivado(aPrivado);
    		if(!modificado) {
    			txtError.setText("No se apodido modificar el aeropuerto");
    			return;
    		}
    	}else{ //en caso de aeropuerto publico
    		//Se guarda la aeropuerto
    		aeropuerto = new AeropuertoPublico(this.aeropuerto.getId(), tfNombre.getText(), Integer.parseInt(tfAnio.getText()), direccion, Integer.parseInt(tfCapacidad.getText()));
    		AeropuertoPublico aPublico = (AeropuertoPublico) aeropuerto;
    		aPublico.setImage(imageBinary);
    		aPublico.setFinanciacion(Double.parseDouble(tfFinanciacion.getText()));
    		aPublico.setTrabajadores(Integer.parseInt(tfNumTrabajadores.getText()));
    		//Si todos los datos son identicos
    		if(aPublico.equals((AeropuertoPublico) this.aeropuerto) && imageBinary.equals(this.aeropuerto.getImage())) {
    			txtError.setText("No se ha cambiado ningun dato");
    			return;
    		}
    		//intenta modificar el aeropuerto
    		boolean modificado = false;
    		if(!esEjercicioM)
    			aeropuertoController.modificarAeropuertoPublico(aPublico);
    		else
    			aeropuertoController2.modificarAeropuertoPublico(aPublico);
    		if(!modificado) {
    			txtError.setText("No se apodido modificar el aeropuerto");
    			return;
    		}
    	}
    	//Si se apodido modificar
    	txtError.setText("");
    	click_cancelar(null);
	}
	
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
	
	@FXML
    void click_select_imagen(ActionEvent event) {
		imageBinary = seleccionarImagen(true);
	}
	
	 @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
	 	//Guarda el aeropuerto
	 	if(!esEjercicioM)
	 		aeropuerto = aeropuertoController.getAeropuerto();
	 	else
	 		aeropuerto = aeropuertoController2.getAeropuerto();
	 	
	 	//se deja marcado el radiobutton 
	 	if(esPublico)
	 		click_publico(null);
	 	else
	 		click_privado(null);
	 	
    	//Se desabilita
    	rbtnPrivado.setDisable(true);
    	rbtnPublico.setDisable(true);
    	
    	//Se pone todos los datos que estaban en tabla en los textFields
    	tfNombre.setText(aeropuerto.getNombre());
    	tfPais.setText(aeropuerto.getDireccion().getPais());
    	tfCiudad.setText(aeropuerto.getDireccion().getCiudad());
    	tfCalle.setText(aeropuerto.getDireccion().getCalle());
    	tfNumero.setText(aeropuerto.getDireccion().getNum() + "");
    	tfAnio.setText(aeropuerto.getAnio() + "");
    	tfCapacidad.setText(aeropuerto.getCapacidad() + "");
    	
    	//Si es un aeropuerto privado
    	if(aeropuerto instanceof AeropuertoPrivado) {
    		AeropuertoPrivado aPrivado = (AeropuertoPrivado) aeropuerto;
    		tfNumSocios.setText(aPrivado.getSocios() + "" );
    	}else{//Si es un aeropuerto publico
    		AeropuertoPublico aPublico = (AeropuertoPublico) aeropuerto;
    		tfFinanciacion.setText(aPublico.getFinanciacion() + "");
    		tfNumTrabajadores.setText(aPublico.getTrabajadores() + "");
    	}
    	
    	imageSelected.setVisible(true);
	 	
	 	if(aeropuerto.getImage() != null) {
	 		System.out.println("imagen");
	 		imageSelected.setImage(new Image(aeropuerto.getImage()));
	 		gpPanel.getRowConstraints().get(8).setPrefHeight(150);
	 	}
	 	
	 	cambiarInterfazEjercicioM();
    }
}
