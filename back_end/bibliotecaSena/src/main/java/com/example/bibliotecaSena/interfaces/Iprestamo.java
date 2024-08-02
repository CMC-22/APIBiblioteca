package com.example.bibliotecaSena.interfaces;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.bibliotecaSena.models.prestamo;
import com.example.bibliotecaSena.models.usuario;

@Repository
public interface Iprestamo extends CrudRepository<prestamo, String> {
	
	List<prestamo>findByUsuarioAndEstado(usuario usuario, String estado);
}
