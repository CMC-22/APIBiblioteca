package com.example.bibliotecaSena.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bibliotecaSena.interfaceService.IlibroService;
import com.example.bibliotecaSena.interfaces.Ilibro;
import com.example.bibliotecaSena.model.libro;

@Service
public class libroService implements IlibroService{
	
	@Autowired
	private Ilibro data;
	
	@Override
	public String save(libro libro) {
		data.save(libro);
		return libro.getId_libro();
	}
	
	@Override

	public List<libro>findAll(){
		List<libro>listaLibro = (List<libro>)data.findAll();
		return listaLibro;
	}
	
	@Override
	public List<libro> filtroLibro(String filtro) {
		List<libro>listaLibro=data.filtroLibro(filtro);
		return listaLibro;
	}
	
	@Override
	public Optional<libro>findOne(String id_libro){
		Optional<libro>libro=data.findById(id_libro);
		return libro;
	}
	
	@Override
	public int delete(String id_libro) {
		data.deleteById(id_libro);
		return 1; 
	}



}
