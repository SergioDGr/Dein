package dao;

import java.io.IOException;
import java.io.InputStream;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;

import conexion.ConexionBDAnimal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import model.Animal;

public class AnimalDao {

	private ConexionBDAnimal conn;
	
	public ObservableList<Animal> get(){
		ObservableList<Animal> lstAnimales = FXCollections.observableArrayList();
		try {
			conn = new ConexionBDAnimal();
			String consulta = "select * from Animal";
			PreparedStatement ps = conn.getConexion().prepareStatement(consulta);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("Id");
				String nombre = rs.getString("Nombre");
				char sexo = rs.getString("Sexo").charAt(0);
				int edad = rs.getInt("Edad");
				float peso = rs.getFloat("Peso");
				Date fecha = new Date(rs.getDate("Fecha_Primera_Consulta").getTime());
				
				byte[]imageByte = null;
				try (InputStream image =  rs.getBinaryStream("Foto");){
					if(image != null)
						imageByte = image.readAllBytes();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				String raza = rs.getString("Raza");
				String especie = rs.getString("Especie");
				
				Animal animal = new Animal(id, nombre, sexo, edad, peso, fecha, imageByte, raza, especie);
				
				lstAnimales.add(animal);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstAnimales;
	}
	
	
	public boolean add(Animal animal) {
		
		return false;
	}
	
	public boolean remove(Animal animal) {
		
		return false;
	}
	
	public boolean mod(Animal animal) {
		
		return false;
	}
	
}
