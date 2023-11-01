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

/**
 * Clase que gestiona las tablas que tengan que ver con el aeropuerto en la base de datos
 */
public class AeropuertoDao {
	
	private ConexionBDAeropuerto conn;
	
	/**
	 * Metodo que los aeropuerto privados que hay en la base de datos
	 * @return devuelve una lista de aeropuertos privados
	 */
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
	
	/**
	 * Metodo que los aeropuerto publicos que hay en la base de datos
	 * @return devuelve una lista de aeropuertos publicos
	 */
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
	
	/**
	 * Se insertar un aeropuerto publico en la base de datos
	 * @param aeropuerto
	 * @return devuelve <code>true</code> si se a podido insertar el aeropuerto o <code>false</code> si no se apodido
	 */
	public boolean insertarAeropuertoPublico(AeropuertoPublico aeropuerto) {
		try {
			conn = new ConexionBDAeropuerto();
			//Si se podido insertar el aeropuerto en la tabla aeropuertos insertara tambien en la tabla aeropuertos_publicos
			if(insertarAeropuerto(aeropuerto)) {
				String consulta = "INSERT INTO aeropuertos_publicos VALUES(?,?,?)";
				PreparedStatement ps = conn.getConexion().prepareStatement(consulta);
				ps.setInt(1, aeropuerto.getId());
				ps.setDouble(2, aeropuerto.getFinanciacion());
				ps.setInt(3, aeropuerto.getTrabajadores());
				
				int actualizado = ps.executeUpdate();
				if(actualizado == 0)
					return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Se insertar un aeropuerto privado en la base de datos
	 * @param aeropuerto
	 * @return devuelve <code>true</code> si se a podido insertar el aeropuerto o <code>false</code> si no se apodido
	 */
	public boolean insertarAeropuertoPrivado(AeropuertoPrivado aeropuerto) {
		try {
			conn = new ConexionBDAeropuerto();
			//Si se podido insertar el aeropuerto en la tabla aeropuertos insertara tambien en la tabla aeropuertos_privados
			if(insertarAeropuerto(aeropuerto)) {
				System.out.println(aeropuerto.getId());
				String consulta = "INSERT INTO aeropuertos_privados VALUES(?,?)";
				PreparedStatement ps = conn.getConexion().prepareStatement(consulta);
				ps.setInt(1, aeropuerto.getId());
				ps.setDouble(2, aeropuerto.getSocios());
				
				int actualizado = ps.executeUpdate();
				if(actualizado == 0)
					return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Se inserta un aeropuerto en base de datos
	 * @param aeropuerto
	 * @return devuelve <code>true</code> si se a podido insertar el aeropuerto o <code>false</code> si no se apodido
	 * @throws SQLException Lanza una exepcion si ocurre algun error en la base de datos
	 */
	private boolean insertarAeropuerto(Aeropuerto aeropuerto) throws SQLException {
		//Si se podido insertar la direccion se insertar el aeropuerto en la tabla aeropuertos
		if(insertarDireccion(aeropuerto.getDireccion())) {
			String consulta = "INSERT INTO Aeropuertos(nombre,anio_inauguracion,capacidad,id_direccion) VALUES(?,?,?,?)";
			PreparedStatement ps = conn.getConexion().prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, aeropuerto.getNombre());
			ps.setInt(2, aeropuerto.getAnio());
			ps.setInt(3, aeropuerto.getCapacidad());
			ps.setInt(4, aeropuerto.getDireccion().getId());
			
			int actualizado = ps.executeUpdate();
			if(actualizado == 0)
				throw new SQLException("No se ha insertado ninguna persona");
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				aeropuerto.setId(rs.getInt(1));
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Se insertar la direccion del aeropuerto en la base de datos
	 * @param direccion
	 * @return devuelve <code>true</code> si se a podido insertar la direccion o <code>false</code> si no se apodido
	 * @throws SQLException Lanza una exepcion si ocurre algun error en la base de datos
	 */
	private boolean insertarDireccion(Direccion direccion) throws SQLException {
		String consulta = "INSERT INTO direcciones(pais,ciudad,calle,numero) VALUES(?,?,?,?)";
		PreparedStatement ps = conn.getConexion().prepareStatement(consulta, PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, direccion.getPais());
		ps.setString(2, direccion.getCiudad());
		ps.setString(3, direccion.getCalle());
		ps.setInt(4, direccion.getNum());
		
		int actualizado = ps.executeUpdate();
		if(actualizado == 0)
			throw new SQLException("No se ha insertado ninguna persona");
		
		ResultSet rs = ps.getGeneratedKeys();
		if(rs.next()) {
			direccion.setId(rs.getInt(1));
			return true;
		}
		return false;
	}
	
	/**
	 * Metodo que del resultado crea un aeropuerto y lo devuelve 
	 * @param resultado un fila del resultado de la consulta, los datos de aeropuerto
	 * @param publico si el aeropuerto es publico
	 * @return Devuelve el aeropuerto
	 * @throws SQLException si ocurre algun error relacionado con la base de datos
	 */
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
