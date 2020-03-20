package com.terrashop.dao;

import java.util.List;

import com.terrashop.entity.Modulo;

public interface ModuloDao extends GenericDao<Modulo>{

	public List<Modulo> listarModulos();
	
	public List<Modulo> listarModulosPorNombre(String nombreModulo);
	
	
}
