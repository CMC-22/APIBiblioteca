package com.example.bibliotecaSena.models;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;



@Entity(name="multa")
public class multas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id_multas", nullable = false, length = 36)
	private String id_multas;
	
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", nullable = false)
	private usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "id_prestamo", nullable = false)
	private prestamo prestamo;
	
	
	@Column(name = "valor_multa", nullable = false, length = 13)
	private String valor_multa;
	
	@Column(name = "fecha_multa", nullable = false)
	private LocalDate fecha_multa;
	
	@Column(name = "estado", nullable = false, length = 50)
	private String estado;

	public multas() {
		super();
	}

	public multas(String id_multas, com.example.bibliotecaSena.models.usuario usuario,
			com.example.bibliotecaSena.models.prestamo prestamo, String valor_multa, LocalDate fecha_multa,
			String estado) {
		super();
		this.id_multas = id_multas;
		this.usuario = usuario;
		this.prestamo = prestamo;
		this.valor_multa = valor_multa;
		this.fecha_multa = fecha_multa;
		this.estado = estado;
	}


	public String getId_multas() {
		return id_multas;
	}


	public void setId_multas(String id_multas) {
		this.id_multas = id_multas;
	}

	public String getValor_multa() {
		return valor_multa;
	}


	public void setValor_multa(String valor_multa) {
		this.valor_multa = valor_multa;
	}

	public LocalDate getFecha_multa() {
		return fecha_multa;
	}

	public void setFecha_multa(LocalDate fecha_multa) {
		this.fecha_multa = fecha_multa;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(usuario usuario) {
		this.usuario = usuario;
	}

	public prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(prestamo prestamo) {
		this.prestamo = prestamo;
	}


}
