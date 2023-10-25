package application.ejercicio_j;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *	Ventana que visualiza un coche, un boton para encender o apagar las luces del coche,
 *  y varios botones para cambiar el color del coche
 */
public class EjercicioJ extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("/fxml/EjercicioJ.fxml"));
		Scene scene = new Scene(root, 575, 550);
		scene.getStylesheets().add(getClass().getResource("/css/estilo_ejercicioJ.css").toExternalForm());
		stage.setTitle("MINI COOPER");
		stage.setScene(scene);
		stage.getIcons().add(new Image(getClass().getResource("/img/Cooper.png").toString()));
		stage.show();
	}
	
}
