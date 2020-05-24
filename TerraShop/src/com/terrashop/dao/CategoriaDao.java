package com.terrashop.dao;

import java.util.List;

import com.terrashop.entity.Categoria;

public interface CategoriaDao extends GenericDao<Categoria> {

	public List<Categoria> listarCategorias();

	public Categoria obtenerProductoPorNombre(String categoria);

}
