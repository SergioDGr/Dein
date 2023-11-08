package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Clase controlador base para ventanas modal para los aeropuertos
 */
public class AeropuertoModalController {
	
	@FXML
	protected GridPane gpPanel;
	
	@FXML
    protected Button btnCancelar;

    @FXML
    protected Button btnGuardar;
    
    @FXML
    protected Button btnImagen;
    
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
    
    @FXML
    protected Text txtImagen;
    
    @FXML
    protected ImageView imageSelected;
    
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
    void click_select_imagen(ActionEvent event) {}
    
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
    
    protected InputStream seleccionarImagen(boolean editar) {
    	InputStream imageBinary = null;
    	FileChooser fileChooser = new FileChooser();
    	Stage stage = new Stage();
    	fileChooser.setTitle("Seleccionar Imagen ");
    	ExtensionFilter jpgFilter = new ExtensionFilter("Imagen JPG (*.jpg)", "*.jpg");
    	fileChooser.getExtensionFilters().add(jpgFilter);
    	File imageFile = fileChooser.showOpenDialog(stage);
    	if(imageFile != null) {
    		try {
    			Image img = new Image(imageFile.toURI().toString());
				imageBinary = new FileInputStream(imageFile);
				imageSelected.setVisible(true);
	    		imageSelected.setImage(img);
	    		gpPanel.getRowConstraints().get(8).setPrefHeight(150);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
    	}else {
    		if(!editar) {
	    		imageSelected.setVisible(false);
	    		imageSelected.setImage(null);
	    		gpPanel.getRowConstraints().get(8).setPrefHeight(0);
    		}
    	}
    	return imageBinary;
    }
    
    /**
     * Cambia la interfaz para el jercicio M 
     */
    protected void cambiarInterfazEjercicioM() {
    	
    	if(esEjercicioM) {
    		txtImagen.setVisible(true);
    		btnImagen.setVisible(true);
    		
    		gpPanel.getChildren().remove(txtError);
    		gpPanel.getChildren().remove(btnCancelar);
    		gpPanel.getChildren().remove(btnGuardar);
    		
    		gpPanel.add(btnGuardar, 0, 13);
    		gpPanel.add(btnCancelar, 1, 13);
    		gpPanel.add(txtError, 0, 12);
    		
    		GridPane.setColumnSpan(btnGuardar, 2);
    		
    		GridPane.setMargin(btnCancelar, new Insets(0, 75, 10, 0));
    		GridPane.setMargin(btnGuardar, new Insets(0, 75, 10, 0));
    		
    		GridPane.setRowIndex(rbtnPrivado, 9);
    		GridPane.setRowIndex(rbtnPublico, 9);
    		GridPane.setRowIndex(txtSocios, 10);
    		GridPane.setRowIndex(tfNumSocios, 10);
    		GridPane.setRowIndex(txtFinanciacion, 10);
    		GridPane.setRowIndex(tfFinanciacion, 10);
    		GridPane.setRowIndex(txtTrabajadores, 11);
    		GridPane.setRowIndex(tfNumTrabajadores, 11);
    	}else {
    		imageSelected.setFitWidth(0);
    		imageSelected.setFitHeight(0);
    	}
    }
    
}
