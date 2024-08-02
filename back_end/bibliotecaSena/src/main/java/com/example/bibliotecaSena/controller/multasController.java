package com.example.bibliotecaSena.Controller;

import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bibliotecaSena.interfacesService.ImultasService;
import com.example.bibliotecaSena.interfacesService.IprestamoService;
import com.example.bibliotecaSena.interfacesService.IusuarioService;
import com.example.bibliotecaSena.models.multas;
import com.example.bibliotecaSena.models.prestamo;
import com.example.bibliotecaSena.models.usuario;


@RequestMapping("/api/v1/multas")
@RestController
@CrossOrigin
public class multasController {
	
	@Autowired
	private ImultasService multasService;
	
	/*@Autowired
	private IusuarioService usuarioService;
	
	@Autowired
	private IprestamoService prestamoService;
	
	@GetMapping("/usuariosprestamo")
    public ResponseEntity<List<usuario>> usuariosPrestamo() {
        List<usuario> listaUsuarios = usuarioService.findAll();
        return new ResponseEntity<>(listaUsuarios, HttpStatus.OK);
    }

    @GetMapping("/prestamos/usuario/{id_usuario}")
    public ResponseEntity<List<prestamo>> prestamosPorUsuario(@PathVariable String id_usuario) {
        Optional<usuario> usuario = usuarioService.findById(id_usuario);
        if (!usuario.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<prestamo> listaPrestamos = prestamoService.findByUsuarioAndEstado(usuario.get(), "activo"); // Suponiendo que "activo" es el estado deseado
        return new ResponseEntity<>(listaPrestamos, HttpStatus.OK);
    }*/

    @PostMapping("/")
    public ResponseEntity<multas> save(@RequestBody multas multas) {
         multasService.save(multas);
        return new ResponseEntity<>(multas, HttpStatus.OK);
    }

	@GetMapping("/")
	public ResponseEntity<Object>findAll(){
		var listaMultas = multasService.findAll();
		return new ResponseEntity<>(listaMultas, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object>findOne(@PathVariable String id){
		var multas = multasService.findOne(id);
		return new ResponseEntity<>(multas, HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminarPermanente/{id}")
	public ResponseEntity<Object>delete(@PathVariable String id){
		multasService.delete(id);
		return new ResponseEntity<>("Registro eliminado", HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object>update(@PathVariable String id, @RequestBody multas multasUpdate){
		var multas = multasService.findOne(id).get();
		if (multas !=null) {
			multas.setValor_multa(multasUpdate.getValor_multa());
			multas.setFecha_multa(multasUpdate.getFecha_multa());
			multas.setEstado(multasUpdate.getEstado());
			multasService.save(multas);
			return new ResponseEntity<>(multas,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Error: multas no encontrado",HttpStatus.BAD_REQUEST);
		}
	}

}
