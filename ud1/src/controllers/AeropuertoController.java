package controllers;

import java.io.IOException;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.AeropuertoDao;
import dao.AvionDao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import model.Aeropuerto;
import model.AeropuertoPrivado;
import model.AeropuertoPublico;
import model.Avion;

/**
 * Controlador principipal que gestiona la ventana principal y todo lo referente a los aeropuertos
 */
public class AeropuertoController implements Initializable{
	
	@FXML
    private RadioButton rbPrivados;

    @FXML
    private RadioButton rbPublicos;

    @FXML
    private TableView<Aeropuerto> tableAeropuerto;
    
    @FXML
    private TableColumn<Aeropuerto, Integer> tbClmAnio;

    @FXML
    private TableColumn<Aeropuerto, String> tbClmCalle;

    @FXML
    private TableColumn<Aeropuerto, Integer> tbClmCapacidad;

    @FXML
    private TableColumn<Aeropuerto, String> tbClmCiudad;

    @FXML
    private TableColumn<AeropuertoPublico, Double> tbClmFinaciacion;

    @FXML
    private TableColumn<Aeropuerto, Integer> tbClmID;

    @FXML
    private TableColumn<Aeropuerto, String> tbClmNombre;

    @FXML
    private TableColumn<AeropuertoPrivado, Integer> tbClmNumSocios;

    @FXML
    private TableColumn<AeropuertoPublico, Integer> tbClmNumTrabajadores;

    @FXML
    private TableColumn<Aeropuerto, Integer> tbClmNumero;

    @FXML
    private TableColumn<Aeropuerto, String> tbClmPais;
    
    @FXML
    private TextField tfNombre;

    @FXML
    private ToggleGroup tipoAeropuerto;
    
    private AeropuertoDao aeropuertoDao;
    private AvionDao avionDao;
    
    private ObservableList<Aeropuerto> lstAeropuertoPrivados = FXCollections.observableArrayList();
    private ObservableList<Aeropuerto> lstAeropuertoPublicos = FXCollections.observableArrayList();
    
