package com.terrashop.service;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.terrashop.dto.ProductoDto;
import com.terrashop.entity.Categoria;
import com.terrashop.entity.LineaDC;
import com.terrashop.entity.Producto;
import com.terrashop.entity.Venta;

public interface ProductoService {

	public List<ProductoDto> listarProductos();

	public Producto obtenerProducto(Long idProducto);

	public Producto editarProducto(Producto producto);

	public void eliminarProducto(Long idProducto);

	public Producto crearProducto(Producto p);

	public Producto obtenerProductoPorNombre(String parameter);

	public List<Producto> listarProductosPorNombre(String nombreProducto);
	
	public List<ProductoDto> listarProductoPorNombre(String nombreProducto);
	
	public ProductoDto recogerProducto(Long idProducto);

	public List<ProductoDto> listarProductosPorCategoria(Categoria categoria);

	public void eliminarLineasDC(Long idProducto, Set<LineaDC> lineasDC);

	public Page<ProductoDto> findPaginated(Pageable pageable);

	public Page<ProductoDto> findPaginatedByCategory(Pageable pageable, Categoria categoria);

}
