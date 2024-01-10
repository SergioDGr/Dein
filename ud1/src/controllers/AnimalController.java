package controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import dao.AnimalDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import model.Animal;
import model.Consulta;


public class AnimalController implements Initializable{
	
	@FXML
    private TableView<Animal> tableAnimales;
	
	@FXML
    private TableColumn<Animal, Integer> tcID;
	
	@FXML
    private TableColumn<Animal, String> tcNombre;
	
	@FXML
    private TableColumn<Animal, Character> tcSexo;
	
    @FXML
    private TableColumn<Animal, Integer> tcEdad;
    
    @FXML
    private TableColumn<Animal, Float> tcPeso;

    @FXML
    private TableColumn<Animal, Date> tcFecha;

    @FXML
    private TableColumn<Animal, String> tcRaza;

    @FXML
    private TableColumn<Animal, String> tcEspecie;
    
    private ObservableList<Animal> lstAnimales;
    private AnimalDao animalDao;
    
	public ObservableList<Animal> getAnimals() {
		return lstAnimales;
	}
    
    @FXML
    void click_addAnimal(ActionEvent event) {
    	try {
    		AniadirAnimalController controller = new AniadirAnimalController();
    		controller.setController(this);
			cargar_ventana_modal(controller, "Añadir Animal", "/fxml/EjercicioS_Modal_Animal.fxml", tableAnimales.getScene().getWindow());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    @FXML
    void click_delAnimal(ActionEvent event) {

    }

    @FXML
    void click_modAnimal(ActionEvent event) {
    	if(tableAnimales.getSelectionModel().getSelectedIndex() != -1) {
	    	try {
	    		EditarAnimalController controller = new EditarAnimalController();
	    		controller.setController(this);
	    		controller.setAnimal(tableAnimales.getSelectionModel().getSelectedItem());
	    		cargar_ventana_modal(controller, "Editar Animal", "/fxml/EjercicioS_Modal_Animal.fxml", tableAnimales.getScene().getWindow());
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}else {
    		crear_mostrar_alerta(AlertType.ERROR, "Error - Editar ANIMAL", "No se a seleccionado ningun animal", tableAnimales.getScene().getWindow());
    	}
    }

    @FXML
    void click_verAnimal(ActionEvent event) {
    	if(tableAnimales.getSelectionModel().getSelectedIndex() != -1) {
    		Animal a = tableAnimales.getSelectionModel().getSelectedItem();
    		if(a.getImagen() != null) {
		    	// Crear un objeto Stage y un objeto Scene
		        Stage stage = new Stage();
		        StackPane root = new StackPane();
		        Scene scene = new Scene(root, 300, 250);
		        
		        // Mostrar la imagen en el objeto Stage
		        root.getChildren().add(new ImageView(new Image(new ByteArrayInputStream(a.getImagen()))));
		        stage.initOwner(tableAnimales.getScene().getWindow());
		        stage.initModality(Modality.APPLICATION_MODAL);
		        stage.setScene(scene);
		        stage.showAndWait();
    		}else
    			crear_mostrar_alerta(AlertType.ERROR, "Error - Ver ANIMAL", "No se a encontrado la foto del animal", tableAnimales.getScene().getWindow());
        }else
    		crear_mostrar_alerta(AlertType.ERROR, "Error - Ver ANIMAL", "No se a seleccionado ningun animal", tableAnimales.getScene().getWindow());

    }
    
    @FXML
    void click_addConsulta(ActionEvent event) {
    	try {
    		AniadirConsultaController controller = new AniadirConsultaController();
    		controller.setController(this);
			cargar_ventana_modal(controller, "Añadir Consulta", "/fxml/EjercicioS_Modal_Consulta.fxml", tableAnimales.getScene().getWindow());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    @FXML
    void click_modConsulta(ActionEvent event) {
    	if(tableAnimales.getSelectionModel().getSelectedIndex() != -1) {
	    	try {
	    		EditarConsultaController controller = new EditarConsultaController();
	    		controller.setController(this);
	    		controller.setAnimal(tableAnimales.getSelectionModel().getSelectedItem());
	    		cargar_ventana_modal(controller, "Editar Consulta", "/fxml/EjercicioS_Modal_Consulta.fxml", tableAnimales.getScene().getWindow());
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}else {
    		crear_mostrar_alerta(AlertType.ERROR, "Error - Editar Consulta", "No se a seleccionado ningun animal", tableAnimales.getScene().getWindow());
    	}
    }
    
    @FXML
    void click_delConsulta(ActionEvent event) {
    	
    }
    
    @FXML
    void click_verConsultas(ActionEvent event) {
    	if(tableAnimales.getSelectionModel().getSelectedIndex() != -1) {
    		crear_mostrar_alerta(AlertType.INFORMATION, "Information - Ver Consultas",tableAnimales.getSelectionModel().getSelectedItem().infoConsultas(),
    				tableAnimales.getScene().getWindow());
    	}else {
    		crear_mostrar_alerta(AlertType.ERROR, "Error - Ver Consultas", "No se a seleccionado ningun animal", tableAnimales.getScene().getWindow());
    	}
    }
    
    public boolean aniadirAnimal(Animal animal) {
    	if(animalDao.add(animal)) {
    		lstAnimales.add(animal);
    		return true;
    	}
    	return false;
    }
    
    public boolean editarAnimal(Animal animal) {
    	if(animalDao.mod(animal)) {
    		tableAnimales.refresh();
    		return true;
    	}
    	
    	return false;
    }
    
    public void modificarFechaAnimal(Animal animal) {
    	animalDao.updateFecha(animal);
    	tableAnimales.refresh();
    }
    
    public boolean aniadirConsulta(Consulta consulta) {
    	if(animalDao.add(consulta)) 
    		return true;
    	return false;
    }
    
    public boolean editarConsulta(Consulta consulta) {
    	if(animalDao.mod(consulta)) {
    		tableAnimales.refresh();
    		return true;
    	}
    	
    	return false;
    }
    
    /**
     * carga y muestra la ventana modal con los siguientes paramatros:
     * @param controlador El controlador de la ventana
     * @param titulo El titulo de la ventana
     * @param fxml interfaz a visualizar
     * @param window A la ventana que pertenece la ventana modal
     * @throws IOException en caso de que de error cuando cargue el fxml
     */
    private void cargar_ventana_modal(Object controlador , String titulo, String fxml , Window window) throws IOException {
    	//Cargamos la intefaz que se visualizara
    	FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
    	//Cargamos el controllador
		loader.setController(controlador);
		Parent parent = loader.load();
		//Creamos y visualizamos la venta
    	Scene newScene = new Scene(parent);
		Stage newStage = new Stage();
		newStage.initModality(Modality.APPLICATION_MODAL);
		newStage.initOwner(window);
		newStage.setScene(newScene);
		newStage.setTitle(titulo);
		newStage.setResizable(false);
		newStage.showAndWait();
    }
    
    /**
     * Crea y muestra un mensaje de alerta
     * @param tipoAlert El tipo de alerta
     * @param titulo El titulo que tendra la alerta
     * @param msg El mensaje que mostrara la alerta
     * @param window ventana propietaria
     */
    private void crear_mostrar_alerta(AlertType tipoAlert,String titulo, String msg, Window window) {
    	Alert alert = new Alert(tipoAlert);
    	alert.setHeaderText(null);
    	alert.setTitle(titulo);
    	alert.setContentText(msg);
    	alert.initOwner(window);
    	alert.showAndWait();
    }
    
    private void cargar_Consultas() {
    	for(int i = 0; i < lstAnimales.size(); i++) {
    		Animal animal = lstAnimales.get(i);
    		animal.setConsultas(animalDao.get(animal));
    	}
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	animalDao = new AnimalDao();
    	lstAnimales = animalDao.get();
    	
    	cargar_Consultas();
    	
    	//Establecer las columnas
    	tcID.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("id"));
    	tcNombre.setCellValueFactory(new PropertyValueFactory<Animal, String>("nombre"));
    	tcSexo.setCellValueFactory(new PropertyValueFactory<Animal, Character>("sexo"));
    	tcEdad.setCellValueFactory(new PropertyValueFactory<Animal, Integer>("edad"));
    	tcPeso.setCellValueFactory(new PropertyValueFactory<Animal, Float>("peso"));
    	tcFecha.setCellValueFactory(new PropertyValueFactory<Animal, Date>("fecha_primera_consulta"));
    	tcRaza.setCellValueFactory(new PropertyValueFactory<Animal, String>("raza"));
    	tcEspecie.setCellValueFactory(new PropertyValueFactory<Animal, String>("especie"));
    	
    	tableAnimales.setItems(lstAnimales);
    }
	
}
