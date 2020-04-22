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
	public List<Producto> listarProductos() {
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
	
	private ProductoDto convertToProductoDto(Producto producto) {
		ProductoDto productoDto = modelMapper.map(producto, ProductoDto.class);
        return productoDto;
    }

	@Override
	public void eliminarLineasDC(Producto producto, Set<LineaDC> lineasDC) {
		ArrayList<LineaDC> lineasDCList = new ArrayList<LineaDC>(lineasDC);
		for (int i = 0; i < lineasDCList.size(); i++) {
			producto.removeLineaDC(lineasDCList.get(i));
		}
	}

}
