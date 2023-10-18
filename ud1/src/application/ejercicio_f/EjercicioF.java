package application.ejercicio_f;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Ventana que visualiza una tabla con 3 columnas nombre, apellidos y
 * edad; un boton para agregar a la persona, editar a la persona,
 * eliminar a la persona, un boton para exportar y otro para importar
 */
public class EjercicioF extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("/fxml/EjercicioF.fxml"));
		Scene scene = new Scene(root,650,400);
        //scene.getStylesheets().add(getClass() .getResource("/css/application.css").toExternalForm());
		stage.setTitle("PERSONAS");
		stage.setScene(scene);
		stage.show();
	}
	
}
