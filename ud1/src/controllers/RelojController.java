package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RelojController implements Initializable {
	
	@FXML
 	private ImageView imgHora1;

    @FXML
    private ImageView imgHora2;

    @FXML
    private ImageView imgMin1;

    @FXML
    private ImageView imgMin2;

    @FXML
    private ImageView imgSeg1;

    @FXML
    private ImageView imgSeg2;
    
    private TimerTask task;
    
    private String hora1, hora2, min1, min2;
    
    private String getImage(String num) {
    	String path = "file://" + getClass().getResource("/img").getFile();
    	String img = "";
    	switch (num) {
			case "1": 
				img = "ONE";
				break;
			case "2":
				img = "TWO";
				break;
			case "3":
				img = "THREE";
				break;
			case "4":
				img = "FOUR";
				break;
			case "5":
				img = "FIVE";
				break;
			case "6":
				img = "SIX";
				break;
			case "7":
				img = "SEVEN";
				break;
			case "8":
				img = "EIGHT";
				break;
			case "9":
				img = "NINE";
				break;
			case "0":
				img = "ZERO";
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + num);
		}
    	return path + img + ".png";
    }
    
    public void pararTask() {
    	task.cancel();
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	hora1 = "";
    	hora2 = "";
    	min1 = "";
    	min2 = "";
    	//Creamos la tarea
		Timer timer = new Timer();
		task = new TimerTask() {
			
		     @Override
		     public void run() {
		         // Obtener el tiempo actual
		         long currentTime = System.currentTimeMillis();
		         // Convertir el tiempo actual a un formato legible
		         String formattedTime = new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date(currentTime));
		         
		         //Mostrarlo el tiempo
		         //Horas
		         if (!hora1.equals(formattedTime.charAt(0) + "")){
		        	 imgHora1.setImage(new Image(getImage(formattedTime.charAt(0) + "")));
		        	 hora1 = formattedTime.charAt(0) + "";
		         }
		         
		         if (!hora2.equals(formattedTime.charAt(1) + "")){
		        	 imgHora2.setImage(new Image(getImage(formattedTime.charAt(1) + "")));
		        	 hora2 =  formattedTime.charAt(1) + "";
		         }
		         //Minutos
		         if (!min1.equals(formattedTime.charAt(3) + "")){
		        	 imgMin1.setImage(new Image(getImage(formattedTime.charAt(3) + "")));
		        	 min1 = formattedTime.charAt(3) + "";
		         }
		         
		         if (!min2.equals(formattedTime.charAt(4) + "")){
			         imgMin2.setImage(new Image(getImage(formattedTime.charAt(4) + "")));
		        	 min2 =  formattedTime.charAt(4) + "";
		         }
		         
		         //Segundos
		         imgSeg1.setImage(new Image(getImage(formattedTime.charAt(6) + "")));
		         imgSeg2.setImage(new Image(getImage(formattedTime.charAt(7) + "")));
		         
		         System.out.println(formattedTime);
		     }
		     
		 };
	 
	 //Programar la tarea para que se ejecute cada segundo
	 timer.scheduleAtFixedRate(task, 0, 1000);
    }
    
 }
