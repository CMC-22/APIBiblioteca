package com.example.bibliotecaSena.interfaceService;

import java.util.List;
import java.util.Optional;

import com.example.bibliotecaSena.model.libro;

public interface IlibroService {
	public String save(libro libro);
	public List<libro>findAll();
	public Optional<libro>findOne(String id_libro);
	public int delete(String id_libro);
	List<libro>filtroLibro(String filtro);
}
