package com.terrashop.dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.terrashop.dto.ProductoDto;
import com.terrashop.entity.Producto;
import com.terrashop.entity.Puntuacion;

@Repository
@Component("PuntuacionDao")
public class PuntuacionDaoImpl extends GenericDaoImpl<Puntuacion> implements PuntuacionDao {

	@Override
	public Puntuacion crearPuntuacion(Puntuacion p) {
		Query query = this.em.createQuery("select u FROM Puntuacion u where u.producto= :producto and u.usuario= :usuario");
		query.setParameter("producto", p.getProducto());
		query.setParameter("usuario", p.getUsuario());
		try {
			Puntuacion puntuacion = (Puntuacion) query.getSingleResult();			
			puntuacion.setValor(p.getValor());
			return this.update(puntuacion);
		} catch (NoResultException e) {
			return this.create(p);			
		}
	}

	@Override
	public List<Puntuacion> listarPuntuacionPorProducto(ProductoDto producto) {
		
		Query query = this.em.createQuery("select u FROM Puntuacion u where u.producto.idProducto= :p");
		query.setParameter("p", producto.getIdProducto());
		
		List<Puntuacion> LPuntuaciones = query.getResultList();
		
		if (LPuntuaciones == null) {
			return null;
		}
		
		return LPuntuaciones.stream()
				.collect(Collectors.toList());
	}

}
