package controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Persona;

public class PersonaController5 implements Initializable{

    @FXML
    private Button btnAddPersona;
    
    @FXML
    private Button btnDelPersona;

    @FXML
    private Button btnModPersona;
    
    @FXML
    private Button btnExportar;

    @FXML
    private Button btnImportar;
    
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
    
    /**
     * Al darle click al boton creara una ventana modal que gestionara
     * la creacion de la persona
     * @param event
     */
    @FXML
    void clic_addPersona(ActionEvent event) {
    	try {	//Cargamos la intefaz que se visualizara
    		NuevaPersonaController3 nuevaPersonaController = new NuevaPersonaController3();
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
    		EditarPersonaController2 editarPersonaController = new EditarPersonaController2();
    		//Le pasamos el controlador principal al controlador de la ventana modal
    		editarPersonaController.setpersonaController(this);
    		//Creamos la ventana y la visualizamos
    		cargar_ventana_modal(editarPersonaController, "Editar Persona", this.btnModPersona.getScene().getWindow());
		} catch (Exception e) {
			crear_mostrar_alerta(Alert.AlertType.ERROR, "Error", e.getMessage());
		}
    }
    
    @FXML
    void click_exportar(ActionEvent event) {
    	
    	File selectedFile = elegirFicheroCsv();
    	if (selectedFile != null) {
    		try {
				FileWriter fw = new FileWriter(selectedFile);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter pw = new PrintWriter(bw);
				pw.println("Nombre,Apellidos,dad");
				for (Iterator<Persona> iterator = lstPersona.iterator(); iterator.hasNext();) {
					Persona persona = (Persona) iterator.next();
					pw.println(persona.toString());
				}
				pw.flush();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    		crear_mostrar_alerta(AlertType.INFORMATION, "Ficheros Guardados", 
    				"Se han exportado todas las personas de la tabla.");
    	}
    }
    
    /**
     * Evento al darle click al boton de importar, seleccionaremos el fichero csv
     * con el formato de linea (nombre,apellidos,edad) y la primera linea la cabezera.
     * Se pondra a la tabla, validara si se puede poner las personas y al final visualizara
     * si a podido o no guarda los datos en la tabla.
     * @param event
     */
    @FXML
    void click_importar(ActionEvent event) {
    	File selectedFile = elegirFicheroCsv();
    	
    	try {
			FileReader fr = new FileReader(selectedFile);
			BufferedReader br = new BufferedReader(fr);
			
			String linea = "";
			br.readLine();
			int aniadidas = 0;
			int totalLinea = 0;
			while ((linea = br.readLine()) != null) {
				String[] persona = linea.split(",");
				boolean aniadido = aniadirPersona(new Persona(persona[0], persona[1], Integer.parseInt(persona[2])));
				if(aniadido) {
					aniadidas += 1;
				}
				totalLinea += 1;
			}
			br.close();
			
			if (aniadidas == totalLinea)
				crear_mostrar_alerta(AlertType.INFORMATION, "Info personas", "Todos las personas del fichero csv se "
					+ "han añadido");
			else if (aniadidas == 0)
				crear_mostrar_alerta(AlertType.ERROR, "Error personas", "Todos las personas del fichero csv no se "
						+ "han podido añadir");
			else
				crear_mostrar_alerta(AlertType.WARNING, "Advertencia personas", "No se ha pidio añadir tolas las persona"
						+ " que hay en el fichero csv");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}catch (NullPointerException e) {
			System.out.println("Se a cerrado el seleccionador de ficheros");
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
    
    private File elegirFicheroCsv() {
    	FileChooser fileChooser = new FileChooser();
    	Stage stage = new Stage();
    	fileChooser.setTitle("El fichero CSV");
    	ExtensionFilter csvFilter = new ExtensionFilter("Archivos CSV (*.csv)", "*.csv");
    	fileChooser.getExtensionFilters().add(csvFilter);
    	return (File) fileChooser.showOpenDialog(stage);
    }
    
    /**
     * Metodo que añada a la lista de personas una persona
     * @param p la persona a añadir
     * @return devuelve si se a podido o no añadir la persona
     */
    public boolean aniadirPersona(Persona p) {
    	if(lstPersona.contains(p))
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
    public void modificarPersona(String nombre, String apellidos, int edad) {
    	int index = tablePersona.getSelectionModel().getSelectedIndex();
    	Persona p = tablePersona.getSelectionModel().getSelectedItem();
    	p.setNombre(nombre);
		p.setApellidos(apellidos);
		p.setEdad(edad);
		lstPersona.set(index, p);
		tablePersona.refresh();
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
    	
    	tablePersona.setItems(lstPesonasVisible);
    }
    
}
