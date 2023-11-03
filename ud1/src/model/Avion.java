package model;

public class Avion {
	
	private int id;
	
	private String modelo;
	
	private int numero_asientos;
	
	private double velocidad_maxima;
	
	private boolean activo;

	public Avion(int id, String modelo, int numero_asientos, double velocidad_maxima, boolean activo) {
		super();
		this.id = id;
		this.modelo = modelo;
		this.numero_asientos = numero_asientos;
		this.velocidad_maxima = velocidad_maxima;
		this.activo = activo;
	}
	
	@Override
	public String toString() {
		return "Modelo:" + modelo + "\nNúmero de asientos:" + numero_asientos + "\nVelocidad máxima:" + velocidad_maxima +
				"\n" + (activo ? "Activado": "Desactivado");
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getNumero_asientos() {
		return numero_asientos;
	}

	public void setNumero_asientos(int numero_asientos) {
		this.numero_asientos = numero_asientos;
	}

	public double getVelocidad_maxima() {
		return velocidad_maxima;
	}

	public void setVelocidad_maxima(double velocidad_maxima) {
		this.velocidad_maxima = velocidad_maxima;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
}
