package com.terrashop.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.terrashop.dao.ProductoDao;
import com.terrashop.dto.ProductoDto;
import com.terrashop.entity.Categoria;
import com.terrashop.entity.LineaDC;
import com.terrashop.entity.Producto;
import com.terrashop.entity.Venta;

@Transactional
@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	ProductoDao productoDao;

	@Override
	public Producto obtenerProducto(Long idProducto) {
		return productoDao.find(idProducto);
	}

	@Override
	public Producto editarProducto(Producto producto) {
		return productoDao.update(producto);
	}

	@Override
	public void eliminarProducto(Long idProducto) {
		productoDao.delete(idProducto);
	}

	@Override
	public Producto crearProducto(Producto p) {
		return productoDao.create(p);
	}

	@Override
	public Producto obtenerProductoPorNombre(String nombre) {
		return productoDao.obtenerProductoPorNombre(nombre);
	}

	@Override
	public List<ProductoDto> listarProductoPorNombre(String nombreProducto) {
		return productoDao.listarProductoPorNombre(nombreProducto);
	}

	@Override
	public List<Producto> listarProductosPorNombre(String nombreProducto) {
		return productoDao.listarProductosPorNombre(nombreProducto);
	}

	@Override
	public ProductoDto recogerProducto(Long idProducto) {
		return productoDao.recogerProducto(idProducto);
	}

	@Override
	public List<ProductoDto> listarProductos() {
		return productoDao.listarProductos();
	}

	@Override
	public List<ProductoDto> listarProductosPorCategoria(Categoria categoria) {
		return productoDao.listarProductosPorCategoria(categoria);
	}

	@Override
	public void eliminarLineasDC(Long idProducto, Set<LineaDC> lineasDC) {
		productoDao.eliminarLineasDC(idProducto, lineasDC);
	}

	@Override
	public Page<ProductoDto> findPaginated(Pageable pageable) {
		return productoDao.findPaginated(pageable);
	}

	@Override
	public Page<ProductoDto> findPaginatedByCategory(Pageable pageable, Categoria categoria) {
		return productoDao.findPaginatedByCategory(pageable, categoria);
	}

}
