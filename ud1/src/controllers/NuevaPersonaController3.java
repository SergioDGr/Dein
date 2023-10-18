package controllers;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import model.Persona;

public class NuevaPersonaController3 {
	
	private PersonaController5 personaController;
	
	@FXML
    private Button btnCancelar;

    @FXML
    private Button btnGuardar;

    @FXML
    private TextField tfApellidos;

    @FXML
    private TextField tfEdad;

    @FXML
    private TextField tfNombre;
    
    @FXML
    private Text txtError;

    /**
     * Al darle al boton cancelar cerrar la ventana
     * @param event
     */
    @FXML
    void click_cancelar(ActionEvent event) {
    	Stage stage = (Stage) btnCancelar.getScene().getWindow();
    	stage.close();
    }
    
    /**
     * Al darle click al boton guardara la infomacion de los 3 textfield
     * para crear a la persona y guardarla en la tabla que esta en ventana
     * principal
     * @param event
     */
    @FXML
    void click_guardar(ActionEvent event) {
    	String mensajeErrores = validarTextFields();
    	boolean aniadido = false;
    	//En caso que no haya errores
    	if (mensajeErrores.isEmpty()) {
    		Persona p = new Persona(tfNombre.getText(), tfApellidos.getText(), Integer.parseInt(tfEdad.getText()));
    		//Se intenta añadir la persona
    		aniadido = personaController.aniadirPersona(p);
    		if (aniadido) {
    			//En caso de que se podido añadirlo
    			Stage stage = (Stage) btnGuardar.getScene().getWindow();
    	    	stage.close();
    	    	return;
    		}
    	}
    	//Cuando hay algun error
    	if (! mensajeErrores.isEmpty() || !aniadido) {
    		if ( mensajeErrores.isEmpty() && !aniadido)
    			mensajeErrores = "La persona ya esta en el tabla\n";
    		txtError.setText(mensajeErrores);
    	}
    	
    }
    
    /**
     * Metodo que valida los textfield que tiene la informacion 
     * cada apartado de la persona
     * @return devuelve un mensaje con los errores que hay en caso que no
     * haya devolvera vacio en mensaje
     */
    private String validarTextFields() {
    	String mensaje = "";
    	//Validaciones
    	if(tfNombre.getText().isEmpty())
			mensaje += "El campo nombre es obligatorio\n";
    	if(tfApellidos.getText().isEmpty())
    		mensaje += "El campo apellidos es obligatorio\n";
    	if(tfEdad.getText().isEmpty())
    		mensaje += "El campo edad es obligatorio\n";
    	else
    		try {
				int edad = Integer.parseInt(tfEdad.getText());
				if(edad < 0 || edad > 150){
					mensaje += "La edad no es valida";
				}
			} catch (Exception e) {
				mensaje += "La edad no es un entero";
			}

    	return mensaje;
    }
    
    /**
     * Metodo para guardar al controlador de la ventana principal
     * @param personaController
     */
    public void setpersonaController(PersonaController5 personaController) {
        this.personaController = personaController;
    }
	
}
