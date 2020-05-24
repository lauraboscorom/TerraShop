package com.terrashop.dao;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.terrashop.entity.LineaDC;
import com.terrashop.entity.Producto;
import com.terrashop.entity.Venta;

@Repository
@Component("VentaDao")
public class VentaDaoImpl extends GenericDaoImpl<Venta> implements VentaDao {

	@Override
	public void setLineaDC(Venta venta, Set<LineaDC> lineasDC) {
		venta.setLineasDC(lineasDC);
	}

	@Override
	public List<Venta> listarVentas(Long idUsuario) {
		Query query = this.em.createQuery("FROM Venta WHERE id_usuario= :idUsuario");
		query.setParameter("idUsuario", idUsuario);
		List<Venta> lVentas = query.getResultList();

		if (lVentas != null) {
			return lVentas;
		}
		return null;
	}

	@Override
	public List<Venta> listarVentas() {
		Query query = this.em.createQuery("FROM Venta");
		List<Venta> lVentas = query.getResultList();

		if (lVentas != null) {
			return lVentas;
		}
		return null;
	}

	@Override
	public void eliminarLineasDC(Venta venta) {
//		Iterator<LineaDC> lineaDC = venta.getLineasDC().iterator();
//		while (lineaDC.hasNext()) {
//			venta.removeLineaDC((LineaDC) lineaDC);
//		}
		venta.setLineasDC(new HashSet<>());
	}

}
