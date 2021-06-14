package org.serratec.backend.controller;

import java.net.URI;
import java.util.List;

import org.serratec.backend.dto.UsuarioDTO;
import org.serratec.backend.exception.EmailException;
import org.serratec.backend.model.Usuario;
import org.serratec.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> findAll() {
		List<UsuarioDTO> usuarios = usuarioService.findAll();
		return ResponseEntity.ok(usuarios);

	}
	

	@PostMapping
	public ResponseEntity<Usuario> inserir(@RequestBody Usuario usuario) {
		try {
		//	usuario = usuarioService.inserir(usuario);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(usuario.getId()).toUri();
			return ResponseEntity.created(uri).body(usuario);	
		}
		catch (EmailException e) {
			return ResponseEntity.unprocessableEntity().build();
		}
	}
	
}

