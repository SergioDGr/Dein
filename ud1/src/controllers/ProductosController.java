package controllers;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

import com.dein.productolabelwidget.ProductoLabel;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;

public class ProductosController implements Initializable {
	
	@FXML
    private ProductoLabel panLabel;

    @FXML
    private ProductoLabel lecheLabel;

    @FXML
    private ProductoLabel manzanaLabel;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		panLabel.setName("Pan");
		manzanaLabel.setName("Manzana");
		lecheLabel.setName("Leche");
		
		panLabel.setStock(10);
		manzanaLabel.setStock(200);
		lecheLabel.setStock(50);
		
		try {
			panLabel.setImage(new Image(getClass().getResource("/img/pan.png").toURI().toString()));
			manzanaLabel.setImage(new Image(getClass().getResource("/img/manzana.png").toURI().toString()));
			lecheLabel.setImage(new Image(getClass().getResource("/img/leche.png").toURI().toString()));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}		
	}
}
