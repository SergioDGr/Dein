package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.ResourceBundle;
import java.util.TimeZone;

public class ConexionBDAnimal {
	
	private Connection conn;
	
	/**
	 * En el contructor se hara la conexion a la base de datos de animal
	 * y se guardar en la propiedad conn
	 * @throws SQLException si ocurre algun error se lanzara exception ralacionada a la base datos
	 */
	public ConexionBDAnimal() throws SQLException {
		ResourceBundle bundle = ResourceBundle.getBundle("configuration");
		
		String host = bundle.getString("connBD.host");
		String baseDatos =  bundle.getString("connBD.database2");
		String usuario = bundle.getString("connBD.user");
		String password = bundle.getString("connBD.pass");
		String cadenaConexion = "jdbc:mysql://" + host + "/" + baseDatos + "?serverTimezone=" + TimeZone.getDefault().getID();
		conn = DriverManager.getConnection(cadenaConexion, usuario, password);
		conn.setAutoCommit(true);
		
	}
	
	public Connection getConexion() {
		return conn;
	}

	/**
	 * Cerrara la conexion
	 * @throws SQLException si ocurre algun error se lanzara exception ralacionada a la base datos
	 */
	public void closeConexion() throws SQLException {
		conn.close();
	}
	
}
