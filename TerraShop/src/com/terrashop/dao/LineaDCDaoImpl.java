package com.terrashop.dao;

import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.terrashop.entity.LineaDC;
import com.terrashop.entity.Producto;
import com.terrashop.entity.Venta;

@Repository
@Component("LineaDCDao")
public class LineaDCDaoImpl extends GenericDaoImpl<LineaDC> implements LineaDCDao {

	@Override
	public void eliminarLineasDC(Venta venta) {
		for (LineaDC lineaDC : venta.getLineasDC()) {
			Query query = this.em.createQuery("DELETE FROM LineaDC WHERE idLineaDC = :idLineaDC");
			query.setParameter("idLineaDC", lineaDC.getIdLineaDC());
			query.executeUpdate();
		}
	}

}
