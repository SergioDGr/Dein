package aplication.ejercicio_b;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 *  
 * 
 */
public class EjercicioB extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader.load(getClass().getResource("/fxml/EjercicioB.fxml"));
		//Scene scene = new Scene(root,600,550);
        //scene.getStylesheets().add(getClass() .getResource("/css/application.css").toExternalForm());
		stage.setTitle("");
		//stage.setScene(scene);
		stage.show();
	}
	
}
