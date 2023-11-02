package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBDAeropuerto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import model.Avion;

/**
 * Gestiona la tabla aviones que tengan que ver con avion en la base datos
 */
public class AvionDao {
	
	private ConexionBDAeropuerto conn;
	
	/**
	 * Metodo que consigue los aviones del aeropuerto pasado con su id
	 * @param id_aeropuerto 
	 * @return devuelve la lista de aviones del aeropuerto
	 */
	public ObservableList<Avion> getAviones(int id_aeropuerto){
		ObservableList<Avion> lstAviones = FXCollections.observableArrayList();
		try {
			conn = new ConexionBDAeropuerto();
			String consulta = "SELECT * FROM aviones WHERE id_aeropuerto = ?";
			PreparedStatement ps = conn.getConexion().prepareStatement(consulta);
			ps.setInt(1, id_aeropuerto);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String modelo = rs.getString("modelo");
				int numAsientos = rs.getInt("numero_asientos");
				double velocidadMaxima = rs.getDouble("velocidad_maxima");
				boolean activo = rs.getBoolean("activado");
				Avion avion = new Avion(id, modelo, numAsientos, velocidadMaxima, activo);
				lstAviones.add(avion);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lstAviones;
	}
	
	public boolean insetarAvion(int id_aeropuerto, Avion avion) {
		try {
			conn = new ConexionBDAeropuerto();
			String consulta = "INSERT INTO aviones(modelo,numero_asientos,velocidad_maxima,activado,id_aeropuerto) VALUES(?,?,?,?,?)";
			PreparedStatement ps = conn.getConexion().prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, avion.getModelo());
			ps.setInt(2, avion.getNumero_asientos());
			ps.setDouble(3, avion.getVelocidad_maxima());
			ps.setBoolean(4, avion.isActivo());
			ps.setInt(5, id_aeropuerto);
			
			int actualizado = ps.executeUpdate();
			if(actualizado == 0)
				return false;
			
			ResultSet rs = ps.getGeneratedKeys();
			
			rs.next();
			
			avion.setId(rs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean eliminarAviones(int id_aeropuerto) {
		try {
			conn = new ConexionBDAeropuerto();
			String consulta = "DELETE FROM aviones WHERE id_aeropuerto = ?";
			PreparedStatement ps = conn.getConexion().prepareStatement(consulta);
			ps.setInt(1, id_aeropuerto);
			
			int actualizado = ps.executeUpdate();
			if (actualizado == 0)
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
