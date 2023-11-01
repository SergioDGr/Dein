package model;

import java.util.Objects;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Clase que representa la Direccion de un sitio.
 */
public class Direccion {
	
	private int id;
	
	private String pais;
	private SimpleStringProperty paisProperty;
	
	private String ciudad;
	private SimpleStringProperty ciudadProperty;
	
	private String calle;
	private SimpleStringProperty calleProperty;
	
	private int num;
	private SimpleIntegerProperty numProperty;

	public Direccion(int id, String pais, String ciudad, String calle, int num) {
		this.id = id;
		this.pais = pais;
		this.paisProperty = new SimpleStringProperty(pais);
		this.ciudad = ciudad;
		this.ciudadProperty = new SimpleStringProperty(ciudad);
		this.calle = calle;
		this.calleProperty = new SimpleStringProperty(calle);
		this.num = num;
		this.numProperty = new SimpleIntegerProperty(num);
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
		paisProperty.set(pais);
	}
	
	public SimpleStringProperty getPaisProperty() {
		return paisProperty;
	}
	
	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
		this.ciudadProperty.set(ciudad);
	}
	
	public SimpleStringProperty getCiudadProperty() {
		return ciudadProperty;
	}
	
	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
		this.calleProperty.set(calle);
	}
	
	public SimpleStringProperty getCalleProperty() {
		return calleProperty;
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
		this.numProperty.set(num);
	}
	
	public SimpleIntegerProperty getNumProperty() {
		return numProperty;
	}

	@Override
	public int hashCode() {
		return Objects.hash(calle, ciudad, num, pais);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Direccion other = (Direccion) obj;
		return Objects.equals(calle, other.calle) && Objects.equals(ciudad, other.ciudad) && num == other.num
				&& Objects.equals(pais, other.pais);
	}
}
