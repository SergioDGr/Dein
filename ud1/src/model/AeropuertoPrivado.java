package model;

/**
 * Clase que repersenta aeropuerto privado
 */
public class AeropuertoPrivado extends Aeropuerto{
	
	private int socios;
	
	public AeropuertoPrivado(int id, String nombre, int anio, Direccion direccion, int capacidad) {
		super(id, nombre, anio, direccion, capacidad);
	}
	
	public int getSocios() {
		return socios;
	}
	
	public void setSocios(int socios) {
		this.socios = socios;
	}
	
}
