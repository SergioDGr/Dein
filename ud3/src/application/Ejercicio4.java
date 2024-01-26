package application;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;

public class Ejercicio4 extends Application {
	
	@Override
	public void start(Stage stage) {
		try {
			GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("/fxml/formulario_medico.fxml"));
			Scene scene = new Scene(root, 500, 600);
	        //scene.getStylesheets().add(getClass() .getResource("/css/application.css").toExternalForm());
			stage.setTitle("FORMULARIO MÉDICO");
			stage.setScene(scene);
			stage.setResizable(false);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
