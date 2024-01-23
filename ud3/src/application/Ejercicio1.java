package application;
	
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.HashMap;
import java.util.Map;

import conexion.ConnBD;

import javafx.application.Application;

import javafx.stage.Stage;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import net.sf.jasperreports.view.JasperViewer;

public class Ejercicio1 extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			ConnBD bd = new ConnBD("paises");
			
			Map<String, Object> parameters = new HashMap<String, Object>();
			
			JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/jrxml/naciones.jasper"));
	        JasperPrint jprint = JasperFillManager.fillReport(report, parameters, bd.getConexion());
	        JasperViewer viewer = new JasperViewer(jprint, false);
	        viewer.setVisible(true);
	        viewer.addWindowListener(new WindowAdapter() {

				@Override
				public void windowClosing(WindowEvent e) {System. exit(0);}
			
	        });
	    	
	        bd.closeConexion();
	        
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);

	}
}
