package application;
	
import javafx.application.Application;

import javafx.stage.Stage;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import net.sf.jasperreports.view.JasperViewer;

public class Ejercicio1 extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			JasperReport report = (JasperReport) JRLoader.loadObject(getClass().getResource("/jrxml/naciones.jasper"));
	        JasperPrint jprint = JasperFillManager.fillReport(report, null, new JREmptyDataSource());
	        JasperViewer viewer = new JasperViewer(jprint, false);
	        viewer.setVisible(true);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
