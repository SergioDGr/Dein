package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Ejercicio2 extends Application {
	
	@Override
	public void start(Stage stage) {
		try {
			GridPane root = (GridPane) FXMLLoader.load(getClass().getResource("/fxml/informes.fxml"));
			Scene scene = new Scene(root, 500, 400);
	        //scene.getStylesheets().add(getClass() .getResource("/css/application.css").toExternalForm());
			stage.setTitle("INFORMES");
			stage.setScene(scene);
			stage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);

	}
}
