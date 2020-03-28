package com.terrashop.dao;

import com.terrashop.entity.Usuario;

public interface UsuarioDao extends GenericDao<Usuario> {

	public Usuario create (Usuario usuario);
	
	public Usuario buscarUsuarioPorUsuario(String usuario);

}
