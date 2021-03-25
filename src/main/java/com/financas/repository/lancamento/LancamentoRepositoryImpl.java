package com.financas.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.financas.domain.model.Lancamento;
import com.financas.filtro.LancamentoFiltro;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Lancamento> filtrar(LancamentoFiltro lancamentoFiltro, String usernameUsuario) {
		
		String sql = "select * from lancamento where lancamento.username_usuario = " + "'"+usernameUsuario+"'";
		
		// informou todos os filtros
		if(lancamentoFiltro.getDataDe() != null && lancamentoFiltro.getDataAte() != null && lancamentoFiltro.getTipo() != null) {
			sql += " and lancamento.data between " 
		        + "'"+lancamentoFiltro.getDataDe()+"'" 
				+ " and " + "'"+lancamentoFiltro.getDataAte()+"'" + " and lancamento.tipo = " + "'"+lancamentoFiltro.getTipo()+"'";
		}
		
		// informou somente a data de inicio e o tipo
		else if(lancamentoFiltro.getDataDe() != null && lancamentoFiltro.getDataAte() == null && lancamentoFiltro.getTipo() != null) {
			sql += " and lancamento.data >= " 
				+"'"+lancamentoFiltro.getDataDe()+"'" + " and lancamento.tipo = " + "'"+lancamentoFiltro.getTipo()+"'";
		}
		
		// informou somente a data fim e o tipo
		else if(lancamentoFiltro.getDataDe() == null && lancamentoFiltro.getDataAte() != null && lancamentoFiltro.getTipo() != null) {
			sql += " and lancamento.data <= " 
				+"'"+lancamentoFiltro.getDataAte()+"'" + " and lancamento.tipo = " + "'"+lancamentoFiltro.getTipo()+"'";
		}
		
		// informou somente o tipo
		else if(lancamentoFiltro.getDataDe() == null && lancamentoFiltro.getDataAte() == null && lancamentoFiltro.getTipo() != null) {
			sql += "and lancamento.tipo = " +"'"+lancamentoFiltro.getTipo()+"'";
		}
		
		// informou somente a data inicio e fim
		else if(lancamentoFiltro.getDataDe() != null && lancamentoFiltro.getDataAte() != null && lancamentoFiltro.getTipo() == null) {
			sql += " and lancamento.data between " 
			        + "'"+lancamentoFiltro.getDataDe()+"'" 
					+ " and " + "'"+lancamentoFiltro.getDataAte()+"'";
		}
		
		// informou somente a data inicio
		else if(lancamentoFiltro.getDataDe() != null && lancamentoFiltro.getDataAte() == null && lancamentoFiltro.getTipo() == null) {
			sql += " and lancamento.data >= " 
					+"'"+lancamentoFiltro.getDataDe()+"'";
		}
		
		// informou somente a data fim
		else if(lancamentoFiltro.getDataDe() == null && lancamentoFiltro.getDataAte() != null && lancamentoFiltro.getTipo() == null) {
			sql += " and lancamento.data <= " 
					+"'"+lancamentoFiltro.getDataAte()+"'";
		}
		
		
		
		Query query = entityManager.createNativeQuery(sql, Lancamento.class);
		
		return query.getResultList();
	
	}

	

}
