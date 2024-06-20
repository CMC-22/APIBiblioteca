package com.example.bibliotecas.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bibliotecas.interfaceService.ILibrosService;
import com.example.bibliotecas.interfaces.ILibros;
import com.example.bibliotecas.model.libros;


@Service
public class librosService implements ILibrosService {
	@Autowired
	private ILibros data;
	
	@Override
	public String save(libros libros) {
		data.save(libros);
		return libros.getId_libros();
	}
	
	@Override
	public List<libros> findAll() {
		List<libros> listaLibros = (List<libros>) data.findAll();
		return listaLibros;
	}


	@Override
	public List<libros> filtroLibros(String filtro) {
		List<libros>listaLibros=data.filtroLibros(filtro);
		return listaLibros;
	}
	
	@Override
	public Optional<libros> findOne(String id_libros) {
		Optional<libros>libros=data.findById(id_libros);
		return libros;
	}
	
	@Override
	public int delete(String id_libros) {
		data.deleteById(id_libros);
		return 1;
	}

}
