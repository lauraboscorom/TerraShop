package com.terrashop.service;

import java.util.List;

import com.terrashop.entity.Categoria;

public interface CategoriaService {

	public List<Categoria> listarCategorias();

	public Categoria obtenerProductoPorNombre(String categoria);

	public Categoria obtenerCategoria(Long idCategoria);

}
