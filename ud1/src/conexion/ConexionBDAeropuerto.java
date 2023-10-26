package conexion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.TimeZone;

public class ConexionBDAeropuerto {
	
	private Connection conn;
	
	/**
	 * En el contructor se hara la conexion a la base de datos de aeropuertos
	 * y se guardar en la propiedad conn
	 * @throws SQLException si ocurre algun error se lanzara exception ralacionada a la base datos
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public ConexionBDAeropuerto() throws SQLException, FileNotFoundException, IOException {
		Properties propiedades = new Properties();
		propiedades.load(new FileInputStream(getClass().getResource("/configuration.properties")
				.toString()));
		
		String host = propiedades.getProperty("connBD.host");
		String baseDatos =  propiedades.getProperty("connBD.database");
		String usuario = propiedades.getProperty("connBD.user");
		String password = propiedades.getProperty("connBD.pass");
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
