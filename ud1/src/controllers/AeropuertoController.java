package controllers;

import java.io.IOException;

import java.net.URL;

import java.util.ResourceBundle;

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

import javafx.stage.Modality;
import javafx.stage.Stage;

public class AeropuertoController implements Initializable{
	
	@FXML
    private RadioButton rbPrivados;

    @FXML
    private RadioButton rbPublicos;

    @FXML
    private TableView<?> tableAeropuerto;
    
    @FXML
    private TableColumn<?, ?> tbClmAnio;

    @FXML
    private TableColumn<?, ?> tbClmCalle;

    @FXML
    private TableColumn<?, ?> tbClmCapacidad;

    @FXML
    private TableColumn<?, ?> tbClmCiudad;

    @FXML
    private TableColumn<?, ?> tbClmFinaciacion;

    @FXML
    private TableColumn<?, ?> tbClmID;

    @FXML
    private TableColumn<?, ?> tbClmNombre;

    @FXML
    private TableColumn<?, ?> tbClmNumSocios;

    @FXML
    private TableColumn<?, ?> tbClmNumTrabajadores;

    @FXML
    private TableColumn<?, ?> tbClmNumero;

    @FXML
    private TableColumn<?, ?> tbClmPais;
    
    @FXML
    private TextField tfNombre;

    @FXML
    private ToggleGroup tipoAeropuerto;
    
    @FXML
    void click_addAeropuerto(ActionEvent event) {

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
     * carga y muestra la ventana modal con los siguientes paramatros:
     * @param controlador El controlador de la ventana
     * @param titulo El titulo de la ventana
     * @return Devuelve el controlador
     */
    public Object cargar_ventana_modal(Object controlador , String titulo) throws IOException {
    	//Cargamos la intefaz que se visualizara
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EjercicioL_Login.fxml"));
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
		newStage.showAndWait();
		return controlador;
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }

}
