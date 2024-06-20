package com.example.bibliotecaSena.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity(name="usuario")
public class usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id_usuario", nullable = false, length = 36)
	private String id_usuario;
	
	@Column(name = "nombre", nullable = false, length = 50)
	private String nombre;

	
	@Column(name = "direccion", nullable = false, length = 50)
	private String direccion;

	
	@Column(name = "correo", nullable = false, length = 50)
	private String correo;

	@Column(name = "tipo_usuario", nullable = false, length = 50)
	private String tipo_usuario;

	public usuario() {
		super();
	}

	public usuario(String id_usuario, String nombre, String direccion, String correo, String tipo_usuario) {
		super();
		this.id_usuario = id_usuario;
		this.nombre = nombre;
		this.direccion = direccion;
		this.correo = correo;
		this.tipo_usuario = tipo_usuario;
	}

	public String getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(String tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}
	
	


}
