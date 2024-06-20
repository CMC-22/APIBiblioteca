package com.example.bibliotecaSena.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="multas")
public class multas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id_multas", nullable = false, length = 36)
	private String id_multas;
	
	@Column(name = "usuario_multado", nullable = false, length = 50)
	private String usuario_multado;

	
	@Column(name = "prestamo", nullable = false, length = 50)
	private String prestamo;

	
	@Column(name = "valor_multa", nullable = false, length = 50)
	private String valor_multa;

	@Column(name = "fecha_multa", nullable = false, length = 50)
	private LocalDate fecha_multa;

	@Column(name = "estado", nullable = false, length = 50)
	private String estado;

	public multas() {
		super();
	}



	public multas(String id_multas, String usuario_multado, String prestamo, String valor_multa, LocalDate fecha_multa,
			String estado) {
		super();
		this.id_multas = id_multas;
		this.usuario_multado = usuario_multado;
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

	public String getUsuario_multado() {
		return usuario_multado;
	}

	public void setUsuario_multado(String usuario_multado) {
		this.usuario_multado = usuario_multado;
	}

	public String getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(String prestamo) {
		this.prestamo = prestamo;
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
	
	


}
