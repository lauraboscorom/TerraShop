package com.terrashop.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;

import com.terrashop.dto.ProductoDto;
import com.terrashop.entity.LineaDC;
import com.terrashop.entity.Producto;

@Repository
@Component("ProductoDao")
public class ProductoDaoImpl extends GenericDaoImpl<Producto> implements ProductoDao {

	@Autowired
    private ModelMapper modelMapper;
	
	@Override
	public List<Producto> listarProductos2() {
		Query query = this.em.createQuery("FROM Producto");
		List<Producto> lProductos = query.getResultList();

		if (lProductos != null) {
			return lProductos;
		}
		return null;
	}

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
	public void eliminarLineasDC(Producto producto, Set<LineaDC> lineasDC) {
		ArrayList<LineaDC> lineasDCList = new ArrayList<LineaDC>(lineasDC);
		for (int i = 0; i < lineasDCList.size(); i++) {
			producto.removeLineaDC(lineasDCList.get(i));
		}
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
		
		return lProducto.stream()
				.map(this::convertToProductoDto)
				.collect(Collectors.toList());
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
		
		return productoDto;
    }

	@Override
	public List<ProductoDto> listarProductos() {
		Query query = this.em.createQuery("FROM Producto");
		List<Producto> lProductos = query.getResultList();
		
		if (lProductos == null) {
			return null;
		}
		
		return lProductos.stream()
				.map(this::convertToProductoDto)
				.collect(Collectors.toList());
	}

}
