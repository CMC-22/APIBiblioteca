package com.example.bibliotecaSena.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.bibliotecaSena.models.prestamo;

@Repository
public interface Iprestamo extends CrudRepository<prestamo, String> {
	
}
