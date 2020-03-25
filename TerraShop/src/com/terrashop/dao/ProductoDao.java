package com.terrashop.dao;

import java.util.List;

import com.terrashop.entity.Producto;

public interface ProductoDao extends GenericDao<Producto> {

	public List<Producto> listarProductos();

}
