package com.example.bibliotecas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bibliotecas.interfaceService.ILibrosService;
import com.example.bibliotecas.model.libros;




@RequestMapping("/api/v1/libros")
@RestController
@CrossOrigin
public class librosController {
    
	@Autowired
	private ILibrosService librosService;
	
	@PostMapping("/")
	 public ResponseEntity<Object> save(@RequestBody libros libros) {
        librosService.save(libros);
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }
	
	@GetMapping("/")
	public ResponseEntity<Object> findAll(){
		var ListaLibros=librosService.findAll();
		return new ResponseEntity<>(ListaLibros, HttpStatus.OK);
	}

	
	@GetMapping("/busquedafiltro/{filtro}")
	public ResponseEntity<Object> findFiltro(@PathVariable String filtro){
		var ListaLibros=librosService.filtroLibros(filtro);
		return new ResponseEntity<>(ListaLibros, HttpStatus.OK);
	}
		
	@GetMapping("/{id}")
	public ResponseEntity<Object> findOne(@PathVariable String id){
		var libros=librosService.findOne(id);
		return new ResponseEntity<>(libros,HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminarPermanente/{id}")
	public ResponseEntity<Object> delete(@PathVariable String id){
		librosService.delete(id);
		return new ResponseEntity<>("Registro Eliminado",HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable String id, @RequestBody libros librosUpdate){
		var libros= librosService.findOne(id).get();
		if (libros != null) {
			libros.setTitulo(librosUpdate.getTitulo());
			libros.setAutor(librosUpdate.getAutor());
			libros.setGenero(librosUpdate.getGenero());
			libros.setIsbn(librosUpdate.getIsbn());
			libros.setNumero_ejemplares_disponibles(librosUpdate.getNumero_ejemplares_disponibles());
			libros.setNumero_ejemplares_ocupados(librosUpdate.getNumero_ejemplares_ocupados());
			librosService.save(libros);
			return new ResponseEntity<>(libros, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Error paciente no encontrado",HttpStatus.BAD_REQUEST);
		}
	}


}
