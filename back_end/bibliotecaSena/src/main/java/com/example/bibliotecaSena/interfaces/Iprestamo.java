package com.example.bibliotecaSena.interfaces;

import org.springframework.data.repository.CrudRepository;

import com.example.bibliotecaSena.model.prestamo;

public interface Iprestamo extends CrudRepository<prestamo, String> {

}
