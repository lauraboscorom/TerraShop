package com.terrashop.dao;

import java.util.List;

import com.terrashop.entity.Email;
import com.terrashop.entity.Profesor;

public interface ProfesorDao extends GenericDao<Profesor>{

	public Profesor buscarPorEmail (String email);
	
	public List<Profesor> buscarPorfesorPorNombreYApellidos(String nombreyapellidos);
	
	public List<Profesor> listarPorfesores();
	
	public Profesor anadirEmail(long idProfesor,Email email);
	
}
