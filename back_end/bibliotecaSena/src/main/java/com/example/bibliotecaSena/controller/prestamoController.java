package com.example.bibliotecaSena.Controller;

import java.util.List;

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
import com.example.bibliotecaSena.interfacesService.IprestamoService;
import com.example.bibliotecaSena.interfacesService.IusuarioService;
import com.example.bibliotecaSena.models.libro;
import com.example.bibliotecaSena.models.prestamo;
import com.example.bibliotecaSena.models.usuario;

@RequestMapping("/api/v1/prestamo")
@RestController
@CrossOrigin
public class prestamoController {
	
	@Autowired
	private IprestamoService prestamoService;
	
	@Autowired
	private IusuarioService usuarioService;
	
	@Autowired
	private IlibroService libroService;
	
	@GetMapping("/usuariosregistrados")
	public ResponseEntity<List<usuario>> usuariosRegistrados() {
	        List<usuario> listaUsuarios = usuarioService.findAll();
	        return new ResponseEntity<>(listaUsuarios, HttpStatus.OK);
	}
	
	@GetMapping("/librosregistrados")
	public ResponseEntity<List<libro>> librosRegistrados() {
	        List<libro> listaLibros = libroService.findAll();
	        return new ResponseEntity<>(listaLibros, HttpStatus.OK);
	}

	@PostMapping("/")
	public ResponseEntity<Object> registrarPrestamo(@RequestBody prestamo prestamo) {
		try {
			String idPrestamo = prestamoService.save(prestamo);
			return new ResponseEntity<>(idPrestamo, HttpStatus.CREATED);
		}catch (Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	

	@GetMapping("/")
	public ResponseEntity<Object>findAll(){
		var listaPrestamo = prestamoService.findAll();
		return new ResponseEntity<>(listaPrestamo, HttpStatus.OK);
	}
	

	@GetMapping("/{id}")
	public ResponseEntity<Object>findOne(@PathVariable String id){
		var prestamo = prestamoService.findOne(id);
		return new ResponseEntity<>(prestamo, HttpStatus.OK);
	}

	@DeleteMapping("/eliminarPermanente/{id}")
	public ResponseEntity<Object>delete(@PathVariable String id){
		prestamoService.delete(id);
		return new ResponseEntity<>("Registro eliminado", HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object>update(@PathVariable String id, @RequestBody prestamo prestamoUpdate){
		var prestamo = prestamoService.findOne(id).get();
		if (prestamo !=null) {
			prestamo.setFecha_devolucion(prestamoUpdate.getFecha_devolucion());
			prestamo.setFecha_prestamo(prestamoUpdate.getFecha_prestamo());
			prestamo.setLibro(prestamoUpdate.getLibro());
			prestamo.setUsuario(prestamoUpdate.getUsuario());
			prestamo.setEstado(prestamoUpdate.getEstado());
			prestamoService.save(prestamo);
			return new ResponseEntity<>(prestamo,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Error: prestamo no encontrado",HttpStatus.BAD_REQUEST);
		}
	}

}
