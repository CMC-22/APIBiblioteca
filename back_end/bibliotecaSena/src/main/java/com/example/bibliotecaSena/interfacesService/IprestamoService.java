package com.example.bibliotecaSena.interfacesService;

import java.util.List;
import java.util.Optional;

import com.example.bibliotecaSena.models.prestamo;

public interface IprestamoService {
	public String save (prestamo prestamo);
	public List<prestamo>findAll();
	public Optional<prestamo>findOne(String id_prestamo);
	public int delete(String id_prestamo);

}
