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
	public void eliminarVenta(Long idVenta) {
		Venta venta = this.find(idVenta);
		Query query = this.em.createQuery("DELETE FROM LineaDC WHERE venta = :venta");
		query.setParameter("venta", venta);
		query.executeUpdate();
		query = this.em.createQuery("DELETE FROM Venta WHERE idVenta = :idVenta");
		query.setParameter("idVenta", idVenta);
		query.executeUpdate();
	}

}
