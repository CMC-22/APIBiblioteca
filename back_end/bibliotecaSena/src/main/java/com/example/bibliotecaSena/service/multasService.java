package com.example.bibliotecaSena.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bibliotecaSena.interfaces.Imultas;
import com.example.bibliotecaSena.interfacesService.ImultasService;
import com.example.bibliotecaSena.models.multas;

@Service
public class multasService implements ImultasService {
	
	@Autowired
	private Imultas data;
	
	@Override
	public String save(multas multas) {
		data.save(multas);
		return multas.getId_multas();
	}
	
	@Override
	public List<multas> findAll() {
		List<multas> listaMultas= (List<multas>) data.findAll();
		return listaMultas;
	}
	
	@Override
	public Optional<multas> findOne(String id_multas) {
		Optional<multas> multas=data.findById(id_multas);
		return multas;
	}
	
	@Override
	public int delete(String id_multas) {
		data.deleteById(id_multas);
		return 1;
	}

}
