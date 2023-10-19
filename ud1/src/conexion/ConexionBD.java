package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
	
	private Connection conn;
	
	/**
	 * En el contructor se hara la conexion a la base de datos
	 * y se guardar en la propiedad conn
	 * @throws SQLException si ocurre algun error se lanzara exception ralacionada a la base datos
	 */
	public ConexionBD() throws SQLException {
		String host = "localhost";
		String baseDatos = "personas";
		String usuario = "admin";
		String password = "password";
		String cadenaConexion = "jdbc:mysql://" + host + "/" + baseDatos + "?serverTimezone=";
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
