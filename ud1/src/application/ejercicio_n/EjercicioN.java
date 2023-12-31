package application.ejercicio_n;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;

/**
 * Ventana que visualiza una iterfaz que importa otro interfaz dentro de ella
 */
public class EjercicioN extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		//Consige el ventana principal 
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EjercicioN.fxml"));
		//Se instancia el controlador de la ventana
		GridPane root = (GridPane) loader.load();
		Scene scene = new Scene(root, 500, 300);
		stage.setScene(scene);
		stage.setTitle("Formulario");
		stage.show();
		
	}
	
}
