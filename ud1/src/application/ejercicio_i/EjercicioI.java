package application.ejercicio_i;

import java.util.Locale;
import java.util.ResourceBundle;

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
public class EjercicioI extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		//Locale.setDefault(new Locale("e"));
		Locale locale = Locale.getDefault();
	
		
		ResourceBundle bundle = ResourceBundle.getBundle("strings",locale);
		
		GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("/fxml/EjercicioI.fxml"),bundle);
		Scene scene = new Scene(root,650,400);
        scene.getStylesheets().add(getClass() .getResource("/css/estilo_ejercicioI.css").toExternalForm());
		stage.setTitle(bundle.getString("title"));
		stage.setScene(scene);
		stage.show();
	}
	
}
