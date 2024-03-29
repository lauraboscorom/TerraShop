package com.terrashop.service;

import com.terrashop.entity.Usuario;

public interface UsuarioService {

	public Usuario crearUsuario(Usuario nuevoUsuario);

	public Usuario buscarUsuarioPorUsuario(String usuario);

	public Usuario obtenerUsuario(long idUsuario);

	public Usuario editarUsuario(Usuario usuarioEditado);

}
