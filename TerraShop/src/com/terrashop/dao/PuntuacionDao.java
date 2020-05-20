
package com.terrashop.dao;

import java.util.List;

import com.terrashop.dto.ProductoDto;
import com.terrashop.entity.Producto;
import com.terrashop.entity.Puntuacion;

public interface PuntuacionDao extends GenericDao<Puntuacion> {

	public Puntuacion crearPuntuacion(Puntuacion p);

	public List<Puntuacion> listarPuntuacionPorProducto(ProductoDto producto);

}
