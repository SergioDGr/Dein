package controllers;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

/**
 * Controlador para la pantalla del ejercicioA
 * 
 */
public class EncuestaController implements Initializable{
	
	@FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private ComboBox<String> cmbEdad;

    @FXML
    private ListView<String> lvDeportes;

    @FXML
    private RadioButton rdbHombre;

    @FXML
    private RadioButton rdbMujer;

    @FXML
    private RadioButton rdbOtro;

    @FXML
    private Slider sldCine;

    @FXML
    private Slider sldCompras;

    @FXML
    private Slider sldTv;

    @FXML
    private TextField tfNumsHermanos;

    @FXML
    private TextField tfProfesion;

    @FXML
    private CheckBox cxbPracDeport;
    
    /**
     * Cuando el checkbox es seleccionado se puede selecionar 
     * alguno de los elementos de listview
     * @param event
     */
    @FXML
    void check_deporte(ActionEvent event) {
    	if (cxbPracDeport.isSelected())
    		lvDeportes.setDisable(false);
    	else
    		lvDeportes.setDisable(true);
    }
    
    /**
     * Al darle al boton cancelar cerrara la ventana
     * @param event
     */
    @FXML
    void click_Cancelar(ActionEvent event) {
    	Stage stage = (Stage) btnCancelar.getScene().getWindow();
    	stage.close();
    }
    
    /**
     * Al darle al boton aceptar abrira un modal con la 
     * informacion de la encuesta o los errores que hay por no 
     * rellenar de manera correcta la encuesta
     * @param event
     */
    @FXML
    void click_aceptar(ActionEvent event) {
    	Alert alert;
    	String mensajeErrores = validarCampos();
    	if (mensajeErrores.isEmpty()) {
	    	alert = new Alert(Alert.AlertType.INFORMATION);
	    	alert.setContentText(mensajeARellenar());
    	}else {
    		alert = new Alert(Alert.AlertType.ERROR);
    		alert.setContentText(mensajeErrores);
    	}
    	alert.setTitle("TUS DATOS");
        alert.setHeaderText("");
        alert.showAndWait();
    }
    
    /**
     * Metodo que valida los elementos de la encuesta
     * @return devuelve un mensaje con errores en caso contrario 
     * devuele un mensaje vacio
     */
    private String validarCampos() {
    	String mensajeError = "";
    	
    	if (tfProfesion.getText().isEmpty())
    		mensajeError += "Hay que rellenar el campo de profesión\n";
    	if (tfNumsHermanos.getText().isEmpty())
    		mensajeError += "Hay que rellenar el campo de numero de hermanos\n";
    	else
    		try {
				Integer.parseInt(tfNumsHermanos.getText());
			} catch (NumberFormatException e) {
				mensajeError += "El campo de numero de hermanos debe ser numerico";
			}
    	if (cxbPracDeport.isSelected() && lvDeportes.getSelectionModel().isEmpty())
    		mensajeError += "Tienes que indicar los deportes que practicas";
    	return mensajeError;
    }
    
    /**
     * 
     * @return
     */
    private String mensajeARellenar() {
    	String mensaje = "";
    	
    	mensaje += "Profesion: " + tfProfesion.getText() + "\n";
    	mensaje += "Nº de hermanos: " + tfNumsHermanos.getText() + "\n";
    	mensaje += "Edad: " + cmbEdad.getSelectionModel().getSelectedItem() + "\n";
    	mensaje += "Sexo: ";
    	
    	if (rdbHombre.isSelected())
    		mensaje += rdbHombre.getText() + "\n";
    	if (rdbMujer.isSelected())
    		mensaje += rdbMujer.getText() + "\n";
    	if (rdbOtro.isSelected())
    		mensaje += rdbOtro.getText() + "\n";
		if (cxbPracDeport.isSelected()) {
			mensaje += "Deportes que practicas:\n";
			ObservableList<String> lvItems = lvDeportes.getSelectionModel().getSelectedItems();
			for (String deporte : lvItems) {
				mensaje += "\t" + deporte + "\n";
			}
		}
	
		mensaje += "Grado de aficción a las compras:" + sldCompras.getValue() + "\n";
		mensaje += "Grado de aficción a ver la televisión:" + sldTv.getValue() + "\n";
		mensaje += "Grado de aficción a ir al cine:" + sldCine.getValue() + "\n";
		
    	return mensaje;
    }
    
    
    /**
     * Al inicializar la aplicacion se cargaran el combobox con edades y 
     * el listview con deportes
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	ObservableList<String> itemsCmb = FXCollections.observableArrayList("Menores de 18",
    			"Entre 18 y 30", "Entre 31 y 50", "Entre 51 y 70", "Mayores de 70" );
    	cmbEdad.setItems(itemsCmb);
    	cmbEdad.getSelectionModel().selectFirst();
    	ObservableList<String> itemsListVIew = FXCollections.observableArrayList("Tenis", "Fútbol", "Baloncesto",
    			"Natación", "Ciclismo", "Otro");
    	lvDeportes.setItems(itemsListVIew);
    	lvDeportes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    

}
