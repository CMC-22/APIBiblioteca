package com.example.bibliotecas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bibliotecas.interfaceService.IusuariosService;
import com.example.bibliotecas.interfaces.Iusuario;
import com.example.bibliotecas.model.usuarios;

@Service
public class usuariosService implements IusuariosService{

	@Autowired
	private Iusuario data;
	
	@Override
	public String save(usuarios usuarios) {
		data.save(usuarios);
		return usuarios.getId_usuarios();
	}
	
	@Override
	public List<usuarios> findAll() {
		List<usuarios> listaUsuarios= (List<usuarios>) data.findAll();
		return listaUsuarios;
	}


	@Override
	public List<usuarios> filtroUsuarios(String filtro) {
		List<usuarios>listaUsuarios=data.filtroUsuarios(filtro);
		return listaUsuarios;
	}
	
	@Override
	public Optional<usuarios> findOne(String id_usuarios) {
		Optional<usuarios>usuarios=data.findById(id_usuarios);
		return usuarios;
	}
	
	@Override
	public int delete(String id_usuarios) {
		data.deleteById(id_usuarios);
		return 1;
	}

}
