package com.terrashop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.terrashop.dao.PuntuacionDao;
import com.terrashop.dto.ProductoDto;
import com.terrashop.entity.Producto;
import com.terrashop.entity.Puntuacion;

@Transactional
@Service
public class PuntuacionServiceImpl implements PuntuacionService {
	
	@Autowired
	PuntuacionDao puntuacionDao;

	@Override
	public Puntuacion crearPuntuacion(Puntuacion p) {
		return puntuacionDao.crearPuntuacion(p);
	}
	
	@Override
	public List<Puntuacion> listarPuntuacionPorProducto(ProductoDto producto) {
		return puntuacionDao.listarPuntuacionPorProducto(producto);
	}

}
