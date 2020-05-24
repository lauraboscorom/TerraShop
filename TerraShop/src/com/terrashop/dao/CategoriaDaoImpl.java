package com.terrashop.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.terrashop.entity.Categoria;
import com.terrashop.entity.Producto;

@Repository
@Component("CategoriaDao")
public class CategoriaDaoImpl extends GenericDaoImpl<Categoria> implements CategoriaDao {

	@Override
	public List<Categoria> listarCategorias() {
		Query query = this.em.createQuery("FROM Categoria");
		List<Categoria> lCategorias = query.getResultList();
		
		if (lCategorias == null) {
			return null;
		}
		return lCategorias;
	}

	@Override
	public Categoria obtenerProductoPorNombre(String categoria) {
		Query query = this.em.createQuery("select u FROM Categoria u where u.nombre= :nombre");
		query.setParameter("nombre", categoria);
		Categoria c = (Categoria) query.getSingleResult();
		
		if (c != null) {
			return c;
		}
		return null;
	}


}
