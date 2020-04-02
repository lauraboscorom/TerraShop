package com.terrashop.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.terrashop.dao.VentaDao;
import com.terrashop.entity.LineaDC;
import com.terrashop.entity.Venta;

@Transactional
@Service
public class VentaServiceImpl implements VentaService {
	
	@Autowired
	private VentaDao ventaDao;

	@Override
	public void setLineaDC(Venta venta, Set<LineaDC> lineasDC) {
		ventaDao.setLineaDC(venta, lineasDC);
	}

	@Override
	public Venta crearVenta(Venta venta) {
		return ventaDao.create(venta);
	}

	@Override
	public List<Venta> listarVentas(Long idUsuario) {
		return ventaDao.listarVentas(idUsuario);
	}

}
