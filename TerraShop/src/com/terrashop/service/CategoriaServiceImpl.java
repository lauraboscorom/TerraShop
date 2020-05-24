package com.terrashop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.terrashop.dao.CategoriaDao;
import com.terrashop.entity.Categoria;

@Transactional
@Service
public class CategoriaServiceImpl implements CategoriaService {

	@Autowired
	CategoriaDao categoriaDao;
	
	@Override
	public List<Categoria> listarCategorias() {
		return categoriaDao.listarCategorias();
	}

	@Override
	public Categoria obtenerProductoPorNombre(String categoria) {
		return categoriaDao.obtenerProductoPorNombre(categoria);
	}

	@Override
	public Categoria obtenerCategoria(Long idCategoria) {
		return categoriaDao.find(idCategoria);
	}

	@Override
	public void eliminarCategoria(Long idCategoria) {
		categoriaDao.eliminarCategoria(idCategoria);
	}

	@Override
	public void crearCategoria(Categoria c) {
		categoriaDao.create(c);
	}

}
