package application.ejercicio_l;

import controllers.AeropuertoController;
import controllers.LoginAeropuertoController;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;

/**
 *	Ventana que visualiza un reloj, y se ve como pasa el tiempo cada segundo
 */
public class EjercicioL extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EjercicioL.fxml"));
		AeropuertoController controller = new AeropuertoController();
		LoginAeropuertoController loginController = (LoginAeropuertoController) controller.cargar_ventana_modal(new LoginAeropuertoController(), "AVIONES - LOGIN");
		if(loginController.logeado) {
			//controller.setLoader(loader);
			loader.setController(controller);
			GridPane root = (GridPane) loader.load();
			Scene scene = new Scene(root, 789, 550);
			stage.setScene(scene);
			stage.setTitle("AVIONES - AEROPUERTO");
			stage.show();
		}
	}
	
}
