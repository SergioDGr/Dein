module ud3 {
	requires javafx.controls;
	requires jasperreports;
	requires java.sql;
	requires java.desktop;
	
	opens application to javafx.graphics, javafx.fxml;
}
