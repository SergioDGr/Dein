module ud3 {
	requires javafx.controls;
	requires javafx.web;
	requires javafx.fxml;
	requires javafx.swing;
	requires javafx.media;
	requires javafx.graphics;
	requires jasperreports;
	requires java.sql;
	requires java.desktop;
	requires javafx.base;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controllers to javafx.graphics, javafx.fxml;
}
