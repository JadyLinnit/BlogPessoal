package com.postagem.atvpostagem.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.postagem.atvpostagem.model.UserLogin;
import com.postagem.atvpostagem.model.Usuario;
import com.postagem.atvpostagem.repository.UsuarioRepository;
import com.postagem.atvpostagem.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository repository;
	
	@GetMapping("/all")
	public ResponseEntity<List<Usuario>> findAllPostagens(){
		return ResponseEntity.ok(repository.findAll());
	}
@GetMapping("/{id}")
	public ResponseEntity<Optional<Usuario>> findById(@PathVariable("id") Long id){
		return ResponseEntity.ok(repository.findById(id));
	}
	
	@PostMapping("/logar")
	public ResponseEntity<UserLogin> Autentication(@RequestBody Optional<UserLogin> user) {
		return usuarioService.logarUsuario(user).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@Valid @RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(usuarioService.cadastrarUsuario(usuario).get());
	}

	@PostMapping("/atualizar")
	public ResponseEntity<Usuario> Put(@Valid @RequestBody Usuario usuario) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(usuarioService.cadastrarUsuario(usuario).get());
	}
}
