package controllers;

import java.net.URL;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Persona;

public class PersonaController implements Initializable{

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
    
    @FXML
    private TextField tfApellidos;

    @FXML
    private TextField tfEdad;

    @FXML
    private TextField tfNombre;
    
    private ObservableList<Persona> lstPesonas = FXCollections.observableArrayList();
    
    @FXML
    void clic_addPersona(ActionEvent event) {
    	Alert alert = null;
    	String mensajeErrores = validarCampos();
    	boolean aniadido = false;
    	if (mensajeErrores.isEmpty()) {
    		Persona p = new Persona(tfNombre.getText(), tfApellidos.getText(), Integer.parseInt(tfEdad.getText()));
    		aniadido = aniadirPersona(p);
    		if (aniadido) {
		    	alert = new Alert(Alert.AlertType.INFORMATION);
		    	alert.setContentText("Persona añadida correctamente");
		    	alert.setTitle("Info");
    		}
    	}
    	if (! mensajeErrores.isEmpty() || !aniadido) {
    		if ( mensajeErrores.isEmpty() && !aniadido)
    			mensajeErrores = "La persona ya esta en el tabla\n";
    		alert = new Alert(Alert.AlertType.ERROR);
    		alert.setContentText(mensajeErrores);
    		alert.setTitle("Error");
    	}
    	
        alert.setHeaderText("");
        alert.showAndWait();
    }
    
    private String validarCampos() {
    	String mensaje = "";
    	if(tfNombre.getText().isEmpty())
			mensaje += "El campo nombre es obligatorio\n";
    	if(tfApellidos.getText().isEmpty())
    		mensaje += "El campo apellidos es obligatorio\n";
    	if(tfEdad.getText().isEmpty())
    		mensaje += "El campo edad es obligatorio\n";
    	else
    		try {
				Integer.parseInt(tfEdad.getText());
			} catch (Exception e) {
				mensaje += "La edad no es un entero";
			}

    	return mensaje;
    	
    }
    
    private boolean aniadirPersona(Persona p) {
    	
    	
    	System.out.println( lstPesonas.size());
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
