package model;

import java.util.Objects;
import javafx.collections.ObservableList;

/**
 * Super Clase que repersenta aeropuerto
 */
public class Aeropuerto {
	
	private int id;
	
	private String nombre;
	
	private int anio;
	
	private Direccion direccion;
	
	private int capacidad;
	
	public ObservableList<Avion> aviones;
	
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
	
	@Override
	public String toString() {
		//Guarda toda la informacion
		String aeropuerto = "Nombre:" + nombre +"\nPais:" +  direccion.getPais() + "\nDirección:" + direccion.getCalle() 
			+ "\nAño de inaguracion:" + anio + "\nCapacidad:" + capacidad;
		//Mira si hay aviones
		if(aviones != null) {
			aeropuerto += "\nAviones:\n  ";
			//los recorre los guarda
			for(Avion avion : aviones) {
				aeropuerto +=  avion.toString().replace("\n", "\n  ") + "\n  ";
			}
			aeropuerto = aeropuerto.substring(0, aeropuerto.length() - 3);
		}
		return aeropuerto;
	}
	
}
