package application.ejercicio_h;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *	Ventana que visualiza una tabla de persona con 3 botones: añadir, modificar y eliminar. 
 *	Todos acciones se reflejaran un una base de datos, las accion de cargar datos, guadar persona,
 *	eliminar persona y modificar persona.
 */
public class EjercicioH extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("/fxml/EjercicioH.fxml"));
		Scene scene = new Scene(root,650,400);
        //scene.getStylesheets().add(getClass() .getResource("/css/application.css").toExternalForm());
		stage.setTitle("PERSONAS");
		stage.setScene(scene);
		stage.show();
	}
	
}
