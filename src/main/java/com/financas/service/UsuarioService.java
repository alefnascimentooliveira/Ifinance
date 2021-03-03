package com.financas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.financas.domain.model.Lancamento;
import com.financas.domain.model.Usuario;
import com.financas.exception.UsuarioNaoEncontradoException;
import com.financas.repository.LancamentoRepository;
import com.financas.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	private LancamentoRepository lancamentoRepository;

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
	
	@Transactional
	public void atualizar(String username, Usuario novoUsuario) {
		Optional<Usuario> usuario = this.usuarioRepository.buscarUsuario(username);
		
		if(usuario.isPresent()) {
			usuario.get().setUsername(novoUsuario.getUsername());
			usuario.get().setNome(novoUsuario.getNome());
			usuario.get().setEmail(novoUsuario.getEmail());
			usuario.get().setSenha(novoUsuario.getSenha());
			
		}
		else {
			throw new UsuarioNaoEncontradoException("usuário não encontrado");
		}
		
	}
	
	@Transactional
	public void excluirConta(String usernameUsuario) {
		
		Optional<Usuario> usuario = this.usuarioRepository.buscarUsuario(usernameUsuario);
		
		if(usuario.isPresent()) {
			
			List<Lancamento> lancamentos = this.lancamentoRepository.buscarTodos(usernameUsuario);
			
			for(Lancamento lancamento : lancamentos) {
				this.lancamentoRepository.delete(lancamento);
			}
			
			this.usuarioRepository.delete(usuario.get());
			
		}
		
		else {
			throw new UsuarioNaoEncontradoException("usuário não encontrado");
		}
		
		
	}
	
	

}
