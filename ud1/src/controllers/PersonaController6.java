package controllers;

import java.io.IOException;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.PersonaDao;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import model.Persona;

public class PersonaController6 implements Initializable{

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
    
    @FXML
    private TextField tfFiltrarNombre;
    
    private List<Persona> lstPersona = new ArrayList<Persona>();
    
    private ObservableList<Persona> lstPesonasVisible = FXCollections.observableArrayList();
    
    private PersonaDao personaDao = new PersonaDao();
    
    /**
     * Al darle click al boton creara una ventana modal que gestionara
     * la creacion de la persona
     * @param event
     */
    @FXML
    void clic_addPersona(ActionEvent event) {
    	try {	//Cargamos la intefaz que se visualizara
    		NuevaPersonaController4 nuevaPersonaController = new NuevaPersonaController4();
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
    		lstPesonasVisible.remove(index);
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
    	int index = tablePersona.getSelectionModel().getSelectedIndex();
    	try {
    		//Si la lista esta vacia
    		if (lstPesonasVisible.size() == 0)
    			throw new Exception("No hay ninguna persona en la tabla.");
    		//Si no se a seleccionado ninguna persona
    		if (index == -1)
    			throw new Exception("No se a seleccionado ningun personsa.");
    		//Creamos el controlador que queremos para la ventana modal
    		EditarPersonaController3 editarPersonaController = new EditarPersonaController3();
    		//Le pasamos el controlador principal al controlador de la ventana modal
    		editarPersonaController.setpersonaController(this);
    		//Creamos la ventana y la visualizamos
    		cargar_ventana_modal(editarPersonaController, "Editar Persona", this.btnModPersona.getScene().getWindow());
		} catch (Exception e) {
			crear_mostrar_alerta(Alert.AlertType.ERROR, "Error", e.getMessage());
		}
    }
    
    /**
     * Crea y muestra un mensaje de alerta
     * @param tipoAlert El tipo de alerta
     * @param titulo El titulo que tendra la alerta
     * @param msg El mensaje que mostrara la alerta
     */
    private void crear_mostrar_alerta(AlertType tipoAlert,String titulo, String msg) {
    	Alert alert = new Alert(tipoAlert);
    	alert.setHeaderText(null);
    	alert.setTitle(titulo);
    	alert.setContentText(msg);
    	alert.showAndWait();
    }
    
    /**
     * carga y muestra la ventana modal con los siguientes paramatros:
     * @param controlador El controlador de la ventana
     * @param titulo El titulo de la ventana
     * @param window A la ventana que pertenece la ventana modal
     * @throws IOException en caso de que de error cuando cargue el fxml
     */
    private void cargar_ventana_modal(Object controlador , String titulo, Window window) throws IOException {
    	//Cargamos la intefaz que se visualizara
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EjercicioH_Modal.fxml"));
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
     * Metodo que añada a la lista de personas una persona y
     * lo guarda en la tabla persona de base de datos
     * @param p la persona a añadir
     * @return devuelve si se a podido o no añadir la persona
     */
    public boolean aniadirPersona(Persona p) {
    	if(lstPersona.contains(p))
    		return false;
    	if(!personaDao.insertarPersona(p))
			return false;
    	if (p.getNombre().toLowerCase().contains(tfFiltrarNombre.getText().toLowerCase())
    			|| tfFiltrarNombre.getText().isEmpty())
    		lstPesonasVisible.add(p);
    	lstPersona.add(p);
    	
    	return true;
    }
    
    /**
     * Se pasa una persona y se valida si esta en la lista de personas
     * @param p : persona que se valida si esta en la lista
     * @return Devuelve <code>true</code> si esta la persona y 
     * <code>false</code> si no esta la persona
     */
    public boolean estaPersona(Persona p) {
    	return lstPesonasVisible.contains(p);
    }
    
    /**
     * Con los parametro que se pasan se modifica la persona seleccionada 
     * de la tabla y se refresca
     * @param nombre
     * @param apellidos
     * @param edad
     */
    public String modificarPersona(String nombre, String apellidos, int edad) {
    	int index = tablePersona.getSelectionModel().getSelectedIndex();
    	Persona p = tablePersona.getSelectionModel().getSelectedItem();
    	p.setNombre(nombre);
		p.setApellidos(apellidos);
		p.setEdad(edad);
    	if (personaDao.modificarPersona(p)) {
			lstPersona.set(index, p);
			tablePersona.refresh();
			return "";
    	}else
    		return "No se podido modificar a la persona";
		
    }
    
    public Persona getPersona() {
    	return tablePersona.getSelectionModel().getSelectedItem();
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	tbClmNombre.setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
    	tbClmEdad.setCellValueFactory(new PropertyValueFactory<Persona, Integer>("edad"));
    	tbClmApellidos.setCellValueFactory(new PropertyValueFactory<Persona, String>("apellidos"));
    	
    	/* Cada vez que escriba en textfield la tabla se filtrara
	     * con los resultados que concuerdan con los caracteres 
	     * que esten el apartado del nombre de la persona
	     */
    	tfFiltrarNombre.textProperty().addListener((observable, oldValue, newValue) ->{
    		String nombre =  tfFiltrarNombre.getText();
        	if (!nombre.isEmpty()) {
    	    	List<Persona> lstPersonaFiltrar = FXCollections.observableArrayList();
    	    	for (int i = 0; i < lstPersona.size(); i++) {
    				Persona p = lstPersona.get(i);
    				if (p.getNombre().toLowerCase().contains(nombre.toLowerCase()))
    					lstPersonaFiltrar.add(p);
    			}
    	    	lstPesonasVisible.setAll(lstPersonaFiltrar);
        	}else {
        		lstPesonasVisible.setAll(lstPersona);
        	}
		});
    	
    	/*
    	 * Carga los datos de las persona que estan en la base de datos en la lista
	     * Para que se visualice en la tabla
	     */
    	lstPersona.addAll(personaDao.cargarPersona());
    	lstPesonasVisible.addAll(lstPersona);
    	
    	tablePersona.setItems(lstPesonasVisible);
    }
    
}
