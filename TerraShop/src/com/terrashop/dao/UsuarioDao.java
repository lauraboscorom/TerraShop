package com.terrashop.dao;

import com.terrashop.entity.Usuario;

public interface UsuarioDao extends GenericDao<Usuario> {
	
	public boolean comprobarUsuario (String usuario, String contrasena);

}
