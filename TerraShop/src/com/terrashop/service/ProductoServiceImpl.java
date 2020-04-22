package com.terrashop.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.terrashop.dao.ProductoDao;
import com.terrashop.dto.ProductoDto;
import com.terrashop.entity.LineaDC;
import com.terrashop.entity.Producto;

@Transactional
@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	ProductoDao productoDao;
	
	@Override
	public List<Producto> listarProductos() {
		return productoDao.listarProductos();
	}

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
	public void eliminarLineasDC(Producto producto, Set<LineaDC> lineasDC) {
		productoDao.eliminarLineasDC(producto, lineasDC);
	}
	
}
