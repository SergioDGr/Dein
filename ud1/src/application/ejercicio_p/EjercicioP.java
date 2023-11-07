package application.ejercicio_p;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;

/**
 *	
 */
public class EjercicioP extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		//Consige el ventana principal 
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EjercicioP.fxml"));
		//Se instancia el controlador de la ventana
		GridPane root = (GridPane) loader.load();
		Scene scene = new Scene(root, 450, 400);
		stage.setScene(scene);
		stage.setTitle("TEST");
		stage.setResizable(false);
		stage.show();
	}
}
