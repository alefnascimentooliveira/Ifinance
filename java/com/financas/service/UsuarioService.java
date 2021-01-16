package com.financas.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.financas.domain.model.Usuario;
import com.financas.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuarioOptional = this.usuarioRepository.buscarUsuario(username);
		if (usuarioOptional.isPresent()) {
			return usuarioOptional.get();
		}

		throw new BadCredentialsException("username ou senha inválidos");
	}

	@Transactional
	public void cadastrar(Usuario usuario) {
		this.usuarioRepository.save(usuario);
	}

}
