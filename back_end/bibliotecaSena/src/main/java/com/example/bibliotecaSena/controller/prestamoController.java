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


import com.example.bibliotecaSena.interfaceService.IprestamoService;

import com.example.bibliotecaSena.model.prestamo;

@RequestMapping("/api/v1/prestamo")
@RestController
@CrossOrigin
public class prestamoController {
	
	@Autowired
	private IprestamoService prestamoService;
	
	@PostMapping("/")
	public ResponseEntity<Object>save(@ModelAttribute("prestamo") prestamo prestamo) {
		prestamoService.save(prestamo);
		return new ResponseEntity<>(prestamo,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<Object>findAll(){
		var listaPrestamo=prestamoService.findAll();
		return new ResponseEntity<>(listaPrestamo, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Object>findOne(@PathVariable String id){
		var prestamo=prestamoService.findOne(id);
		return new ResponseEntity<>(prestamo, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object>update(@PathVariable String id, @RequestBody prestamo prestamoUpdate){
		var prestamo = prestamoService.findOne(id).get();
		if(prestamo !=null) {
			prestamo.setUsuario(prestamoUpdate.getUsuario());
			prestamo.setLibro(prestamoUpdate.getLibro());
			prestamo.setFecha_prestamo(prestamoUpdate.getFecha_prestamo());
			prestamo.setFecha_devolucion(prestamoUpdate.getFecha_devolucion());
			prestamo.setEstado(prestamoUpdate.getEstado());
			prestamoService.save(prestamo);
			return new ResponseEntity<>(prestamo, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Error paciente no encontrado", HttpStatus.BAD_REQUEST);
		}
	}

}
