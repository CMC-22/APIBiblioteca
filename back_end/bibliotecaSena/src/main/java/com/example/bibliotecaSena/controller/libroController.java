package com.example.bibliotecaSena.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bibliotecaSena.interfaceService.IlibroService;
import com.example.bibliotecaSena.model.libro;

@RequestMapping("/api/v1/libro")
@RestController
@CrossOrigin
public class libroController {
	@Autowired
	private IlibroService libroService;
	
	@PostMapping("/")
	public ResponseEntity<Object>save(@RequestBody libro libro) {
		libroService.save(libro);
		return new ResponseEntity<>(libro,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<Object>findAll(){
		var listaLibro=libroService.findAll();
		return new ResponseEntity<>(listaLibro, HttpStatus.OK);
	}
	
	@GetMapping("/busquedafiltro/{filtro}")
	public ResponseEntity<Object>filtroLibro(@PathVariable String filtro){
		var listaLibro=libroService.filtroLibro(filtro);
		return new ResponseEntity<>(listaLibro, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object>findOne(@PathVariable String id){
		var libro=libroService.findOne(id);
		return new ResponseEntity<>(libro, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object>update(@PathVariable String id, @RequestBody libro libroUpdate){
		var libro = libroService.findOne(id).get();
		if(libro !=null) {
			libro.setTitulo(libroUpdate.getTitulo());
			libro.setAutor(libroUpdate.getAutor());
			libro.setGenero(libroUpdate.getGenero());
			libro.setIsbn(libroUpdate.getIsbn());
			libro.setNumero_ejemplares_disponibles(libroUpdate.getNumero_ejemplares_disponibles());
			libro.setNumero_ejemplares_ocupados(libroUpdate.getNumero_ejemplares_ocupados());
			libroService.save(libro);
			return new ResponseEntity<>(libro, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Error paciente no encontrado", HttpStatus.BAD_REQUEST);
		}
	}

}
