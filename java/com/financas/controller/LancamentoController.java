package com.financas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financas.convert.LancamentoConvert;
import com.financas.domain.dto.lancamento.LancamentoCadastroDto;
import com.financas.domain.dto.lancamento.LancamentoRetornoDto;
import com.financas.domain.dto.lancamento.LancamentoEdicaoDto;
import com.financas.domain.model.Lancamento;
import com.financas.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoController {

	@Autowired
	private LancamentoService lancamentoService;

	@Autowired
	private LancamentoConvert lancamentoConvert;

	@GetMapping
	public ResponseEntity<?> buscarTodos() {
		String usernameUsuario = SecurityContextHolder.getContext().getAuthentication().getName();
		List<Lancamento> lancamentos = this.lancamentoService.buscarTodos(usernameUsuario);
		List<LancamentoRetornoDto> lancamentosRetornoDto = this.lancamentoConvert.converter(lancamentos);
		return new ResponseEntity<>(lancamentosRetornoDto, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> cadastrar(@RequestBody @Valid LancamentoCadastroDto lancamentoCadastroDto) {
		Lancamento lancamento = this.lancamentoConvert.converter(lancamentoCadastroDto);
		this.lancamentoService.cadastrar(lancamento);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> deletar(@PathVariable Long codigo) {
		this.lancamentoService.deletar(codigo);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<?> atualizar(@PathVariable Long codigo,
			@RequestBody @Valid LancamentoEdicaoDto lancamentoEdicaoDto) {
		Lancamento lancamento = this.lancamentoConvert.converter(lancamentoEdicaoDto);
		this.lancamentoService.atualizar(codigo, lancamento);
		return new ResponseEntity<>(HttpStatus.OK);

	}

	@GetMapping("/{codigo}")
	private ResponseEntity<?> buscarLancamentoPelocodigo(@PathVariable Long codigo) {
		Lancamento lancamentoCadastrado = this.lancamentoService.buscarLancamentoPeloCodigo(codigo);
		LancamentoRetornoDto lancamentoRetornoDto = this.lancamentoConvert.converter(lancamentoCadastrado);
		return new ResponseEntity<>(lancamentoRetornoDto, HttpStatus.OK);
	}
}
