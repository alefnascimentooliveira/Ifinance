package com.financas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.financas.domain.model.Categoria;
import com.financas.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Transactional(readOnly = true)
	public List<Categoria> buscarTodasAsCategorias() {
		List<Categoria> categorias = this.categoriaRepository.findAll();
		return categorias;
	}

}
