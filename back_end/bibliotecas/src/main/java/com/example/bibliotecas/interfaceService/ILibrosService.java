package com.example.bibliotecas.interfaceService;

import java.util.List;
import java.util.Optional;

import com.example.bibliotecas.model.libros;

public interface ILibrosService {

	public String save (libros libros);
	public List<libros>findAll();
	public Optional<libros> findOne(String id_libros);
	public int delete(String id_libros);
	List<libros>filtroLibros(String filtro);

}
