package com.terrashop.dao;

import java.util.List;
import java.util.Set;

import com.terrashop.dto.ProductoDto;
import com.terrashop.entity.LineaDC;
import com.terrashop.entity.Producto;

public interface ProductoDao extends GenericDao<Producto> {

	public List<Producto> listarProductos();

	public Producto obtenerProductoPorNombre(String nombre);

	public List<ProductoDto> listarProductoPorNombre(String nombreProducto);

	public void eliminarLineasDC(Producto producto, Set<LineaDC> lineasDC);

	public List<Producto> listarProductosPorNombre(String nombreProducto);

}
