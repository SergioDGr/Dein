package controllers;

import java.net.URL;

import java.time.ZoneId;

import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Animal;
import model.Consulta;

public class EditarConsultaController implements Initializable{

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<Consulta> cmbAnimal;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private TextArea taObservacion;

    @FXML
    private Text txtError;

    @FXML
    private Text txtTitulo;
    
    @FXML
    private Text txtCmb;
    
    private AnimalController controller;
    
    private Animal animal;
    
    public void setController(AnimalController controller) {
    	this.controller = controller;
    }
    
    public void setAnimal(Animal animal) {
    	this.animal = animal;
    }
    
    @FXML
    void click_cancelar(ActionEvent event) {
    	Stage stage = (Stage) btnCancelar.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void click_guardar(ActionEvent event) {
    	String msg = validar();
    	if(msg.isEmpty()) {
    		txtError.setText("");
    		Consulta consulta = animal.getConsultas().get(cmbAnimal.getSelectionModel().getSelectedIndex());
    		consulta.setFecha(java.sql.Date.valueOf(dpFecha.getValue()));
    		consulta.setObservacion(taObservacion.getText());
    		
    		if(controller.editarConsulta(consulta)) {
    			animal.modConsulta(cmbAnimal.getSelectionModel().getSelectedIndex(), consulta);
    			if( animal.fechaMaxPequenia(consulta.getFecha())) {
    				animal.setFecha_primera_consulta(consulta.getFecha());
    				controller.modificarFechaAnimal(animal);
    			}
    			click_cancelar(event);
    		}else
    			txtError.setText("No se podido editar la consulta.");
    		
    	}else
    		txtError.setText(msg);
    }
    
    @FXML
    void select_Observacion(ActionEvent event) {
    	System.out.println("Fecha: " +dpFecha.getValue());
    	Consulta con = cmbAnimal.getSelectionModel().getSelectedItem();
    	dpFecha.setValue(con.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    	taObservacion.setText(con.getObservacion());
    }
    
    private String validar() {
    	if(taObservacion.getText().isEmpty())
    		return "No se a puesto ninguna observacion";
    	if(dpFecha.getValue() == null)
    		return "No se selecciona la fecha";
    	return "";
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	txtTitulo.setText("Editar Consulta");
    	txtCmb.setText("Consulta");
    	
    	cmbAnimal.setItems(animal.getConsultas());
    	cmbAnimal.getSelectionModel().selectFirst();
    	
    	select_Observacion(null);
    }
    
}
