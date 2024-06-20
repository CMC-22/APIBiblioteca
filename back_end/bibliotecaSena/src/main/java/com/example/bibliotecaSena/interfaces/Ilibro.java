package com.example.bibliotecaSena.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.bibliotecaSena.model.libro;

public interface Ilibro extends CrudRepository<libro,String> {
	@Query("SELECT l FROM libro l WHERE "
			+"l.titulo LIKE %?1% OR "
			+"l.autor LIKE %?1% OR "
			+"l.genero LIKE %?1% OR "
			+"l.isbn LIKE %?1%")
	List<libro>filtroLibro(String filtro);

}
