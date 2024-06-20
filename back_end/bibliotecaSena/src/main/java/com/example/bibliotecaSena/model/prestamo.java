package com.example.bibliotecaSena.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="prestamo")
public class prestamo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id_prestamo", nullable = false, length = 36)
	private String id_prestamo;
	
	@Column(name = "fecha_prestamo", nullable = false, length = 50)
	private LocalDate fecha_prestamo;

	
	@Column(name = "fecha_devolucion", nullable = true, length = 50)
	private LocalDate fecha_devolucion;

	
	@Column(name = "usuario", nullable = false, length = 50)
	private String usuario;

	@Column(name = "libro", nullable = false, length = 50)
	private String libro;

	@Column(name = "estado", nullable = false, length = 50)
	private String estado;

	public prestamo() {
		super();
	}

	public prestamo(String id_prestamo, LocalDate fecha_prestamo, LocalDate fecha_devolucion, String usuario,
			String libro, String estado) {
		super();
		this.id_prestamo = id_prestamo;
		this.fecha_prestamo = fecha_prestamo;
		this.fecha_devolucion = fecha_devolucion;
		this.usuario = usuario;
		this.libro = libro;
		this.estado = estado;
	}

	public String getId_prestamo() {
		return id_prestamo;
	}

	public void setId_prestamo(String id_prestamo) {
		this.id_prestamo = id_prestamo;
	}

	public LocalDate getFecha_prestamo() {
		return fecha_prestamo;
	}

	public void setFecha_prestamo(LocalDate fecha_prestamo) {
		this.fecha_prestamo = fecha_prestamo;
	}

	public LocalDate getFecha_devolucion() {
		return fecha_devolucion;
	}

	public void setFecha_devolucion(LocalDate fecha_devolucion) {
		this.fecha_devolucion = fecha_devolucion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getLibro() {
		return libro;
	}

	public void setLibro(String libro) {
		this.libro = libro;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	


}
