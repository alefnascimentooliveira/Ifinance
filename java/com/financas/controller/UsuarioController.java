package com.financas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financas.configuration.jwt.TokenService;
import com.financas.convert.UsuarioConvert;
import com.financas.domain.dto.token.TokenDto;
import com.financas.domain.dto.usuario.UsuarioCadastroDto;
import com.financas.domain.dto.usuario.UsuarioLoginDto;
import com.financas.domain.model.Usuario;
import com.financas.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioConvert usuarioConvert;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;

	@PostMapping("/login")
	public ResponseEntity<?> logar(@RequestBody @Valid UsuarioLoginDto usuarioLoginDto) {

		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
				usuarioLoginDto.getUsername(), usuarioLoginDto.getSenha());

		Authentication authenticationon = this.authenticationManager.authenticate(auth);

		String token = this.tokenService.gerarToken(authenticationon);

		TokenDto tokenDto = new TokenDto();
		tokenDto.setToken(token);

		return new ResponseEntity<>(tokenDto, HttpStatus.ACCEPTED);

	}

	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioCadastroDto usuarioCadastroDto) {
		Usuario usuario = this.usuarioConvert.converter(usuarioCadastroDto);
		this.usuarioService.cadastrar(usuario);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
