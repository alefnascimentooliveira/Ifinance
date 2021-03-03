package com.financas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.financas.domain.model.Categoria;
import com.financas.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<?> listarCategorias() {
		List<Categoria> categoriasDto = this.categoriaService.buscarTodasAsCategorias();
		return new ResponseEntity<>(categoriasDto, HttpStatus.OK);
	}

}