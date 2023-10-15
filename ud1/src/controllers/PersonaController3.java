package controllers;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Modality;
import javafx.stage.Stage;

import model.Persona;

public class PersonaController3 implements Initializable{

    @FXML
    private Button btnAddPersona;
    
    @FXML
    private TableView<Persona> tablePersona;
    
    @FXML
    private TableColumn<Persona, String> tbClmApellidos;

    @FXML
    private TableColumn<Persona, Integer> tbClmEdad;

    @FXML
    private TableColumn<Persona, String> tbClmNombre;
    
    private ObservableList<Persona> lstPesonas = FXCollections.observableArrayList();
    
    /**
     * Al darle click al boton intenta añadir una persona, muestra una alerta 
     * si se a podido añadir o no la persona
     * @param event
     */
    @FXML
    void clic_addPersona(ActionEvent event) {
    	
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EjercicioD_Modal.fxml"));
    		Parent parent = loader.load();
    		Scene newScene = new Scene(parent);
    		Stage newStage = new Stage();
    		newStage.initModality(Modality.APPLICATION_MODAL);
    		newStage.initOwner(this.btnAddPersona.getScene().getWindow());
    		newStage.setScene(newScene);
    		newStage.setTitle("Nueva Persona");
    		newStage.showAndWait();
		} catch (Exception e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setHeaderText(null);
	        alert.setTitle("Error");
	        alert.setContentText(e.getMessage());
	        alert.showAndWait();
		}
    	
    	
    	/*Alert alert = null;
    	String mensajeErrores = validarTextFields();
    	boolean aniadido = false;
    	//En caso que no haya errores
    	if (mensajeErrores.isEmpty()) {
    		Persona p = new Persona(tfNombre.getText(), tfApellidos.getText(), Integer.parseInt(tfEdad.getText()));
    		//Se intenta añadir la persona
    		aniadido = aniadirPersona(p);
    		if (aniadido) {
    			//En caso de que se podido añadirlo
    			alert = crearAlert(Alert.AlertType.INFORMATION, "Persona añadida correctamente","info");
    		}
    	}
    	//Cuando hay algun error
    	if (! mensajeErrores.isEmpty() || !aniadido) {
    		if ( mensajeErrores.isEmpty() && !aniadido)
    			mensajeErrores = "La persona ya esta en el tabla\n";
    		alert = crearAlert(Alert.AlertType.ERROR, mensajeErrores,"Error");

    	}
    	mostrarAlert(alert);*/
    }
    
    /**
     * Metodo que valida los textfield que tiene la informacion 
     * cada apartado de la persona
     * @return devuelve un mensaje con los errores que hay en caso que no
     * haya devolvera vacio en mensaje
     */
    /*private String validarTextFields() {
    	String mensaje = "";
    	//Validaciones
    	if(tfNombre.getText().isEmpty())
			mensaje += "El campo nombre es obligatorio\n";
    	if(tfApellidos.getText().isEmpty())
    		mensaje += "El campo apellidos es obligatorio\n";
    	if(tfEdad.getText().isEmpty())
    		mensaje += "El campo edad es obligatorio\n";
    	else
    		try {
				Integer.parseInt(tfEdad.getText());
			} catch (Exception e) {
				mensaje += "La edad no es un entero";
			}

    	return mensaje;
    }*/
    
    
    /**
     * Metodo que añada a la lista de personas una persona
     * @param p la persona a añadir
     * @return devuelve si se a podido o no añadir la persona
     */
    private boolean aniadirPersona(Persona p) {
    	if(lstPesonas.contains(p))
    		return false;
    	lstPesonas.add(p);
    	return true;
    }

    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	tbClmNombre.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
    	tbClmEdad.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("edad"));
    	tbClmApellidos.setCellValueFactory(new PropertyValueFactory<Persona, String>("apellidos"));
    	
    	tablePersona.setItems(lstPesonas);
    }

}
