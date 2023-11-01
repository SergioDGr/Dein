package model;

import java.util.Objects;

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
	public int hashCode() {
		return Objects.hash(anio, capacidad, direccion, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass()) 
			return false;
		Aeropuerto other = (Aeropuerto) obj;
		return anio == other.anio && capacidad == other.capacidad && Objects.equals(direccion, other.direccion)
				&& Objects.equals(nombre, other.nombre);
	}
	
}
