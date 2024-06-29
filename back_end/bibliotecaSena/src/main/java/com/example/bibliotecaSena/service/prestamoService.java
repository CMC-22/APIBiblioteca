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
    private Iusuario Iusuario; // Repositorio de usuario
    
    @Autowired
    private Ilibro Ilibro; // Repositorio de libro
    
    @Override
    public String save(prestamo prestamo) {
        // Validar que el usuario y el libro existan
        usuario usuario = Iusuario.findById(prestamo.getUsuario().getId_usuario())
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        libro libro = Ilibro.findById(prestamo.getLibro().getId_libro())
                .orElseThrow(() -> new IllegalArgumentException("Libro no encontrado"));

        prestamo.setUsuario(usuario);
        prestamo.setLibro(libro);

        return data.save(prestamo).getId_prestamo();
    }

	
	@Override
	public List<prestamo>findAll() {
		List<prestamo> listaPrestamo= (List<prestamo>) data.findAll();
		return listaPrestamo;
	}

	
	@Override
	public Optional<prestamo> findOne(String id_prestamo) {
		Optional<prestamo> prestamo=data.findById(id_prestamo);
		return prestamo;
	}
	
	@Override
	public int delete(String id_prestamo) {
		data.deleteById(id_prestamo);
		return 1;
	}
	

}
