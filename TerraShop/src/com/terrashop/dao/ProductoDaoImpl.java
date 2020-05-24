package com.terrashop.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;

import com.terrashop.dto.ProductoDto;
import com.terrashop.entity.Categoria;
import com.terrashop.entity.Imagen;
import com.terrashop.entity.LineaDC;
import com.terrashop.entity.Pregunta;
import com.terrashop.entity.Producto;
import com.terrashop.entity.Venta;

@Repository
@Component("ProductoDao")
public class ProductoDaoImpl extends GenericDaoImpl<Producto> implements ProductoDao {

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Producto obtenerProductoPorNombre(String nombre) {
		Query query = this.em.createQuery("select u FROM Producto u where u.nombre= :nombre");
		query.setParameter("nombre", nombre);
		Producto p = (Producto) query.getSingleResult();

		if (p != null) {
			return p;
		}
		return null;
	}

	@Override
	public List<Producto> listarProductosPorNombre(String nombreProducto) {
		String n = "%" + nombreProducto + "%";
		Query query = this.em.createQuery("FROM Producto u where u.nombre like :n");
		query.setParameter("n", n);
		List<Producto> lProductos = query.getResultList();
		return lProductos;
	}

	@Override
	public List<ProductoDto> listarProductoPorNombre(String nombreProducto) {

		String n = "%" + nombreProducto + "%";
		Query query = this.em.createQuery("FROM Producto u where u.nombre like :n");
		query.setParameter("n", n);
		List<Producto> lProducto = query.getResultList();

		if (lProducto == null) {
			return null;
		}

		return lProducto.stream().map(this::convertToProductoDto).collect(Collectors.toList());
	}

	@Override
	public ProductoDto recogerProducto(Long idProducto) {
		Producto p = this.find(idProducto);
		if (p == null) {
			return null;
		}

		return convertToProductoDto(p);
	}

	private ProductoDto convertToProductoDto(Producto producto) {
		ProductoDto productoDto = modelMapper.map(producto, ProductoDto.class);
		Query query = this.em.createQuery("SELECT idImagen FROM Imagen u where u.producto = :p");
		query.setParameter("p", producto);
		List<Long> lIdImagenes = query.getResultList();
		productoDto.setIdImagenes(lIdImagenes);
		query = this.em.createQuery("FROM LineaDC u where u.producto = :p");
		query.setParameter("p", producto);
		List<Long> lLineasDC = query.getResultList();
		productoDto.setHasLineasDC(lLineasDC.isEmpty());
		
		return productoDto;
	}

	@Override
	public List<ProductoDto> listarProductos() {
		Query query = this.em.createQuery("FROM Producto");
		List<Producto> lProductos = query.getResultList();

		if (lProductos == null) {
			return null;
		}

		return lProductos.stream().map(this::convertToProductoDto).collect(Collectors.toList());
	}

	@Override
	public List<ProductoDto> listarProductosPorCategoria(Categoria categoria) {
		Query query = this.em.createQuery("FROM Producto u where u.categoria = :categoria");
		query.setParameter("categoria", categoria);
		List<Producto> lProductos = query.getResultList();

		if (lProductos == null) {
			return null;
		}

		return lProductos.stream().map(this::convertToProductoDto).collect(Collectors.toList());
	}

	@Override
	public void eliminarLineasDC(Long idProducto, Set<LineaDC> lineasDC) {
		Producto producto = this.find(idProducto);
		for (LineaDC lineaDC : lineasDC) {
			producto.removeLineaDC(lineaDC);
		}
	}

	@Override
	public Page<ProductoDto> findPaginated(Pageable pageable) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<ProductoDto> list;
		Query query = this.em.createQuery("FROM Producto");
		List<Producto> productos = query.getResultList();
		List<ProductoDto> productosDto = productos.stream().map(this::convertToProductoDto)
				.collect(Collectors.toList());

		if (productosDto.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, productos.size());
			list = productosDto.subList(startItem, toIndex);
		}

		Page<ProductoDto> productoPage = new PageImpl<ProductoDto>(list, PageRequest.of(currentPage, pageSize),
				productosDto.size());

		return productoPage;
	}

	@Override
	public Page<ProductoDto> findPaginatedByCategory(Pageable pageable, Categoria categoria) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<ProductoDto> list;
		Query query = this.em.createQuery("FROM Producto u where u.categoria = :categoria");
		query.setParameter("categoria", categoria);
		List<Producto> productos = query.getResultList();
		List<ProductoDto> productosDto = productos.stream().map(this::convertToProductoDto)
				.collect(Collectors.toList());

		if (productosDto.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, productos.size());
			list = productosDto.subList(startItem, toIndex);
		}

		Page<ProductoDto> productoPage = new PageImpl<ProductoDto>(list, PageRequest.of(currentPage, pageSize),
				productosDto.size());

		return productoPage;
	}

	@Override
	public void eliminarProducto(Long idProducto) {
		Producto producto = this.find(idProducto);
		//Borrar imagenes
		Query query = this.em.createQuery("DELETE FROM Imagen WHERE producto = :producto");
		query.setParameter("producto", producto);
		query.executeUpdate();
		//Recuperar preguntas
		query = this.em.createQuery("FROM Pregunta u where producto = :producto");
		query.setParameter("producto", producto);
		List<Pregunta> preguntas = query.getResultList();
		//Borrar respuestas de preguntas
		for (int i = 0; i < preguntas.size(); i++) {
			query = this.em.createQuery("DELETE FROM Respuesta WHERE pregunta = :pregunta");
			query.setParameter("pregunta", preguntas.get(i));
			query.executeUpdate();
		}
		//Borrar preguntas
		query = this.em.createQuery("DELETE FROM Pregunta WHERE producto = :producto");
		query.setParameter("producto", producto);
		query.executeUpdate();
		//Borrar puntuaciones
		query = this.em.createQuery("DELETE FROM Puntuacion WHERE producto = :producto");
		query.setParameter("producto", producto);
		query.executeUpdate();
		// Borrar producto
		query = this.em.createQuery("DELETE FROM Producto WHERE idProducto = :idProducto");
		query.setParameter("idProducto", idProducto);
		query.executeUpdate();
	}

}
