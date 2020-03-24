package com.terrashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.terrashop.dao.UsuarioDao;
import com.terrashop.entity.Usuario;

@Transactional
@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioDao usuarioDao;

	@Override
	public boolean comprobarUsuario(String usuario, String contrasena) {
		return usuarioDao.comprobarUsuario(usuario, contrasena);
	}

	@Override
	public Usuario crearUsuario(Usuario nuevoUsuario) {
		return usuarioDao.crearUsuario(nuevoUsuario);
	}

	@Override
	public Usuario buscarUsuarioPorUsuario(String usuario) {
		return usuarioDao.buscarUsuarioPorUsuario(usuario);
	}

	@Override
	public Usuario obtenerUsuario(long idUsuario) {
		return usuarioDao.obtenerUsuario(idUsuario);
	}

}
