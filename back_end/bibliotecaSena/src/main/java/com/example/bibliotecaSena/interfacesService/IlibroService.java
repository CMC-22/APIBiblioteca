package com.example.bibliotecaSena.interfacesService;

import java.util.List;
import java.util.Optional;

import com.example.bibliotecaSena.models.libro;

public interface IlibroService {
	public String save (libro libro);
	public List<libro>findAll();
	public Optional<libro>findOne(String id_libro);
	public int delete(String id_libro);
	List<libro>filtroLibro(String filtro);
	boolean existeTitulo(String titulo);
	boolean existsByIsbn(String isbn);
	public List<libro> librosRegistrados();
	Optional<libro> findById(String id_libro);
	

}
