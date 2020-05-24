package com.terrashop.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.terrashop.dto.ProductoDto;
import com.terrashop.entity.Categoria;
import com.terrashop.entity.LineaDC;
import com.terrashop.entity.Producto;
import com.terrashop.entity.Venta;

public interface ProductoDao extends GenericDao<Producto> {

	public Producto obtenerProductoPorNombre(String nombre);

	public List<ProductoDto> listarProductoPorNombre(String nombreProducto);

	public List<Producto> listarProductosPorNombre(String nombreProducto);
	
	public ProductoDto recogerProducto(Long idProducto);

	public List<ProductoDto> listarProductos();

	public List<ProductoDto> listarProductosPorCategoria(Categoria categoria);

	public void eliminarLineasDC(Long idProducto, Set<LineaDC> lineasDC);

	public Page<ProductoDto> findPaginated(Pageable pageable);

	public Page<ProductoDto> findPaginatedByCategory(Pageable pageable, Categoria categoria);

}
