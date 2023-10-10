/**
 * 
 */
/**
 * 
 */
module ud1 {
	requires javafx.controls;
	requires java.desktop;
	requires javafx.web;
	requires javafx.fxml;
	requires javafx.swing;
	requires javafx.media;
	requires javafx.graphics;
	opens application.ejercicio_a to javafx.graphics, javafx.fxml;
	opens application.ejercicio_b to javafx.graphics, javafx.fxml;
	opens controllers to javafx.graphics, javafx.fxml;
}