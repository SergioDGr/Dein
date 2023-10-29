package model;

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
}
