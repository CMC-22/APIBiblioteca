package com.example.bibliotecas.interfaces;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.bibliotecas.model.libros;

public interface ILibros extends CrudRepository<libros,String> {
	@Query("SELECT l FROM libros l WHERE " 
			+"l.titulo LIKE %?1% OR " 
			+"l.autor LIKE %?1% OR " 
			+"l.genero LIKE %?1% OR "
			+"l.isbn LIKE %?1%")
	
	//WHERE l.titulo LIKE %?1% OR
	List<libros>filtroLibros(String filtro);

}
