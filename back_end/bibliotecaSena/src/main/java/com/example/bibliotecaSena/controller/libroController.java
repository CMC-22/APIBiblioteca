package com.example.bibliotecaSena.Controller;

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

import com.example.bibliotecaSena.interfacesService.IlibroService;
import com.example.bibliotecaSena.models.libro;

@RequestMapping("/api/v1/libro")
@RestController
@CrossOrigin
public class libroController {

	@Autowired
	private IlibroService libroService;

	@PostMapping("/")
	public ResponseEntity<Object> save(@RequestBody libro libro) {
		if (libro.getIsbn().length() != 13) {
			return new ResponseEntity<>("El Isbn debe tener exactamente 13 digitos numericos", HttpStatus.BAD_REQUEST);
		}
		libroService.save(libro);
		return new ResponseEntity<>(libro, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<Object>findAll(){
		var listaLibro = libroService.findAll();
		return new ResponseEntity<>(listaLibro, HttpStatus.OK);
	}

    @GetMapping("/check/{titulo}")
    public ResponseEntity<Boolean> verificarTitulo(@PathVariable String titulo) {
        boolean existe = libroService.existeTitulo(titulo);
        return new ResponseEntity<>(existe, HttpStatus.OK);
    }
    
    @GetMapping("/checkIsbn/{isbn}")
    public ResponseEntity<Boolean> verificarIsbn(@PathVariable String isbn) {
        boolean exists = libroService.existsByIsbn(isbn);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }

	@GetMapping("/busquedafiltro/{filtro}")
	public ResponseEntity<Object>filtroLibro(@PathVariable String filtro){
		var listaLibro = libroService.filtroLibro(filtro);
		return new ResponseEntity<>(listaLibro, HttpStatus.OK);
	}



	@GetMapping("/{id}")
	public ResponseEntity<Object>findOne(@PathVariable String id){
		var libro = libroService.findOne(id);
		return new ResponseEntity<>(libro, HttpStatus.OK);
	}
	

	@DeleteMapping("/eliminar/{id}")
	public ResponseEntity<Object>delete(@PathVariable String id){
		var libro = libroService.delete(id);
		return new ResponseEntity<>(libro, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object>update(@PathVariable String id, @RequestBody libro libroUpdate){
		var libro = libroService.findOne(id).get();
		if (libro !=null) {
			libro.setTitulo(libroUpdate.getTitulo());
			libro.setAutor(libroUpdate.getAutor());
			libro.setGenero(libroUpdate.getGenero());
			libro.setIsbn(libroUpdate.getIsbn());
			libro.setNumero_ejemplares_disponibles(libroUpdate.getNumero_ejemplares_disponibles());
			libro.setNumero_ejemplares_ocupados(libroUpdate.getNumero_ejemplares_ocupados());
			libroService.save(libro);
			return new ResponseEntity<>(libro,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Error: libro no encontrado",HttpStatus.BAD_REQUEST);
		}
	}

}
