package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
	
	private Connection conn;
	
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

	public void closeConexion() throws SQLException {
		conn.close();
	}
	
}
