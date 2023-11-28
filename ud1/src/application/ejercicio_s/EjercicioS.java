package application.ejercicio_s;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;

/**
 *	Ventana que visualiza contenido para gestionar una veterinario
 */
public class EjercicioS extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		//Consige el ventana principal 
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EjercicioS.fxml"));
		//Se instancia el controlador de la ventana
		GridPane root = (GridPane) loader.load();
		Scene scene = new Scene(root, 800, 500);
		stage.setScene(scene);
		stage.setTitle("Animales");
		stage.show();
	}
}
