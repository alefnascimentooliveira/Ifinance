package com.financas.convert;

import org.springframework.stereotype.Component;

import com.financas.domain.dto.usuario.UsuarioCadastroDto;
import com.financas.domain.model.Usuario;
import com.financas.util.BeanUtil;

@Component
public class UsuarioConvert {

	public Usuario converter(UsuarioCadastroDto usuarioCadastroDto) {
		Usuario usuario = new Usuario();
		usuario.setNome(usuarioCadastroDto.getNome());
		usuario.setEmail(usuarioCadastroDto.getEmail());
		String senhaCriptografada = BeanUtil.getBCryptPasswordEncoder().encode(usuarioCadastroDto.getSenha());
		usuario.setSenha(senhaCriptografada);
		usuario.setUsername(usuarioCadastroDto.getUsername());
		return usuario;
	}

}
