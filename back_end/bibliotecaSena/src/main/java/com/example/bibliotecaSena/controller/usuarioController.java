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

import com.example.bibliotecaSena.interfaceService.IusuarioService;
import com.example.bibliotecaSena.model.usuario;

@RequestMapping("/api/v1/usuario")
@RestController
@CrossOrigin
public class usuarioController {

	@Autowired
	private IusuarioService usuarioService;
	
	@PostMapping("/")
	public ResponseEntity<Object>save(@ModelAttribute("usuario") usuario usuario) {
		usuarioService.save(usuario);
		return new ResponseEntity<>(usuario,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<Object>findAll(){
		var listaUsuario=usuarioService.findAll();
		return new ResponseEntity<>(listaUsuario, HttpStatus.OK);
	}
	
	@GetMapping("/busquedafiltro/{filtro}")
	public ResponseEntity<Object>filtroUsuario(@PathVariable String filtro){
		var listaUsuario=usuarioService.filtroUsuario(filtro);
		return new ResponseEntity<>(listaUsuario, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object>findOne(@PathVariable String id){
		var usuario=usuarioService.findOne(id);
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object>update(@PathVariable String id, @RequestBody usuario usuarioUpdate){
		var usuario = usuarioService.findOne(id).get();
		if(usuario !=null) {
			usuario.setNombre(usuarioUpdate.getNombre());
			usuario.setCorreo(usuarioUpdate.getCorreo());
			usuario.setDireccion(usuarioUpdate.getDireccion());
			usuario.setTipo_usuario(usuarioUpdate.getTipo_usuario());;
			usuarioService.save(usuario);
			return new ResponseEntity<>(usuario, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Error paciente no encontrado", HttpStatus.BAD_REQUEST);
		}
	}
}
