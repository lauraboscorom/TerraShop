package com.terrashop.dao;

import java.util.List;

import com.terrashop.dto.ProductoDto;
import com.terrashop.entity.Producto;

public interface ProductoDao extends GenericDao<Producto> {

	public List<Producto> listarProductos();

	public Producto obtenerProductoPorNombre(String nombre);

	public List<ProductoDto> listarProductoPorNombre(String nombreProducto);

}
