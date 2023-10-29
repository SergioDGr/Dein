package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.ConexionBDAeropuerto;

public class UsuarioDao {
	
	private ConexionBDAeropuerto conn;
	
	/**
	 * Hace una consulta para validar el nombre de usuario y la contraseña pasadas son las mismas
	 * @param usuario
	 * @param password
	 * @return devuelve <code>true</code> si son las mismas en caso contrario devuelve <code>false</code>
	 */
	public boolean validarUsuario(String usuario, String password) {	
		try {
			conn = new ConexionBDAeropuerto();
			String consulta = "SELECT * FROM usuarios WHERE usuario = ? AND password = ?";
			PreparedStatement ps = conn.getConexion().prepareStatement(consulta);
			ps.setString(1, usuario);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
