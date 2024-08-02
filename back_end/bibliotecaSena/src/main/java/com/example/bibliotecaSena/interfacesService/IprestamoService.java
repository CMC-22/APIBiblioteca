package com.example.bibliotecaSena.interfacesService;

import java.util.List;
import java.util.Optional;

import com.example.bibliotecaSena.models.prestamo;
import com.example.bibliotecaSena.models.usuario;

public interface IprestamoService {
	public String save (prestamo prestamo);
	public List<prestamo>findAll();
	public Optional<prestamo>findOne(String id_prestamo);
	public int delete(String id_prestamo);
	List<prestamo> findByUsuarioAndEstado(usuario usuario, String estado);
	public Optional<prestamo> findById(String id_prestamo);

}
