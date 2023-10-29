package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBDAeropuerto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import model.Aeropuerto;
import model.AeropuertoPrivado;
import model.AeropuertoPublico;
import model.Direccion;

public class AeropuertoDao {
	
	private ConexionBDAeropuerto conn;
	
	public ObservableList<Aeropuerto> getAeropuertoPrivados(){
		ObservableList<Aeropuerto> lstAeropuerto = FXCollections.observableArrayList();
		try {
			conn = new ConexionBDAeropuerto();
			String consulta = "select * from aeropuertos a, direcciones d, aeropuertos_privados pri where id_direccion = d.id and a.id = pri.id_aeropuerto;";
			PreparedStatement ps = conn.getConexion().prepareStatement(consulta);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				AeropuertoPrivado aeropuerto = (AeropuertoPrivado) conseguirAeropuerto(rs, false);
				aeropuerto.setSocios(rs.getInt("numero_socios"));
				lstAeropuerto.add(aeropuerto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstAeropuerto;
	}
	
	public ObservableList<Aeropuerto> getAeropuertoPublicos(){
		ObservableList<Aeropuerto> lstAeropuerto = FXCollections.observableArrayList();
		try {
			conn = new ConexionBDAeropuerto();
			String consulta = "select * from aeropuertos a, aeropuertos.direcciones d, aeropuertos_publicos pu where id_direccion = d.id and a.id = pu.id_aeropuerto;";
			PreparedStatement ps = conn.getConexion().prepareStatement(consulta);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				AeropuertoPublico aeropuerto = (AeropuertoPublico) conseguirAeropuerto(rs, true);
				aeropuerto.setTrabajadores(rs.getInt("num_trabajadores"));
				aeropuerto.setFinanciacion(rs.getDouble("financiacion"));
				lstAeropuerto.add(aeropuerto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstAeropuerto;
	}
	
	private Aeropuerto conseguirAeropuerto(ResultSet resultado, boolean publico) throws SQLException {
		int id_aeropuerto = resultado.getInt("id");
		String nombre = resultado.getString("nombre");
		int anio = resultado.getInt("anio_inauguracion");
		int capacidad = resultado.getInt("capacidad");
		int id_direccion = resultado.getInt("id_direccion");
		String pais = resultado.getString("pais");
		String ciudad = resultado.getString("ciudad");
		String calle = resultado.getString("calle");
		int numero = resultado.getInt("numero");
		Direccion direccion = new Direccion(id_direccion, pais, ciudad, calle, numero);
		Aeropuerto aeropuerto;
		if (publico) 
			aeropuerto = new AeropuertoPublico(id_aeropuerto, nombre, anio, direccion, capacidad);
		else
			aeropuerto = new AeropuertoPrivado(id_aeropuerto, nombre, anio, direccion, capacidad);
		return aeropuerto;
	}
	
}
