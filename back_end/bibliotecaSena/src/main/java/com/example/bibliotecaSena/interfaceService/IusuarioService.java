package com.example.bibliotecaSena.interfaceService;

import java.util.List;
import java.util.Optional;


import com.example.bibliotecaSena.model.usuario;

public interface IusuarioService {
	public String save(usuario usuario);
	public List<usuario>findAll();
	public Optional<usuario>findOne(String id_usuario);
	public int delete(String id_usuario);
	List<usuario>filtroUsuario(String filtro);

}
