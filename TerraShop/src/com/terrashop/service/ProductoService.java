package com.terrashop.service;

import java.util.List;

import com.terrashop.entity.Producto;

public interface ProductoService {

	public List<Producto> listarProductos();

	public Producto obtenerProducto(Long idProducto);

	public Producto editarProducto(Producto producto);

}
