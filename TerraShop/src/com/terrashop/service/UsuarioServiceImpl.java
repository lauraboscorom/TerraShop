package com.terrashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.terrashop.dao.UsuarioDao;
import com.terrashop.entity.Usuario;

@Transactional
@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioDao usuarioDao;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public Usuario crearUsuario(Usuario nuevoUsuario) {
		nuevoUsuario.setPassword(bCryptPasswordEncoder.encode(nuevoUsuario.getPassword()));
		return usuarioDao.create(nuevoUsuario);
	}

	@Override
	public Usuario buscarUsuarioPorUsuario(String usuario) {
		return usuarioDao.buscarUsuarioPorUsuario(usuario);
	}

	@Override
	public Usuario obtenerUsuario(long idUsuario) {
		return usuarioDao.find(idUsuario);
	}

	@Override
	public Usuario editarUsuario(Usuario usuarioEditado) {
		return usuarioDao.update(usuarioEditado);
	}

}
