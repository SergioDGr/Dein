package controllers;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import javafx.stage.Stage;

import model.Aeropuerto;

public class AvionModalController implements Initializable{
    
	@FXML
	private Button btnCancelar;

    @FXML
    protected Button btnGuardar;

    @FXML
    protected ComboBox<String> cmbAeropuerto;
    
    @FXML
    protected ComboBox<String> cmbAvion;
    
    @FXML
    protected GridPane gdPane;

    @FXML
    protected RadioButton rbActivado;

    @FXML
    protected RadioButton rbDesactivado;

    @FXML
    protected TextField tfAsientos;

    @FXML
    protected TextField tfModelo;

    @FXML
    protected TextField tfVelMax;

    @FXML
    private ToggleGroup tgEstado;
    
    @FXML
    protected Text txtTitulo;

    @FXML
    protected Text txtAeropuerto;

    @FXML
    protected Text txtAsientos;

    @FXML
    protected Text txtModelo;

    @FXML
    protected Text txtVelMax;
    
    @FXML
    protected Text txtError;
    
    @FXML
    protected Text txtRealizado;
    
    @FXML
    protected Text txtAvion;
    
    protected ObservableList<Aeropuerto> lstAeropuertos ;
    
    protected AeropuertoController aeropuertoController;
    
    public void setLstAeropuertos(ObservableList<Aeropuerto> lstAeropuertos) {
    	this.lstAeropuertos = lstAeropuertos;
    }
    
    public void setAeropuertoController(AeropuertoController controller) {
    	aeropuertoController = controller;
    }
    
    @FXML
	void change_cmbAeropuerto(ActionEvent event) {}
    
    /**
     * al darle a cancelar cierra la ventana modal
     * @param event
     */
    @FXML
    void click_cancelar(ActionEvent event) {
    	Stage stage = (Stage) btnCancelar.getScene().getWindow();
    	stage.close();
    }
    
    /**
     * Se consigue una lista de los avion del aeropuerto 
     * @param aeropuerto
     * @return devuelve la lista de los aviones del aeropuerto
     */
    protected ObservableList<String> getAvion(Aeropuerto aeropuerto){
		ObservableList<String> lstAvion = FXCollections.observableArrayList();
		if(!aeropuerto.aviones.isEmpty()) {
			btnGuardar.setDisable(false);
			aeropuerto.aviones.forEach(avion ->{
				 lstAvion.add(avion.getModelo());
			});
		}else {
			lstAvion.add("No tiene ningun Avión");
			btnGuardar.setDisable(true);
		}
		
		return lstAvion;
	 }
    
    /**
     * Cambia la interfaz inicial dedica a añadir por otro mas acorde activar/desactivar avion o eliminar 
     * avion.
     */
    protected void cambiarInterfaz() {
    	//Carga el avion
		change_cmbAeropuerto(null);
		//Se modifica el panel
		GridPane.setRowIndex(txtAeropuerto, 1);
		GridPane.setRowIndex(cmbAeropuerto, 1);
		GridPane.setRowSpan(rbActivado, 2);
		GridPane.setRowSpan(rbDesactivado, 2);
		GridPane.setRowSpan(txtError, 1);
		GridPane.setRowSpan(txtRealizado, 1);
		//Se cambia la visibilidad de los componenetes
		txtAvion.setVisible(true);
		cmbAvion.setVisible(true);
		txtAsientos.setVisible(false);
		tfAsientos.setVisible(false);
		txtModelo.setVisible(false);
		tfModelo.setVisible(false);
		txtVelMax.setVisible(false);
		tfVelMax.setVisible(false);
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	//Se consigue la lista de nombres aeropuerto
    	ObservableList<String> listaNombreAeropuertos = FXCollections.observableArrayList();
    	lstAeropuertos.forEach(aeropuerto ->{
    		listaNombreAeropuertos.add(aeropuerto.getNombre());
    	});
    	//Se guarda en el combo box de aeropuerto
    	cmbAeropuerto.setItems(listaNombreAeropuertos);
    	cmbAeropuerto.getSelectionModel().selectFirst();
    }
}   
	