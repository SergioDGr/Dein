package model;

public class Direccion {
	
	private int id;
	
	private String pais;
	
	private String ciudad;
	
	private String calle;
	
	private int num;

	public Direccion(int id, String pais, String ciudad, String calle, int num) {
		this.id = id;
		this.pais = pais;
		this.ciudad = ciudad;
		this.calle = calle;
		this.num = num;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
