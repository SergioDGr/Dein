package model;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(socios);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AeropuertoPrivado other = (AeropuertoPrivado) obj;
		return socios == other.socios;
	}	
	
	@Override
		public String toString() {
			return super.toString() + "\nPrivado\nNúmero de socios:" + socios;
		}
	
}
