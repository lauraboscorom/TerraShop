package com.terrashop.dao;

import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.terrashop.entity.Usuario;

@Repository
@Component("UsuarioDao")
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario> implements UsuarioDao {

	@Override
	public boolean comprobarUsuario(String usuario, String contrasena) {
		Query query = this.em.createQuery("select u FROM Usuario u where u.usuario= :usuario");
		query.setParameter("usuario", usuario);
		Usuario resUsuario = (Usuario) query.getSingleResult();

		if (resUsuario.getContrasena().equals(contrasena)) {
			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public Usuario crearUsuario(Usuario nuevoUsuario) {
		this.em.persist(nuevoUsuario);
		return nuevoUsuario;
	}

	@Override
	public Usuario buscarUsuarioPorUsuario(String usuario) {
		Query query = this.em.createQuery("select u FROM Usuario u where u.usuario= :usuario");
		query.setParameter("usuario", usuario);
		return (Usuario) query.getSingleResult();
	}

	@Override
	public Usuario obtenerUsuario(long idUsuario) {
		Query query = this.em.createQuery("select u FROM Usuario u where u.idUsuario= :idUsuario");
		query.setParameter("idUsuario", idUsuario);
		return (Usuario) query.getSingleResult();
	}

}