    /**
     * Se abre la ventana modal que gestiona el añadir un aeropuerto a la base de datos
     * @param event
     */
    @FXML
    void click_addAeropuerto(ActionEvent event) {
    	try {
    		AniadirAeropuertoController controller = new AniadirAeropuertoController();
    		controller.esPublico = rbPublicos.isSelected();
    		controller.setAeropuertoController(this);
			cargar_ventana_modal(controller, "/fxml/EjercicioL_Modal_Aeropuerto.fxml",  "AVIONES - AÑADIR AEROPUERTO",
					tableAeropuerto.getScene().getWindow() , new Image(getClass().getResource("/img/avion.png").toString()));
			tfNombre.setText("");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void click_addAvion(ActionEvent event) {
    	ObservableList<Aeropuerto> lstAeropuertos = FXCollections.observableArrayList();
    	lstAeropuertos.addAll(lstAeropuertoPublicos);
    	lstAeropuertos.addAll(lstAeropuertoPrivados);
    	try {
    		AniadirAvionController controller = new AniadirAvionController();
    		controller.setAeropuertoController(this);
        	controller.setLstAeropuertos(lstAeropuertos);
			cargar_ventana_modal(controller, "/fxml/EjercicioL_Avion.fxml", "AVIONES - AÑADIR AEROPUERTO", tableAeropuerto.getScene().getWindow(),
					new Image(getClass().getResource("/img/avion.png").toString()));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Se intenta eliminar el aeropuerto seleccionado de la tabla y en la base de datos
     * @param event
     */
    @FXML
    void click_delAeropuerto(ActionEvent event) {
    	//Si se seleccionado un aeropuerto
    	if(tableAeropuerto.getSelectionModel().getSelectedIndex() != -1) {
    		boolean eliminado;
    		//Meuesta una ventana de advertencia si realmente quiere eliminar el aeropuerto
    		ButtonType ok = new ButtonType("Sí", ButtonData.OK_DONE);
    		ButtonType close = new ButtonType("No", ButtonData.CANCEL_CLOSE);
    		Alert alert = new Alert(AlertType.WARNING, "¿Seguro que quiere eliminar el aeropuerto seleccionado?", ok, close);
    		alert.setTitle("Advetencia - Borrar AEROPUERTO");
    		alert.setHeaderText(null);
    		Optional<ButtonType> result = alert.showAndWait();
    		if(result.orElse(ok) == close) {
    			return;
    		}
    		//Si aeropuerto privado
    		if(rbPrivados.isSelected()) {
    			AeropuertoPrivado aPrivado = (AeropuertoPrivado) tableAeropuerto.getSelectionModel().getSelectedItem();
    			eliminado = aeropuertoDao.eliminarAeropuertoPrivado(aPrivado);
    			//si se apodido eliminar de la base de datos se quita de la tabla
    			if (eliminado)
    				lstAeropuertoPrivados.remove(aPrivado);
    		}else { //Si aeropuerto publico
    			AeropuertoPublico aPublico = (AeropuertoPublico) tableAeropuerto.getSelectionModel().getSelectedItem();
    			eliminado = aeropuertoDao.eliminarAeropuertoPublico(aPublico);
    			//si se apodido eliminar de la base de datos se quita de la tabla
    			if (eliminado)
    				lstAeropuertoPublicos.remove(aPublico);
    			else
    	    		crear_mostrar_alerta(AlertType.ERROR, "Error - Eliminar AEROPUERTO", "No se a podido eliminar el aeropuerto", tableAeropuerto.getScene().getWindow());

    		}
    		//Si se a eliminado muestra que se apodido
    		if (eliminado) 
    			crear_mostrar_alerta(AlertType.INFORMATION, "Informacion - Eliminar AEROPUERTO", "Se a eliminado el aeropuerto", tableAeropuerto.getScene().getWindow());
    	}else { //Mostrara un mensaje de error
    		crear_mostrar_alerta(AlertType.ERROR, "Error - Eliminar AEROPUERTO", "No se a seleccionado ningun aeropuerto", tableAeropuerto.getScene().getWindow());
    	}
    }

    @FXML
    void click_delAvion(ActionEvent event) {

    }
    
    /**
     * Se abre la ventana modal que gestiona la modificacion de un aeropuerto a la base de datos
     * @param event
     */
    @FXML
    void click_modAeropuerto(ActionEvent event) {
    	if(tableAeropuerto.getSelectionModel().getSelectedIndex() != -1) {
	    	try {
	    		EditarAeropuertoController controller = new EditarAeropuertoController();
	    		controller.esPublico = rbPublicos.isSelected();
	    		controller.setAeropuertoController(this);
				cargar_ventana_modal(controller, "/fxml/EjercicioL_Modal_Aeropuerto.fxml",  "AVIONES - Editar AEROPUERTO",
						tableAeropuerto.getScene().getWindow() , new Image(getClass().getResource("/img/avion.png").toString()));
				tfNombre.setText("");
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}else {
    		crear_mostrar_alerta(AlertType.ERROR, "Error - Editar AEROPUERTO", "No se a seleccionado ningun aeropuerto", tableAeropuerto.getScene().getWindow());
    	}
    }
    
    @FXML
    void click_on_offAvion(ActionEvent event) {

    }
    
    /**
     * Muestra la informacion del aeropuerto seleccionado
     * @param event
     */
    @FXML
    void click_viewInfoAeropuerto(ActionEvent event) {
    	Aeropuerto aeropuerto = tableAeropuerto.getSelectionModel().getSelectedItem();
    	if(aeropuerto != null) {
    		crear_mostrar_alerta(AlertType.INFORMATION, "Información", aeropuerto.toString(), tableAeropuerto.getScene().getWindow());
    	}else {
    		crear_mostrar_alerta(AlertType.ERROR, "Error", "No se a seleccionado ningun aeropuerto", tableAeropuerto.getScene().getWindow());

    	}
    }
    
    /**
     * Muestra los aeropuerto privados con las columnas que tiene.
     * @param event
     */
    @FXML
    void click_privado(ActionEvent event) {
    	tbClmNumSocios.setVisible(true);
    	tbClmNumTrabajadores.setVisible(false);
    	tbClmFinaciacion.setVisible(false);
    	tableAeropuerto.setItems(lstAeropuertoPrivados);
    	tfNombre.setText("");
    }

    /**
     * Muestra los aeropuerto publicos con las columnas que tiene.
     * @param event
     */
    @FXML
    void click_publico(ActionEvent event) {
    	tbClmNumSocios.setVisible(false);
    	tbClmNumTrabajadores.setVisible(true);
    	tbClmFinaciacion.setVisible(true);
    	tableAeropuerto.setItems(lstAeropuertoPublicos);
    	tfNombre.setText("");
    }
    
    /**
     * Crea y muestra un mensaje de alerta
     * @param tipoAlert El tipo de alerta
     * @param titulo El titulo que tendra la alerta
     * @param msg El mensaje que mostrara la alerta
     * @param window ventana propietaria
     */
    private void crear_mostrar_alerta(AlertType tipoAlert,String titulo, String msg, Window window) {
    	Alert alert = new Alert(tipoAlert);
    	alert.setHeaderText(null);
    	alert.setTitle(titulo);
    	alert.setContentText(msg);
    	alert.initOwner(window);
    	alert.showAndWait();
    }
    
    /**
     * carga y muestra la ventana modal con los siguientes paramatros:
     * @param controlador El controlador de la ventana
     * @param titulo El titulo de la ventana
     * @return Devuelve el controlador
     */
    public Object cargar_ventana_modal(Object controlador ,String fxml, String titulo, Window stage, Image img) throws IOException {
    	//Cargamos la intefaz que se visualizara
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
    	//Cargamos el controllador
		loader.setController(controlador);
		Parent parent = loader.load();
		//Creamos y visualizamos la venta
    	Scene newScene = new Scene(parent);
		Stage newStage = new Stage();
		newStage.initModality(Modality.APPLICATION_MODAL);
		//newStage.initOwner(window);
		newStage.setScene(newScene);
		newStage.setTitle(titulo);
		newStage.setResizable(false);
		newStage.initOwner(stage);
		if(img != null)
			newStage.getIcons().add(img);
		newStage.showAndWait();
		return controlador;
    }
    
    /**
     * Consigue el aeropuerto seleccionado de la tabla
     * @return devuelve un aeropuerto
     */
    public Aeropuerto getAeropuerto() {
    	return tableAeropuerto.getSelectionModel().getSelectedItem();
    }
    
    /**
     * Insertar un aeropuerto en la base de datos y si todo esta correcto tambien lo guardar en la tabla
     * referente a su tipo de aeropuerto que es
     * @param aeropuerto
     * @return devuelve <code>true</code> si se a podido hacer la operacion o <code>false</code> si no se apodido
     */
    public boolean insertarAeropuerto(Aeropuerto aeropuerto) {
    	boolean aniadido = false;
    	if(aeropuerto instanceof AeropuertoPublico) {
    		aniadido = aeropuertoDao.insertarAeropuertoPublico((AeropuertoPublico) aeropuerto);
    		if(aniadido)
    			lstAeropuertoPublicos.add(aeropuerto);
    	}else {
    		aniadido = aeropuertoDao.insertarAeropuertoPrivado((AeropuertoPrivado) aeropuerto);
    		if(aniadido)
    			lstAeropuertoPrivados.add(aeropuerto);
    	}
    	return aniadido;
    }
    
    public boolean insertarAvion(Avion avion, Aeropuerto aeropuerto) {
    	if(avionDao.insetarAvion(aeropuerto.getId(), avion)) {
    		aeropuerto.aviones.add(avion);
    		return true;
    	}
    	return false;
    }
    
    /**
     * con el aeropuerto privado que se pasan se modifica el aeropuerto privado seleccionada de la tabla,
     * esos cambios se reflejan tambien el base de datos y se refresca la tabla.
     * @param aeropuerto privado
     * @return devuelve <code>true</code> si se apodido modificar y <code>false</code> si no se apodido.
     */
    public boolean modificarAeropuertoPrivado(AeropuertoPrivado aeropuerto) {
		AeropuertoPrivado a = (AeropuertoPrivado) tableAeropuerto.getSelectionModel().getSelectedItem();
    	if(aeropuertoDao.modificarAeropuertoPrivado(aeropuerto) && aeropuertoDao.modificarAeropuerto(aeropuerto)
    			&& aeropuertoDao.modificarDireccion(aeropuerto.getDireccion())) {
    		a.setNombre(aeropuerto.getNombre());
    		a.setDireccion(aeropuerto.getDireccion());
    		a.setAnio(aeropuerto.getAnio());
    		a.setCapacidad(aeropuerto.getCapacidad());
    		a.setSocios(aeropuerto.getSocios());
    		tableAeropuerto.refresh();
    		return true;
    	}
    	return false;
    }
    
    /**
     * con el aeropuerto publico que se pasan se modifica el aeropuerto publico seleccionada de la tabla,
     * esos cambios se reflejan tambien el base de datos y se refresca la tabla.
     * @param aeropuerto publico
     * @return devuelve <code>true</code> si se apodido modificar y <code>false</code> si no se apodido.
     */
    public boolean modificarAeropuertoPublico(AeropuertoPublico aeropuerto) {
		AeropuertoPublico a = (AeropuertoPublico) tableAeropuerto.getSelectionModel().getSelectedItem();
    	if(aeropuertoDao.modificarAeropuertoPublico(aeropuerto) && aeropuertoDao.modificarAeropuerto(aeropuerto)
    			&& aeropuertoDao.modificarDireccion(aeropuerto.getDireccion())) {
    		a.setNombre(aeropuerto.getNombre());
    		a.setDireccion(aeropuerto.getDireccion());
    		a.setAnio(aeropuerto.getAnio());
    		a.setCapacidad(aeropuerto.getCapacidad());
    		a.setFinanciacion(aeropuerto.getFinanciacion());
    		a.setTrabajadores(aeropuerto.getTrabajadores());
    		tableAeropuerto.refresh();
    		return true;
    	}
    	return false;
    }
    
    /**
     * Consigue una lista filtrada de aeropuertos por el nombre pasado por el text field
     * @param lstAeropuerto el contenido de la tabla
     * @return devuelve la lista filtrada de aeropuertos
     */
    private ObservableList<Aeropuerto> getListaFiltrada(ObservableList<Aeropuerto> lstAeropuerto){
    	ObservableList<Aeropuerto> lstAeropuertoFiltrar = FXCollections.observableArrayList();
    	String nombre =  tfNombre.getText();
    	if (!nombre.isEmpty()) {
    		for (int i = 0; i < lstAeropuerto.size(); i++) {
				Aeropuerto a = lstAeropuerto.get(i);
				if (a.getNombre().toLowerCase().contains(nombre.toLowerCase()))
					lstAeropuertoFiltrar.add(a);
			}
    	}else {
    		lstAeropuertoFiltrar.setAll(lstAeropuerto);
    	}
    	return lstAeropuertoFiltrar;
    }
    
    /**
     * Guara los aviones en el aeropuerto
     * @param lstAeropuertos
     */
    private void getAvionPorAeropuerto(ObservableList<Aeropuerto> lstAeropuertos){
    	for (Aeropuerto aeropuerto : lstAeropuertos) {
			aeropuerto.aviones = avionDao.getAviones(aeropuerto.getId());
		}
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	//Conseguir datos de los aeropuertos
    	aeropuertoDao = new AeropuertoDao();
    	avionDao = new AvionDao();
    	lstAeropuertoPrivados = aeropuertoDao.getAeropuertoPrivados();
    	getAvionPorAeropuerto(lstAeropuertoPrivados);
    	lstAeropuertoPublicos = aeropuertoDao.getAeropuertoPublicos();
    	getAvionPorAeropuerto(lstAeropuertoPublicos);
    	
    	//Establecer las columnas
    	tbClmID.setCellValueFactory(new PropertyValueFactory<Aeropuerto, Integer>("id"));
    	tbClmNombre.setCellValueFactory(new PropertyValueFactory<Aeropuerto, String>("nombre"));
    	tbClmPais.setCellValueFactory(celldata -> celldata.getValue().getDireccion().getPaisProperty());
    	tbClmCiudad.setCellValueFactory(celldata -> celldata.getValue().getDireccion().getCiudadProperty());
    	tbClmCalle.setCellValueFactory(celldata -> celldata.getValue().getDireccion().getCalleProperty());
    	tbClmNumero.setCellValueFactory(celldata -> celldata.getValue().getDireccion().getNumProperty().asObject());
    	tbClmAnio.setCellValueFactory(new PropertyValueFactory<Aeropuerto, Integer>("anio"));
    	tbClmCapacidad.setCellValueFactory(new PropertyValueFactory<Aeropuerto, Integer>("capacidad"));
    	tbClmNumSocios.setCellValueFactory(new PropertyValueFactory<AeropuertoPrivado, Integer>("socios"));
    	tbClmNumTrabajadores.setCellValueFactory(new PropertyValueFactory<AeropuertoPublico, Integer>("trabajadores"));
    	tbClmFinaciacion.setCellValueFactory(new PropertyValueFactory<AeropuertoPublico, Double>("financiacion"));
    	
    	//Redefinir el evento del texto del text filed para filtrar
    	tfNombre.textProperty().addListener((observable, oldValue, newValue) ->{
    		if(rbPrivados.isSelected())
    			tableAeropuerto.setItems(getListaFiltrada(lstAeropuertoPrivados));
    		else
    			tableAeropuerto.setItems(getListaFiltrada(lstAeropuertoPublicos));
    	});
    	
    	//Añadir a la tabla
    	tableAeropuerto.setItems(lstAeropuertoPrivados);
    }
}
