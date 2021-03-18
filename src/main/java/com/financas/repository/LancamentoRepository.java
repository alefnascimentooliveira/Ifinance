package com.financas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.financas.domain.model.Lancamento;
import com.financas.repository.lancamento.LancamentoRepositoryQuery;

@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery{

	@Query("from Lancamento l where l.usuario.username = ?1 order by l.data")
	List<Lancamento> buscarTodos(String usernameUsuario);

}
