package com.example.bibliotecaSena.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bibliotecaSena.interfaces.Iusuario;
import com.example.bibliotecaSena.interfacesService.IusuarioService;
import com.example.bibliotecaSena.models.usuario;

@Service
public class usuarioService implements IusuarioService {

	@Autowired
	private Iusuario data;

	@Override
	public List<usuario>usuariosRegistrados(){
		return (List<usuario>)data.findAll();
	}
	
	@Override
	public Optional<usuario>findById(String id_usuario) {
		return data.findById(id_usuario);
	}
	
	@Override
	public List<usuario> usuariosPrestamos() {
		return (List<usuario>)data.findAll();
	}

	@Override
	public String save(usuario usuario) {
		data.save(usuario);
		return usuario.getId_usuario();
	}

	@Override
	public List<usuario> findAll() {
		return data.findAll();
	}

	@Override
	public List<usuario>filtroUsuario(String filtro) {
		List<usuario>listaUsuario=data.filtroUsuario(filtro);
		return listaUsuario;
	}

	@Override
	public Optional<usuario> findOne(String id_usuario) {
		Optional<usuario> usuario=data.findById(id_usuario);
		return usuario;
	}

	@Override
	public int delete(String id_usuario) {
		data.deleteById(id_usuario);
		return 1;
	}

	@Override
	public boolean existeNombre(String nombre) {
		List<usuario> usuarios = (List<usuario>) data.findAll();
		for (usuario usuario : usuarios) {
			if (usuario.getNombre().equals(nombre)) {
				return true;
			}
		}
		return false;
	}



}
