package controllers;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import dao.AnimalDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import model.Animal;


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

    @FXML
    private TextField tfNombre;
    
    private ObservableList<Animal> lstAnimales;
    private AnimalDao animalDao;
    
    @FXML
    void click_addAnimal(ActionEvent event) {

    }

    @FXML
    void click_delAnimal(ActionEvent event) {

    }

    @FXML
    void click_modAnimal(ActionEvent event) {

    }

    @FXML
    void click_verAnimal(ActionEvent event) {

    }
    
    @FXML
    void click_addConsulta(ActionEvent event) {

    }
    
    @FXML
    void click_verConsultas(ActionEvent event) {

    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	animalDao = new AnimalDao();
    	lstAnimales = animalDao.get();
    	
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
