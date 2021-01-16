package com.financas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.financas.domain.model.Lancamento;
import com.financas.repository.LancamentoRepository;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Transactional
	public void cadastrar(Lancamento lancamento) {
		this.lancamentoRepository.save(lancamento);
	}

	@Transactional(readOnly = true)
	public List<Lancamento> buscarTodos(String usernameUsuario) {
		return this.lancamentoRepository.buscarTodos(usernameUsuario);
	}

	@Transactional
	public void deletar(Long codigoDoLancamento) {
		this.lancamentoRepository.deleteById(codigoDoLancamento);
	}

	@Transactional
	public void atualizar(Long codigoDoLancamentoCadastrado, Lancamento lancamentoNovo) {

		Optional<Lancamento> optional = this.lancamentoRepository.findById(codigoDoLancamentoCadastrado);

		Lancamento lancamentoSalvo = optional.get();

		lancamentoSalvo.setTipo(lancamentoNovo.getTipo());
		lancamentoSalvo.setDescricao(lancamentoNovo.getDescricao());
		lancamentoSalvo.setData(lancamentoNovo.getData());
		lancamentoSalvo.setValor(lancamentoNovo.getValor());
		lancamentoSalvo.setCategoria(lancamentoNovo.getCategoria());

		this.lancamentoRepository.save(lancamentoSalvo);

	}

	@Transactional
	public Lancamento buscarLancamentoPeloCodigo(Long codigoLancamento) {
		Optional<Lancamento> lancamento = this.lancamentoRepository.findById(codigoLancamento);
		if (lancamento.isPresent()) {
			return lancamento.get();
		} else {
			throw new RuntimeException("Lancamento não encontrado");
		}
	}

}
