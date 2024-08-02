package com.example.bibliotecaSena.interfacesService;

import java.util.List;
import java.util.Optional;
import com.example.bibliotecaSena.models.usuario;

public interface IusuarioService {
	public String save (usuario usuario);
	public List<usuario>findAll();
	public Optional<usuario>findOne(String id_usuario);
	public int delete(String id_usuario);
	List<usuario>filtroUsuario(String filtro);
	boolean existeNombre(String nombre);
	public Optional<usuario> findById(String id_usuario);
	public List<usuario> usuariosRegistrados();
	public List<usuario> usuariosPrestamos();	

}
