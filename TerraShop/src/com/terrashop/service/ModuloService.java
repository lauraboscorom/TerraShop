package com.terrashop.service;

import java.util.List;

import com.terrashop.entity.Modulo;

public interface ModuloService {

	public List<Modulo> listarModulos();
	
	public List<Modulo> listarModulosPorNombre(String nombreModulo);

	public Modulo obtenerModulo(long idModulo);
	
}
