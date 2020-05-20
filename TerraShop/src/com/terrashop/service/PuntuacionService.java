package com.terrashop.service;

import java.util.List;

import com.terrashop.dto.ProductoDto;
import com.terrashop.entity.Puntuacion;

public interface PuntuacionService {

	public Puntuacion crearPuntuacion(Puntuacion p);

	public List<Puntuacion> listarPuntuacionPorProducto(ProductoDto producto);

}
