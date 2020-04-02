package com.terrashop.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;

import com.terrashop.entity.Producto;

@Repository
@Component("ProductoDao")
public class ProductoDaoImpl extends GenericDaoImpl<Producto> implements ProductoDao {

	@Override
	public List<Producto> listarProductos() {
		Query query = this.em.createQuery("FROM Producto");
		List<Producto> lProductos = query.getResultList();

		if (lProductos != null) {
			return lProductos;
		}
		return null;
	}

}
