package model;

import java.util.Date;

import javafx.collections.ObservableList;

public class Animal {
	
	private int id;
	
	private String nombre;
	
	private char sexo;
	
	private int edad;
	
	private float peso;
	
	private Date fecha_primera_consulta;
	
	private byte[] imagen;
	
	private String raza;
	
	private String  especie;
	
	private ObservableList<Consulta> consultas;
	
	public Animal(int id, String nombre, char sexo, int edad, float peso, Date fecha_primera_consulta, byte[] imagen,
			String raza, String especie, ObservableList<Consulta> consultas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.sexo = sexo;
		this.edad = edad;
		this.peso = peso;
		this.fecha_primera_consulta = fecha_primera_consulta;
		this.imagen = imagen;
		this.raza = raza;
		this.especie = especie;
		this.consultas = consultas;
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

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public Date getFecha_primera_consulta() {
		return fecha_primera_consulta;
	}

	public void setFecha_primera_consulta(Date fecha_primera_consulta) {
		this.fecha_primera_consulta = fecha_primera_consulta;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}
	
	
	
}
