package com.example.bibliotecaSena.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bibliotecaSena.interfaceService.IusuarioService;
import com.example.bibliotecaSena.interfaces.Iusuario;
import com.example.bibliotecaSena.model.usuario;

@Service
public class usuarioService implements IusuarioService {

	@Autowired
	private Iusuario data;
	
	@Override
	public List<usuario>findAll(){
		List<usuario>listaUsuario = (List<usuario>)data.findAll();
		return listaUsuario;
	}
	
	@Override
	public Optional<usuario>findOne(String id_usuario){
		Optional<usuario>usuario=data.findById(id_usuario);
		return usuario;
	}
	
	@Override
	public int delete(String id_usuario) {
		data.deleteById(id_usuario);
		return 1; 
	}

	@Override
	public String save(usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<usuario> filtroUsuario(String filtro) {
		// TODO Auto-generated method stub
		return null;
	}

}
