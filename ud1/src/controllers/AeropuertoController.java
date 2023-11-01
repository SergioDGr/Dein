package controllers;

import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;

import dao.AeropuertoDao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import model.Aeropuerto;
import model.AeropuertoPrivado;
import model.AeropuertoPublico;

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
    
    private ObservableList<Aeropuerto> lstAeropuertoPrivados = FXCollections.observableArrayList();
    private ObservableList<Aeropuerto> lstAeropuertoPublicos = FXCollections.observableArrayList();
    
    /**
     * Se abre la ventana modal que gestiona el añadir un aeropuerto a la base de datos
     * @param event
     */
    @FXML
    void click_addAeropuerto(ActionEvent event) {
    	try {
    		AeropuertoAniadirController controller = new AeropuertoAniadirController();
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

    }

    @FXML
    void click_delAeropuerto(ActionEvent event) {

    }

    @FXML
    void click_delAvion(ActionEvent event) {

    }

    @FXML
    void click_modAeropuerto(ActionEvent event) {

    }
    
    @FXML
    void click_on_offAvion(ActionEvent event) {

    }

    @FXML
    void click_viewInfoAeropuerto(ActionEvent event) {

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
				if (a.getNombre().toLowerCase().substring(0, nombre.length())
						.contains(nombre.toLowerCase()))
					lstAeropuertoFiltrar.add(a);
			}
    	}else {
    		lstAeropuertoFiltrar.setAll(lstAeropuerto);
    	}
    	return lstAeropuertoFiltrar;
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	//Conseguir datos de los aeropuertos
    	aeropuertoDao = new AeropuertoDao();
    	lstAeropuertoPrivados = aeropuertoDao.getAeropuertoPrivados();
    	lstAeropuertoPublicos = aeropuertoDao.getAeropuertoPublicos();
    	
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
