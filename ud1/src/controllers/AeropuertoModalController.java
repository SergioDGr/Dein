package controllers;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

import javafx.stage.Stage;

/**
 * Clase controlador base para ventanas modal para los aeropuertos
 */
public class AeropuertoModalController {
	
	@FXML
    protected Button btnCancelar;

    @FXML
    protected Button btnGuardar;

    @FXML
    protected RadioButton rbtnPrivado;

    @FXML
    protected RadioButton rbtnPublico;

    @FXML
    protected TextField tfAnio;

    @FXML
    protected TextField tfCalle;

    @FXML
    protected TextField tfCapacidad;

    @FXML
    protected TextField tfCiudad;

    @FXML
    protected TextField tfFinanciacion;

    @FXML
    protected TextField tfNombre;

    @FXML
    protected TextField tfNumSocios;

    @FXML
    protected TextField tfNumTrabajadores;

    @FXML
    protected TextField tfNumero;

    @FXML
    protected TextField tfPais;

    @FXML
    protected ToggleGroup tgTipoAeropuerto;
    
    @FXML
    protected Text txtSocios;
    
    @FXML
    protected Text txtTrabajadores;
    
    @FXML
    protected Text txtFinanciacion;
    
    @FXML
    protected Text txtError;
    
    protected boolean esPublico;
    
    protected AeropuertoController aeropuertoController;
    
    protected AeropuertoController2 aeropuertoController2;
    
    protected boolean esEjercicioM = false;
    
    public void setAeropuertoController(AeropuertoController controller) {
    	aeropuertoController = controller;
    	esEjercicioM = false;
    }
    
    public void setAeropuertoController(AeropuertoController2 controller) {
    	aeropuertoController2 = controller;
    	esEjercicioM = true;
	}
    
    /**
     * Si se seleccion el radio button privado se cambia elementos para aeoropuerto privados
     * @param event
     */
    @FXML
    protected void click_privado(ActionEvent event) {
    	rbtnPublico.setSelected(false);
		tfFinanciacion.setVisible(false);
		tfNumTrabajadores.setVisible(false);
		tfNumSocios.setVisible(true);
		txtFinanciacion.setVisible(false);
		txtSocios.setVisible(true);
		txtTrabajadores.setVisible(false);
		esPublico = false;
    }
    
    /**
     * Si se seleccion el radio button publico se cambia elementos para aeoropuerto publico
     * @param event
     */
    @FXML
    protected void click_publico(ActionEvent event) {
    	rbtnPublico.setSelected(true);
		tfFinanciacion.setVisible(true);
		tfNumTrabajadores.setVisible(true);
		tfNumSocios.setVisible(false);
		txtFinanciacion.setVisible(true);
		txtSocios.setVisible(false);
		txtTrabajadores.setVisible(true);
		esPublico = true;
    }
    
    /**
     * al darle a cancelar cierra la ventana modal
     * @param event
     */
    @FXML
    protected void click_cancelar(ActionEvent event) {
    	Stage stage = (Stage) btnCancelar.getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    void clic_guardar_intro(KeyEvent event) {

    }
    
    @FXML
    void click_cancelar_escape(KeyEvent event) {

    }
    
    /**
     * Valida todos los campos text field si estan correctos
     * @return devuelve un {@link String} vacio si todo esta valido en caso contrario guardar el mesaje del error
     */
    protected String validar() {
    	if(tfNombre.getText().isEmpty() || tfPais.getText().isEmpty() || tfCiudad.getText().isEmpty() || tfCalle.getText().isEmpty() || tfNumero.getText().isEmpty() || 
    		tfAnio.getText().isEmpty() || tfCapacidad.getText().isEmpty() || (esPublico && (tfFinanciacion.getText().isEmpty() ||tfNumTrabajadores.getText().isEmpty()) 
    		|| (!esPublico && tfNumSocios.getText().isEmpty()))) {
    		return "Alguno de los campos esta vacio";
    	}
    	String enteroFormat = "^\\d+$";
    	String doubleFormat = "^\\d+(\\.\\d{2})?$|^\\d+(,\\d{2})?$";
    	if(!tfNumero.getText().matches(enteroFormat) || !tfAnio.getText().matches("\\d{4}") || !tfCapacidad.getText().matches(enteroFormat) ||
    			(esPublico && (!tfNumTrabajadores.getText().matches(enteroFormat) || !tfFinanciacion.getText().matches(doubleFormat)) || 
    					(!esPublico && !tfNumSocios.getText().matches(enteroFormat))))
    		return "Alguno de los campos no tienen el formato valido";
    	 return "";
    }
}
