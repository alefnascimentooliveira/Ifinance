package com.financas.configuration.jwt;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.financas.domain.model.Usuario;
import com.financas.repository.UsuarioRepository;

public class TokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;

	private UsuarioRepository usuarioRepository;

	public TokenFilter(TokenService tokenService, UsuarioRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = getToken(request);

		boolean valido = tokenService.validarToken(token);

		if (valido) {
			autenticarUsuarioValidado(token);

		}
		filterChain.doFilter(request, response);

	}

	private String getToken(HttpServletRequest request) {
		String header = request.getHeader(("Authorization"));

		if (header == null || header.isEmpty() || !header.startsWith("Bearer")) {
			return null;
		}

		String token = header.replaceAll("Bearer", "");

		return token;
	}

	private void autenticarUsuarioValidado(String token) {
		String username = this.tokenService.getUsername(token);

		Optional<Usuario> usuario = this.usuarioRepository.buscarUsuario(username);

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				usuario.get().getUsername(), usuario.get().getSenha(), null);

		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
