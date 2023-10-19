package dao;

import conexion.ConexionBD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import model.Persona;

public class PersonaDao {
	
	private ConexionBD conn;
	
	/**
	 * Consulta a la base de datos que devuelve las personas que hay 
	 * y las guarda en una lista
	 * @return Devuelve una listas de personas
	 */
	public ObservableList<Persona> cargarPersona(){
		ObservableList<Persona> lstPersonas = FXCollections.observableArrayList();
		try {
			conn = new ConexionBD();
			String consulta = "select * from Persona";
			PreparedStatement pstmt = conn.getConexion().prepareStatement(consulta);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				String apellidos = rs.getString("apellidos");
				int edad = rs.getInt("edad");
				
				Persona p = new Persona(id, nombre, apellidos, edad);
				lstPersonas.add(p);
			}
			rs.close();
			conn.closeConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lstPersonas;
	}
	
	public boolean insertarPersona(Persona p) {
		try {
			conn = new ConexionBD();
			String consulta = "INSERT INTO Persona(nombre,apellidos,edad) VALUES (?, ?, ?)";
			System.out.println(consulta);
			PreparedStatement ps = conn.getConexion().prepareStatement(consulta);
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getApellidos());
			ps.setInt(3, p.getEdad());
	
			ps.executeUpdate();
			conn.closeConexion();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
}
