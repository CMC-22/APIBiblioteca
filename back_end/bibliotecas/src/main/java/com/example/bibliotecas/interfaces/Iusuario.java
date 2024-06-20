package com.example.bibliotecas.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.bibliotecas.model.usuarios;

public interface Iusuario extends CrudRepository<usuarios, String> {
@Query("SELECT u FROM usuarios u WHERE "
		+"u.nombre LIKE %?1% OR "
		+"u.correo LIKE %?1%")
List<usuarios>filtroUsuarios(String filtro);
}
