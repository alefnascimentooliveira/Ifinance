package com.financas.repository.lancamento;

import java.util.List;

import com.financas.domain.model.Lancamento;
import com.financas.filtro.LancamentoFiltro;

public interface LancamentoRepositoryQuery {
	
	public List<Lancamento> filtrar(LancamentoFiltro lancamentoFiltro, String usernameUsuario);

}
