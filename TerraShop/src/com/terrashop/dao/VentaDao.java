package com.terrashop.dao;

import java.util.List;
import java.util.Set;

import com.terrashop.entity.LineaDC;
import com.terrashop.entity.Venta;

public interface VentaDao extends GenericDao<Venta> {

	public void setLineaDC(Venta venta, Set<LineaDC> lineasDC);

	public List<Venta> listarVentas(Long idUsuario);

	public List<Venta> listarVentas();

	public void eliminarLineasDC(Venta venta);
	
}
