package com.terrashop.dao;

import com.terrashop.entity.LineaDC;
import com.terrashop.entity.Venta;

public interface LineaDCDao extends GenericDao<LineaDC> {

	public void eliminarLineasDC(Venta venta);

}
