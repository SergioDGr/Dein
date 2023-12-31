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
	
	/**
	 * Funcion que añade a la persona a la base de datos
	 * @param p Persona a guadar en la base de datos
	 * @return devuelve el id generado sino devolvera -1
	 */
	public int insertarPersona(Persona p) {
		try {
			conn = new ConexionBD();
			String consulta = "INSERT INTO Persona(nombre,apellidos,edad) VALUES (?, ?, ?)";
			System.out.println(consulta);
			PreparedStatement ps = conn.getConexion().prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getApellidos());
			ps.setInt(3, p.getEdad());
	
			int actualizado = ps.executeUpdate();
			if(actualizado == 0)
				throw new SQLException("No se ha insertado ninguna persona");
			
			ResultSet generatedKeys = ps.getGeneratedKeys();
			if (generatedKeys.next())
				return generatedKeys.getInt(1);
				
			conn.closeConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	/**
	 * Funcion que modifcar los campos de la persona en la base de datos en la tabla Persona
	 * @param p Persona a modificar
	 * @return devuelve <code>true</code> si se ha podido modificar o <code>false</code> si no se podido
	 */
	public boolean modificarPersona(Persona p) {
		try {
			conn = new ConexionBD();
			String consulta = "UPDATE Persona SET nombre = ?, apellidos = ?, edad = ? WHERE id = ?";
			
			PreparedStatement ps = conn.getConexion().prepareStatement(consulta);
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getApellidos());
			ps.setInt(3, p.getEdad());
			ps.setInt(4, p.getId());
			
			ps.executeUpdate();
			conn.closeConexion();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Funcion que elimina con el identificador a la persona de la tabla y de la base de datos
	 * @param id identificador de la persona
	 * @return devuelve <code>true</code> si se ha podido eliminarlo o <code>false</code> si no se podido
	 */
	public boolean eliminarPersona(int id) {
		try {
			System.out.println(id);
			conn = new ConexionBD();
			String consulta = "DELETE FROM Persona WHERE id = ?";
			
			PreparedStatement ps = conn.getConexion().prepareStatement(consulta);
			ps.setInt(1, id);
			
			ps.executeUpdate();
			conn.closeConexion();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
}
