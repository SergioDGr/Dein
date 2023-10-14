package application.ejercicio_c;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Ventana que muestra una tabla con 3 columnas Nombre, Apellidos y Edad y
 *  3 TextField para rellenar los datos de la persona los mismos que la tabla;
 *  y 3 botones, uno para añadir a la persona, otro para modificar y otro para
 *  eliminar a la persona.
 */
public class EjercicioC extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("/fxml/EjercicioC.fxml"));
		Scene scene = new Scene(root,650,400);
        //scene.getStylesheets().add(getClass() .getResource("/css/application.css").toExternalForm());
		stage.setTitle("PERSONAS");
		stage.setScene(scene);
		stage.show();
	}
	
}
