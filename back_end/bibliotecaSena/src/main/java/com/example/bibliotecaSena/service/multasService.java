package com.example.bibliotecaSena.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bibliotecaSena.interfaces.Imultas;
import com.example.bibliotecaSena.interfaces.Iprestamo;
import com.example.bibliotecaSena.interfaces.Iusuario;
import com.example.bibliotecaSena.interfacesService.ImultasService;
import com.example.bibliotecaSena.models.multas;
import com.example.bibliotecaSena.models.prestamo;
import com.example.bibliotecaSena.models.usuario;

@Service
public class multasService implements ImultasService {
	
	@Autowired
	private Imultas data;
	
	@Autowired
	private Iusuario usuarioService;
	
	@Autowired
	private Iprestamo prestamoService;
	
	@Override
	public String save(multas multas) {
		Optional<usuario> usuario = usuarioService.findById(multas.getUsuario().getId_usuario());
		Optional<prestamo> prestamo = prestamoService.findById(multas.getPrestamo().getId_prestamo());
		
		if (!usuario.isPresent()) {
			throw new IllegalArgumentException("Usuario no encontrado.");
		}
		
		if (!prestamo.isPresent()) {
			throw new IllegalArgumentException("Prestamo no encontrado.");
		}
		
		multas.setUsuario(usuario.get());
		multas.setPrestamo(prestamo.get());
		
		return data.save(multas).getId_multas().toString();
	}
	
	@Override
	public List<multas> findAll() {
		return (List<multas>)data.findAll();
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
	
	@Override
	public Optional<multas>findById(String id_multas) {
		return data.findById(id_multas);
	}

}
