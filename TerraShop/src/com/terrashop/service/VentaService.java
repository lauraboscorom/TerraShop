package com.terrashop.service;

import java.util.List;
import java.util.Set;

import com.terrashop.entity.LineaDC;
import com.terrashop.entity.Venta;

public interface VentaService {

	public void setLineaDC(Venta venta, Set<LineaDC> lineasDC);

	public Venta crearVenta(Venta venta);

	public List<Venta> listarVentas(Long idUsuario);
	
	public void eliminarVenta(Long idVenta);

	public Venta obtenerVenta(long idVenta);

	public List<Venta> listarVentas();

}
