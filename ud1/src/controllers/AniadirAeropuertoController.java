package controllers;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import model.Aeropuerto;
import model.AeropuertoPrivado;
import model.AeropuertoPublico;
import model.Direccion;

/**
 * Clase controladora que gestiona el añadir un aeropuerto en la base de datos
 */
public class AniadirAeropuertoController extends AeropuertoModalController implements Initializable{
	
	private byte[] imageBinary;
	
	/**
	 * Al darle a guardar valida los textfield y si todo esta correcto intenta insertar un aeropuerto en
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
    	txtError.setText("");
    	//Creamos la direccion
    	Direccion d = new Direccion(0, tfPais.getText(), tfCiudad.getText(), tfCalle.getText(), Integer.parseInt(tfNumero.getText()));
    	//Creamos el aerpuerto
    	Aeropuerto a;
    	if(esPublico) {
    		AeropuertoPublico aPublico = new AeropuertoPublico(0, tfNombre.getText(), Integer.parseInt(tfAnio.getText()), d, Integer.parseInt(tfCapacidad.getText()));
    		String financiacion = tfFinanciacion.getText().replace(",", ".");
    		aPublico.setFinanciacion(Double.parseDouble(financiacion));
    		aPublico.setTrabajadores(Integer.parseInt(tfNumTrabajadores.getText()));
    		a = aPublico;
    	}else {
    		AeropuertoPrivado aPrivado = new AeropuertoPrivado(0, tfNombre.getText(), Integer.parseInt(tfAnio.getText()), d, Integer.parseInt(tfCapacidad.getText()));
    		aPrivado.setSocios(Integer.parseInt(tfNumSocios.getText()));
    		a = aPrivado;
    	}
    	a.setImage(imageBinary);
    	//se intenta insertar la base de datos
    	boolean insertado = false;
    	if(!esEjercicioM)
    		insertado = aeropuertoController.insertarAeropuerto(a);
    	else
    		insertado = aeropuertoController2.insertarAeropuerto(a);
    	
    	
    	if(insertado) //si se inserto se cierra la ventana
    		click_cancelar(null);
    	else //sino muestra el mensaje de error
    		txtError.setText("No se a podido añadir el aeropuerto");
    }
	
	/**
	 * Dependiendo de la tecla que se preccione si es enter intentara guardar el aeropuerto creado  o 
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
	
	/**
	 * Al darle al boton seleccionar imagen se abrira el exporar de archivo y seleccionara la imagen y 
	 * lo guaradar en una variable 
	 */
	@FXML
    void click_select_imagen(ActionEvent event) {
		imageBinary = seleccionarImagen(false);
	}
	
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
		if(esPublico)
	 		click_publico(null);
	 	else
	 		click_privado(null);
		imageSelected.setVisible(false);
    	cambiarInterfazEjercicioM();
    		
    }
    
}
