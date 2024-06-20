package com.example.bibliotecaSena.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bibliotecaSena.interfaceService.IprestamoService;
import com.example.bibliotecaSena.interfaces.Iprestamo;
import com.example.bibliotecaSena.model.prestamo;

@Service
public class prestamoService implements IprestamoService{

	@Autowired
	private Iprestamo data;
	
	@Override
	public List<prestamo>findAll(){
		List<prestamo>listaPrestamo = (List<prestamo>)data.findAll();
		return listaPrestamo;
	}
	
	@Override
	public Optional<prestamo>findOne(String id_prestamo){
		Optional<prestamo>prestamo=data.findById(id_prestamo);
		return prestamo;
	}
	
	@Override
	public int delete(String id_prestamo) {
		data.deleteById(id_prestamo);
		return 1; 
	}

	@Override
	public String save(prestamo prestamo) {
		// TODO Auto-generated method stub
		return null;
	}



}
