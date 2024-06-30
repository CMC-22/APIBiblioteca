package com.example.bibliotecaSena.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bibliotecaSena.interfaces.Ilibro;
import com.example.bibliotecaSena.interfaces.Iprestamo;
import com.example.bibliotecaSena.interfaces.Iusuario;
import com.example.bibliotecaSena.interfacesService.IprestamoService;
import com.example.bibliotecaSena.models.libro;
import com.example.bibliotecaSena.models.prestamo;
import com.example.bibliotecaSena.models.usuario;

@Service
public class prestamoService implements IprestamoService{
	
	@Autowired
	private Iprestamo data;
	
	@Autowired
    private Iusuario usuarioService; // Repositorio de usuario
    
    @Autowired
    private Ilibro libroService; // Repositorio de libro
    
    @Override
    public String save(prestamo prestamo) {
    	Optional<usuario> usuario = usuarioService.findById(prestamo.getUsuario().getId_usuario());
    	Optional<libro> libro = libroService.findById(prestamo.getLibro().getId_libro());
    	
    	if (!usuario.isPresent()) {
    		throw new IllegalArgumentException("Usuario no encontrado");
    	}
    	
    	if (!libro.isPresent()) {
    		throw new IllegalArgumentException("Libro no encontrado");
    	}
    	
    	prestamo.setUsuario(usuario.get());
    	prestamo.setLibro(libro.get());
    	
    	return data.save(prestamo).getId_prestamo();
     
    }

	
	@Override
	public List<prestamo>findAll() {
		return (List<prestamo>)data.findAll();
	}

	
	@Override
	public Optional<prestamo> findOne(String id_prestamo) {
		return data.findById(id_prestamo);
	}
	
	@Override
	public int delete(String id_prestamo) {
		data.deleteById(id_prestamo);
		return 1;
	}
	

}
