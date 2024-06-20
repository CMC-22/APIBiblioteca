package com.example.bibliotecaSena.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import com.example.bibliotecaSena.model.usuario;

public interface Iusuario extends CrudRepository<usuario,String> {
	@Query("SELECT u FROM usuario u WHERE "
			+"u.nombre LIKE %?1% OR "
			+"u.correo LIKE %?1%")
	List<usuario>filtroUsuario(String filtro);
}
