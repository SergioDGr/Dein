package application.ejercicio_a;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * 
 *  
 * 
 */
public class EjercicioA extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		VBox root = (VBox) FXMLLoader.load(getClass().getResource("/fxml/EjercicioA.fxml"));
		Scene scene = new Scene(root,600,550);
        //scene.getStylesheets().add(getClass() .getResource("/css/application.css").toExternalForm());
		stage.setTitle("ENCUESTA");
		stage.setScene(scene);
		stage.show();
	}
	
}
