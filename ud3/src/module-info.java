module ud3 {
	requires javafx.controls;
	requires jasperreports;
	requires java.sql;
	
	opens application to javafx.graphics, javafx.fxml;
}
