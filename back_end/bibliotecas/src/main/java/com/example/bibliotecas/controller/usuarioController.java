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

import com.example.bibliotecas.interfaceService.IusuariosService;
import com.example.bibliotecas.model.usuarios;

@RequestMapping("/api/v1/usuarios")
@RestController
@CrossOrigin
public class usuarioController {
	 
	@Autowired
	private IusuariosService usuariosService;
	
	@PostMapping("/")
	 public ResponseEntity<Object> save(@RequestBody usuarios usuarios) {
        usuariosService.save(usuarios);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
	
	@GetMapping("/")
	public ResponseEntity<Object> findAll(){
		var ListaUsuarios=usuariosService.findAll();
		return new ResponseEntity<>(ListaUsuarios, HttpStatus.OK);
	}
	
	@GetMapping("/busquedafiltro/{filtro}")
	public ResponseEntity<Object> findFiltro(@PathVariable String filtro){
		var ListaUsuarios=usuariosService.filtroUsuarios(filtro);
		return new ResponseEntity<>(ListaUsuarios, HttpStatus.OK);
	}
		
	@GetMapping("/{id}")
	public ResponseEntity<Object> findOne(@PathVariable String id){
		var usuarios=usuariosService.findOne(id);
		return new ResponseEntity<>(usuarios,HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminarPermanente/{id}")
	public ResponseEntity<Object> delete(@PathVariable String id){
		usuariosService.delete(id);
		return new ResponseEntity<>("Registro Eliminado",HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable String id, @RequestBody usuarios usuariosUpdate){
		var usuarios = usuariosService.findOne(id).get();
		if (usuarios != null) {
			usuarios.setNombre(usuariosUpdate.getNombre());
			usuarios.setDireccion(usuariosUpdate.getDireccion());
			usuarios.setCorreo(usuariosUpdate.getCorreo());
			usuarios.setTipo_usuario(usuariosUpdate.getTipo_usuario());
			usuariosService.save(usuarios);
			return new ResponseEntity<>(usuarios, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Error paciente no encontrado",HttpStatus.BAD_REQUEST);
		}
	}


}
