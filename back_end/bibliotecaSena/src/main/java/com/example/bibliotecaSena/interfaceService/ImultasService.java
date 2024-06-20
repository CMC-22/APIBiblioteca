package com.example.bibliotecaSena.interfaceService;

import java.util.List;
import java.util.Optional;


import com.example.bibliotecaSena.model.multas;

public interface ImultasService {
	public String save(multas multas);
	public List<multas>findAll();
	public Optional<multas>findOne(String id_multas);
	public int delete(String id_multas);

}
