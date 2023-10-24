package application.ejercicio_k;

import controllers.RelojController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *	Ventana que visualiza un reloj, y se ve como pasa el tiempo cada segundo
 */
public class EjercicioK extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		//Conseguir la interfaz
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EjercicioK.fxml"));
		GridPane root = loader.load();
		Scene scene = new Scene(root, 467, 400);
		//scene.getStylesheets().add(getClass().getResource("/css/estilo_ejercicioK.css").toExternalForm());
		//Mostrar la venta
		stage.setTitle("RELOJ");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/Icono.ico")));
		stage.show();
		
		//Cuando se cierra la ventana se detiene la tarea que existe en el controlador
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent arg0) {
				RelojController controller = loader.getController();
				controller.pararTask();
			}
		});
		
	}
	
}
