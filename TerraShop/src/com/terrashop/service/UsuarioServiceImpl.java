package com.terrashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.terrashop.dao.UsuarioDao;

@Transactional
@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioDao usuarioDao;

	@Override
	public boolean comprobarUsuario(String usuario, String contrasena) {
		return usuarioDao.comprobarUsuario(usuario, contrasena);
	}

}
