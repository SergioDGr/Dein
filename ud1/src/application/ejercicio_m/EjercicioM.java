package application.ejercicio_m;

import controllers.AeropuertoController2;
import controllers.LoginAeropuertoController;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;

/**
 *	Aplicacion del ejercicio L añadiendo algunas funcionaliades mas
 */
public class EjercicioM extends Application{

	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		//Consige el ventana principal 
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EjercicioM.fxml"));
		//Se instancia el controlador de la ventana
		AeropuertoController2 controller = new AeropuertoController2();
		//Se visualiza una ventana modal que sirve para el login
		LoginAeropuertoController loginController = (LoginAeropuertoController) controller.cargar_ventana_modal(
				new LoginAeropuertoController(),"/fxml/EjercicioL_Login.fxml", "AVIONES - LOGIN", null,
				new Image(getClass().getResource("/img/avion.png").toString()));
		//Y si el login esta correcto lo visualiza
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
