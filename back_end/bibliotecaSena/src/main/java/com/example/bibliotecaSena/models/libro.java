package com.example.bibliotecaSena.models;


import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity(name="libro")
public class libro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id_libro", nullable = false, length = 36)
	private String id_libro;
	
	@Column(name = "titulo", nullable = false, length = 50)
	private String titulo;
	
	@Column(name = "autor", nullable = false, length = 50)
	private String autor;
	
	@Column(name = "isbn", nullable = false, length = 13)
	private String isbn;
	
	@Column(name = "genero", nullable = false, length = 50)
	private String genero;
	
	@Column(name = "numero_ejemplares_disponibles", nullable = false)
	private int numero_ejemplares_disponibles;
	
	@Column(name = "numero_ejemplares_ocupados", nullable = false)
	private int numero_ejemplares_ocupados;

	 @OneToMany(mappedBy = "libro")
	    private List<prestamo> prestamos;

	public libro() {
		super();
	}


	public libro(String id_libro, String titulo, String autor, String isbn, String genero,
			int numero_ejemplares_disponibles, int numero_ejemplares_ocupados) {
		super();
		this.id_libro = id_libro;
		this.titulo = titulo;
		this.autor = autor;
		this.isbn = isbn;
		this.genero = genero;
		this.numero_ejemplares_disponibles = numero_ejemplares_disponibles;
		this.numero_ejemplares_ocupados = numero_ejemplares_ocupados;
	}


	public String getId_libro() {
		return id_libro;
	}

	public void setId_libro(String id_libro) {
		this.id_libro = id_libro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}


	public int getNumero_ejemplares_disponibles() {
		return numero_ejemplares_disponibles;
	}


	public void setNumero_ejemplares_disponibles(int numero_ejemplares_disponibles) {
		this.numero_ejemplares_disponibles = numero_ejemplares_disponibles;
	}


	public int getNumero_ejemplares_ocupados() {
		return numero_ejemplares_ocupados;
	}


	public void setNumero_ejemplares_ocupados(int numero_ejemplares_ocupados) {
		this.numero_ejemplares_ocupados = numero_ejemplares_ocupados;
	}
	

}
