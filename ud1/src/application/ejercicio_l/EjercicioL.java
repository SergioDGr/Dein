package application.ejercicio_l;

import controllers.RelojController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *	Ventana que visualiza un reloj, y se ve como pasa el tiempo cada segundo
 */
public class EjercicioL extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("/fxml/EjercicioJ.fxml"));
		Scene scene = new Scene(root, 575, 550);
		//scene.getStylesheets().add(getClass().getResource("/css/estilo_ejercicioJ.css").toExternalForm());
		stage.setTitle("AVIONES - AEROPUERTOS");
		stage.setScene(scene);
		stage.getIcons().add(new Image(getClass().getResource("/img/Cooper.png").toString()));
		stage.show();
	}
	
}
