package controllers;

import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;

import conexion.ConnBD;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class InformesController {
	
	@FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private RadioButton rbInfioPerCal;

    @FXML
    private RadioButton rbInfoPer;

    @FXML
    private RadioButton rbInfoPerSub;

    @FXML
    private ToggleGroup tgInformes;

    @FXML
    void click_aceptar(ActionEvent event) {
    	if(rbInfoPer.isSelected()) {
    		cargarInforme("/jrxml/informe_1.jasper");
    	}
    		
    }

    @FXML
    void click_cancelar(ActionEvent event) {
    	Stage stage = (Stage)btnCancelar.getScene().getWindow();
    	stage.close();
    }
    
    private void cargarInforme(String ruta_jasper) {
    	try {
    		ConnBD bd = new ConnBD("agenda");
    		Map<String, Object> parameters = new HashMap<String, Object>();
    		
    		InputStream jasper = getClass().getResourceAsStream(ruta_jasper);
			JasperReport report = (JasperReport) JRLoader.loadObject(jasper);
	        JasperPrint jprint = JasperFillManager.fillReport(report, parameters, bd.getConexion());
	        JasperViewer viewer = new JasperViewer(jprint, false);
	        viewer.setVisible(true);
	        bd.closeConexion();
		} catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("ERROR");
            alert.setContentText("Ha ocurrido un error");
            alert.showAndWait();
            e.printStackTrace();
        }
    }
    
}