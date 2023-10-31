package model;

/**
 * Super Clase que repersenta aeropuerto
 */
public class Aeropuerto {
	
	private int id;
	
	private String nombre;
	
	private int anio;
	
	private Direccion direccion;
	
	private int capacidad;

	public Aeropuerto(int id, String nombre, int anio, Direccion direccion, int capacidad) {
		this.id = id;
		this.nombre = nombre;
		this.anio = anio;
		this.direccion = direccion;
		this.capacidad = capacidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	@Override
	public String toString() {
		String aeropuerto = "Nombre:" + nombre +"\nPais:" +  direccion.getPais() + "\nDirección:" + direccion.getCalle() 
			+ "\nAño de inaguracion:" + anio + "\nCapacidad:" + capacidad;
		return aeropuerto;
	}
	
}
