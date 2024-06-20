package com.example.bibliotecas.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="libros")
public class libros {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id_libros", nullable = false, length = 36)
	private String id_libros;
	
	@Column(name = "titulo", nullable = false, length = 50)
	private String titulo;
	
	@Column(name = "autor", nullable = false, length = 50)
	private String autor;
	
	@Column(name = "genero", nullable = false, length = 50)
	private String genero;
	
	@Column(name = "isbn", nullable = true, length = 50)
	private String isbn;
	
	@Column(name = "numero_ejemplares_disponibles", nullable = false, length = 50)
	private int numero_ejemplares_disponibles;
	
	@Column(name = "numero_ejemplares_ocupados", nullable = false, length = 50)
	private int numero_ejemplares_ocupados;

	public libros() {
		super();
	}

	

	public libros(String id_libros, String titulo, String autor, String genero, String isbn,
			int numero_ejemplares_disponibles, int numero_ejemplares_ocupados) {
		super();
		this.id_libros = id_libros;
		this.titulo = titulo;
		this.autor = autor;
		this.genero = genero;
		this.isbn = isbn;
		this.numero_ejemplares_disponibles = numero_ejemplares_disponibles;
		this.numero_ejemplares_ocupados = numero_ejemplares_ocupados;
	}



	
	public String getId_libros() {
		return id_libros;
	}



	public void setId_libros(String id_libros) {
		this.id_libros = id_libros;
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



	public String getGenero() {
		return genero;
	}



	public void setGenero(String genero) {
		this.genero = genero;
	}



	public String getIsbn() {
		return isbn;
	}



	public void setIsbn(String isbn) {
		this.isbn = isbn;
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



	/*sirve para no dejar que se coloquen las ""*/
	public boolean contieneCamposVacios() {
		return titulo.isEmpty() || autor.isEmpty() || isbn.isEmpty() ||
				genero.isEmpty();
	}

}
