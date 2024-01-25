package controllers;

import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;

import conexion.ConnBD;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class SupermercadoController {

    @FXML
    private Button btnGraficoInforme;

    @FXML
    private Button btnProductoInforme;

    @FXML
    private Button btnSeccionesInforme;

    @FXML
    private Button btnTablaProductoInforme;

    @FXML
    void click_grafico(ActionEvent event) {
    	
    }

    @FXML
    void click_producto(ActionEvent event) {
    	cargarInforme("/jrxml/productos.jasper");
    }

    @FXML
    void click_secciones(ActionEvent event) {

    }

    @FXML
    void click_tabla_producto(ActionEvent event) {

    }
    
    private void cargarInforme(String ruta_jasper) {
    	try {
    		ConnBD bd = new ConnBD("supermercado");
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

