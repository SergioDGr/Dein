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
 *	Ventana que visualiza un coche, un boton para encender o apagar las luces del coche,
 *  y varios botones para cambiar el color del coche
 */
public class EjercicioK extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EjercicioK.fxml"));
		GridPane root = loader.load();
		Scene scene = new Scene(root, 467, 400);
		//scene.getStylesheets().add(getClass().getResource("/css/estilo_ejercicioK.css").toExternalForm());
		stage.setTitle("RELOJ");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/Icono.ico")));
		stage.show();
		
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent arg0) {
				RelojController controller = loader.getController();
				controller.pararTask();
			}
		});
		
	}
	
}
