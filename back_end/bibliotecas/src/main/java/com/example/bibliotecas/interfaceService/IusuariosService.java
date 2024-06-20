package com.example.bibliotecas.interfaceService;

import java.util.List;
import java.util.Optional;

import com.example.bibliotecas.model.usuarios;

public interface IusuariosService {
	public String save (usuarios usuarios);
	public List<usuarios>findAll();
	public Optional<usuarios>findOne(String id_usuarios);
	public int delete(String id_usuario);
	List<usuarios>filtroUsuarios(String filtro);

}
