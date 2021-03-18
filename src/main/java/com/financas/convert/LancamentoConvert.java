package com.financas.convert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.financas.domain.dto.ValorTotalDto;
import com.financas.domain.dto.lancamento.LancamentoCadastroDto;
import com.financas.domain.dto.lancamento.LancamentoRetornoDto;
import com.financas.domain.dto.lancamento.LancamentoRetornoValorTotalDto;
import com.financas.domain.dto.lancamento.LancamentoEdicaoDto;
import com.financas.domain.model.Categoria;
import com.financas.domain.model.Lancamento;
import com.financas.domain.model.Usuario;
import com.financas.repository.CategoriaRepository;
import com.financas.repository.UsuarioRepository;

@Component
public class LancamentoConvert {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public LancamentoRetornoValorTotalDto converter(List<Lancamento> lancamentos) {
		List<LancamentoRetornoDto> lancamentosRetornoDto = new ArrayList<>();
		
		BigDecimal valor = BigDecimal.valueOf(0.00);
		
		for (Lancamento l : lancamentos) {
			LancamentoRetornoDto lancamentoRetornoDto = new LancamentoRetornoDto();
			lancamentoRetornoDto.setCodigo(l.getCodigo());
			lancamentoRetornoDto.setTipo(l.getTipo());
			lancamentoRetornoDto.setData(l.getData());
			lancamentoRetornoDto.setDescricao(l.getDescricao());
			lancamentoRetornoDto.setValor(l.getValor());
			lancamentoRetornoDto.setCategoria(l.getCategoria().getDescricao());
			
			if (lancamentoRetornoDto.getTipo().equals("RECEITA")) {
				valor = valor.add(lancamentoRetornoDto.getValor());
			} else if (lancamentoRetornoDto.getTipo().equals("DESPESA")) {
				valor = valor.subtract(l.getValor());
			}
			
			lancamentosRetornoDto.add(lancamentoRetornoDto);
		}

		LancamentoRetornoValorTotalDto lancamentoRetornoValorTotalDto = new LancamentoRetornoValorTotalDto();
		lancamentoRetornoValorTotalDto.setLancamentos(lancamentosRetornoDto);
		lancamentoRetornoValorTotalDto.setValorTotal(valor);
		
		return lancamentoRetornoValorTotalDto;
	}

	public Lancamento converter(LancamentoCadastroDto lancamentoCadastroDto) {
		Lancamento lancamento = new Lancamento();
		lancamento.setTipo(lancamentoCadastroDto.getTipo());
		lancamento.setDescricao(lancamentoCadastroDto.getDescricao());
		lancamento.setValor(lancamentoCadastroDto.getValor());
		lancamento.setData(lancamentoCadastroDto.getData());
		Categoria c = buscarCategoriaPeloCodigo(lancamentoCadastroDto.getCategoria());
		lancamento.setCategoria(c);
		Usuario u = buscarUsuarioPeloUsername(lancamentoCadastroDto.getUsernameUsuario());
		lancamento.setUsuario(u);
		return lancamento;
	}

	public Lancamento converter(LancamentoEdicaoDto lancamentoEdicaoDto) {
		Lancamento lancamento = new Lancamento();
		lancamento.setTipo(lancamentoEdicaoDto.getTipo());
		lancamento.setDescricao(lancamentoEdicaoDto.getDescricao());
		lancamento.setData(lancamentoEdicaoDto.getData());
		lancamento.setValor(lancamentoEdicaoDto.getValor());
		Categoria categoria = buscarCategoriaPeloCodigo(lancamentoEdicaoDto.getCategoria());
		lancamento.setCategoria(categoria);
		return lancamento;
	}

	public LancamentoRetornoDto converter(Lancamento lancamento) {
		LancamentoRetornoDto lancamentoRetornoDto = new LancamentoRetornoDto();
		lancamentoRetornoDto.setCodigo(lancamento.getCodigo());
		lancamentoRetornoDto.setTipo(lancamento.getTipo());
		lancamentoRetornoDto.setDescricao(lancamento.getDescricao());
		lancamentoRetornoDto.setData(lancamento.getData());
		lancamentoRetornoDto.setValor(lancamento.getValor());
		lancamentoRetornoDto.setCategoria(lancamento.getCategoria().getDescricao());
		return lancamentoRetornoDto;
	}

	private Categoria buscarCategoriaPeloCodigo(Long codigo) {
		Optional<Categoria> categoria = this.categoriaRepository.findById(codigo);
		return categoria.get();
	}

	private Usuario buscarUsuarioPeloUsername(String usernameUsuario) {
		Optional<Usuario> usuario = this.usuarioRepository.buscarUsuario(usernameUsuario);
		return usuario.get();
	}

}
