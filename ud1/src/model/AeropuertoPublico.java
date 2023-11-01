package model;

import java.util.Objects;

/**
 * Clase que repersenta aeropuerto publico
 */
public class AeropuertoPublico extends Aeropuerto {
	
	private double financiacion;
	
	private int trabajadores;

	public AeropuertoPublico(int id, String nombre, int anio, Direccion direccion, int capacidad) {
		super(id, nombre, anio, direccion, capacidad);
	}
	
	public double getFinanciacion() {
		return financiacion;
	}
	
	public void setFinanciacion(double financiacion) {
		this.financiacion = financiacion;
	}
	
	public int getTrabajadores() {
		return trabajadores;
	}
	
	public void setTrabajadores(int trabajadores) {
		this.trabajadores = trabajadores;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(financiacion, trabajadores);
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
		AeropuertoPublico other = (AeropuertoPublico) obj;
		return Double.doubleToLongBits(financiacion) == Double.doubleToLongBits(other.financiacion)
				&& trabajadores == other.trabajadores;
	}

	
}
