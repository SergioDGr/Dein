package controllers;

import java.io.IOException;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Persona;

public class PersonaController4 implements Initializable{

    @FXML
    private Button btnAddPersona;
    
    @FXML
    private Button btnDelPersona;

    @FXML
    private Button btnModPersona;
    
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
     * Al darle click al boton creara una ventana modal que gestionara
     * la creacion de la persona
     * @param event
     */
    @FXML
    void clic_addPersona(ActionEvent event) {
    	try {	//Cargamos la intefaz que se visualizara
    		NuevaPersonaController2 nuevaPersonaController = new NuevaPersonaController2();
    		//Le pasamos el controlador al controlador de la ventana modal
    		nuevaPersonaController.setpersonaController(this);
    		//Creamos la ventana y la visualizamos
    		cargar_ventana_modal(nuevaPersonaController, "Nueva Persona", this.btnAddPersona.getScene().getWindow());
		} catch (Exception e) {
			crear_mostrar_alerta(Alert.AlertType.ERROR, "Error", e.getMessage());
		}
    }
    
    /**
     * Al darle click al boton elimina a la persona selecciona de la tabla.
     * Muestra una alerta si a podido eliminar o no a la persona correspondiente.
     * @param event
     */
    @FXML
    void click_delPersona(ActionEvent event) {
    	int index = tablePersona.getSelectionModel().getSelectedIndex();
    	AlertType tipoAlert;
    	String mensaje, titulo;
    	//Si se a seleccionado alguna persona
    	if (index != -1) {
    		lstPesonas.remove(index);
    		tipoAlert = Alert.AlertType.INFORMATION;
    		mensaje = "La persona se ha eliminado correctamente";
    		titulo = "Info";
    	}else { //En caso contrario
    		tipoAlert = Alert.AlertType.ERROR;
    		mensaje = "No se a seleciona ninguna";
    		titulo = "Error";
    	}
    	tablePersona.getSelectionModel().clearSelection();
    	crear_mostrar_alerta(tipoAlert, titulo, mensaje);
    }
    
    /**
     * Al darle click al boton creara una ventana modal que gestionara
     * la modificacion de la persona seleccionada
     * @param event
     */
    @FXML
    void click_modPersona(ActionEvent event) {
    	try {
    		//Creamos el controlador que queremos para la ventana modal
    		EditarPersonaController editarPersonaController = new EditarPersonaController();
    		//Le pasamos el controlador principal al controlador de la ventana modal
    		editarPersonaController.setpersonaController(this);
    		//Creamos la ventana y la visualizamos
    		cargar_ventana_modal(editarPersonaController, "Editar Persona", this.btnModPersona.getScene().getWindow());
		} catch (Exception e) {
			crear_mostrar_alerta(Alert.AlertType.ERROR, "Error", e.getMessage());
		}
    }
    
    private void crear_mostrar_alerta(AlertType tipoAlert,String titulo, String msg) {
    	Alert alert = new Alert(tipoAlert);
    	alert.setHeaderText(null);
    	alert.setTitle(titulo);
    	alert.setContentText(msg);
    	alert.showAndWait();
    }
    
    private void cargar_ventana_modal(Object controlador , String titulo, Window window) throws IOException {
    	//Cargamos la intefaz que se visualizara
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EjercicioE_Modal.fxml"));
    	//Cargamos el controllador
		loader.setController(controlador);
		Parent parent = loader.load();
		//Creamos y visualizamos la venta
    	Scene newScene = new Scene(parent);
		Stage newStage = new Stage();
		newStage.initModality(Modality.APPLICATION_MODAL);
		newStage.initOwner(window);
		newStage.setScene(newScene);
		newStage.setTitle(titulo);
		newStage.setResizable(false);
		newStage.showAndWait();
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
