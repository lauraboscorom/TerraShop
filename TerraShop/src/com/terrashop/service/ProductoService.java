package com.terrashop.service;

import java.util.List;

import com.terrashop.dto.ProductoDto;
import com.terrashop.entity.Producto;

public interface ProductoService {

	public List<Producto> listarProductos();

	public Producto obtenerProducto(Long idProducto);

	public Producto editarProducto(Producto producto);

	public void eliminarProducto(Long idProducto);

	public Producto crearProducto(Producto p);

	public Producto obtenerProductoPorNombre(String parameter);

	public List<ProductoDto> listarProductoPorNombre(String nombreProducto);

}
