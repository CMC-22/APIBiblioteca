 package com.example.bibliotecaSena.models;

import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;



@Entity(name="prestamo")
public class prestamo {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id_prestamo", nullable = false, length = 36)
	private String id_prestamo;

	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private usuario usuario;

	@ManyToOne
	@JoinColumn(name = "id_libro", nullable = false)
	private libro libro;


	@Column(name = "fecha_prestamo", nullable = false)
	private LocalDate fecha_prestamo;

	@Column(name = "fecha_devolucion", nullable = false)
	private LocalDate fecha_devolucion;

	@Column(name = "estado", nullable = false, length = 50)
	private String estado;
	

	public prestamo() {
		super();
	}

	public prestamo(String id_prestamo, com.example.bibliotecaSena.models.usuario usuario,
			com.example.bibliotecaSena.models.libro libro, LocalDate fecha_prestamo, LocalDate fecha_devolucion,
			String estado) {
		super();
		this.id_prestamo = id_prestamo;
		this.usuario = usuario;
		this.libro = libro;
		this.fecha_prestamo = fecha_prestamo;
		this.fecha_devolucion = fecha_devolucion;
		this.estado = estado;
	}


	public String getId_prestamo() {
		return id_prestamo;
	}

	public void setId_prestamo(String id_prestamo) {
		this.id_prestamo = id_prestamo;
	}

	

	public usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(usuario usuario) {
		this.usuario = usuario;
	}

	public libro getLibro() {
		return libro;
	}

	public void setLibro(libro libro) {
		this.libro = libro;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
}
