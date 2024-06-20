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


import com.example.bibliotecaSena.interfaceService.ImultasService;

import com.example.bibliotecaSena.model.multas;

@RequestMapping("/api/v1/multas")
@RestController
@CrossOrigin
public class multasController {
	@Autowired
	private ImultasService multasService;
	
	@PostMapping("/")
	public ResponseEntity<Object>save(@ModelAttribute("multas") multas multas) {
		multasService.save(multas);
		return new ResponseEntity<>(multas,HttpStatus.OK);
	}
	
	@GetMapping("/")
	public ResponseEntity<Object>findAll(){
		var listaMultas=multasService.findAll();
		return new ResponseEntity<>(listaMultas, HttpStatus.OK);
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<Object>findOne(@PathVariable String id){
		var multas=multasService.findOne(id);
		return new ResponseEntity<>(multas, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object>update(@PathVariable String id, @RequestBody multas multasUpdate){
		var multas = multasService.findOne(id).get();
		if(multas !=null) {
			multas.setPrestamo(multasUpdate.getPrestamo());
			multas.setUsuario_multado(multasUpdate.getUsuario_multado());
			multas.setValor_multa(multasUpdate.getValor_multa());
			multas.setFecha_multa(multasUpdate.getFecha_multa());
			multas.setEstado(multasUpdate.getEstado());
			multasService.save(multas);
			return new ResponseEntity<>(multas, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Error paciente no encontrado", HttpStatus.BAD_REQUEST);
		}
	}

}
