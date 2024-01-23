package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class ConnBD {
	
	private Connection conn;
	
	/**
	 * En el contructor se hara la conexion a la base de datos
	 * y se guardar en la propiedad conn
	 * @throws SQLException si ocurre algun error se lanzara exception ralacionada a la base datos
	 */
	public ConnBD(String baseDatos) throws SQLException {
		String host = "localhost";
		String usuario = "admin";
		String password = "password";
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
