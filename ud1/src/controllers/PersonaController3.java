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
    		//
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EjercicioD_Modal.fxml"));
    		Parent parent = loader.load();
    		//Le pasamos el controlador al controlador de la ventana modal
    		NuevaPersonaController NuevaPersonaController = loader.getController();
    		NuevaPersonaController.setpersonaController(this);
    		//
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
    	
    }
    
    /**
     * Metodo que añada a la lista de personas una persona
     * @param p la persona a añadir
     * @return devuelve si se a podido o no añadir la persona
     */
    public boolean aniadirPersona(Persona p) {
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
