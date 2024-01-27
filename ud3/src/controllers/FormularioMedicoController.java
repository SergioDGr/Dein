package controllers;

import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class FormularioMedicoController {

    @FXML
    private Button btnGenerarInforme;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnSalir;

    @FXML
    private Text txtError;
    
    @FXML
    private TextField tfCodMedico;

    @FXML
    private TextField tfDireccionPaciente;

    @FXML
    private TextField tfEspecMedico;

    @FXML
    private TextField tfNomMedico;

    @FXML
    private TextField tfNomPaciente;

    @FXML
    private TextField tfNumPaciente;

    @FXML
    private TextArea taTratamiento;

    @FXML
    void click_generar_informe(ActionEvent event) {
    	try {
    		String msg = validar();
    		if(!msg.isEmpty()){
    			txtError.setText(msg);
    			return;
    		}
    		txtError.setText("");
    		
    		Map<String, Object> parameters = new HashMap<String, Object>();
    		parameters.put("nombre_medico", tfNomMedico.getText());
    		parameters.put("especialidad_medico", tfEspecMedico.getText());
    		parameters.put("codigo_medico", tfCodMedico.getText());
    		parameters.put("nombre_paciente", tfNomPaciente.getText());
    		parameters.put("direccion_paciente", tfDireccionPaciente.getText());
    		parameters.put("numero_paciente", tfNumPaciente.getText());
    		parameters.put("tratamiento", taTratamiento.getText());
    		
    		InputStream jasper = getClass().getResourceAsStream("/jrxml/formulario_medico.jasper");
			JasperReport report = (JasperReport) JRLoader.loadObject(jasper);
	        JasperPrint jprint = JasperFillManager.fillReport(report, parameters , new JREmptyDataSource());
	        JasperViewer viewer = new JasperViewer(jprint, false);
	        viewer.setVisible(true);
		} catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Ha ocurrido un error");
            alert.showAndWait();
            e.printStackTrace();
        }
    }
    
    @FXML
    void click_limpiar(ActionEvent event) {
    	tfCodMedico.setText("");
    	tfDireccionPaciente.setText("");
    	tfEspecMedico.setText("");
    	tfNomPaciente.setText("");
    	tfNomMedico.setText("");
    	tfNumPaciente.setText("");
    	taTratamiento.setText("");
    }

    @FXML
    void click_salir(ActionEvent event) {
    	Stage stage = (Stage) btnSalir.getScene().getWindow();
    	stage.close();
    }

    
    private String validar() {
    	if(tfCodMedico.getText().isEmpty() || tfDireccionPaciente.getText().isEmpty() || tfEspecMedico.getText().isEmpty() || tfNomMedico.getText().isEmpty() 
    			|| tfNomPaciente.getText().isEmpty() || tfNumPaciente.getText().isEmpty() || taTratamiento.getText().isEmpty()) 
    		return "Alguno de los campo esta vacio.";
    	
    	try {
			Integer.parseInt(tfCodMedico.getText());
			Integer.parseInt(tfNumPaciente.getText());
		} catch (Exception e) {
			return "Alguno de los campos no tiene el formato valido.";
		}
    	
    	return "";
    }
    
}