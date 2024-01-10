package controllers;

import java.net.URL;
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

public class AniadirConsultaController implements Initializable{

    @FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<Animal> cmbAnimal;

    @FXML
    private DatePicker dpFecha;

    @FXML
    private TextArea taObservacion;

    @FXML
    private Text txtError;

    @FXML
    private Text txtTitulo;
    
    private AnimalController controller;
    
    public void setController(AnimalController controller) {
    	this.controller = controller;
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
    		Consulta consulta = new Consulta();
    		consulta.setFecha(java.sql.Date.valueOf(dpFecha.getValue()));
    		consulta.setObservacion(taObservacion.getText());
    		consulta.setAnimal(cmbAnimal.getSelectionModel().getSelectedItem());
    		
    		if(controller.aniadirConsulta(consulta)) {
    			controller.getAnimals().get(cmbAnimal.getSelectionModel().getSelectedIndex()).addConsulta(consulta);
    			click_cancelar(event);
    		}else
    			txtError.setText("No se podido insertar consulta.");
    		
    	}else
    		txtError.setText(msg);
    }
    
    private String validar() {
    	if(taObservacion.getText().isEmpty())
    		return "No se a puesto ninguna observacion";
    	System.out.println("Fecha: " +dpFecha.getValue());
    	if(dpFecha.getValue() == null)
    		return "No se selecciona la fecha";
    	return "";
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	txtTitulo.setText("Añadir Consulta");
    	cmbAnimal.setItems(controller.getAnimals());
    	cmbAnimal.getSelectionModel().selectFirst();
    }

}
