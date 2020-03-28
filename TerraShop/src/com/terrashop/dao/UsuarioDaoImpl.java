package com.terrashop.dao;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.terrashop.entity.Rol;
import com.terrashop.entity.Usuario;

@Repository
@Component("UsuarioDao")
public class UsuarioDaoImpl extends GenericDaoImpl<Usuario> implements UsuarioDao {

	@Autowired
	private RolRepository rolRepository;
	
	public Usuario create (Usuario usuario) {
		Set<Rol> roles  =new HashSet<Rol>();

		Rol rol = rolRepository.getOne(1);
		roles.add(rol);
		usuario.setRoles(roles);
        this.em.persist(usuario);

		return usuario;
	}

	@Override
	public Usuario buscarUsuarioPorUsuario(String usuario) {
		Query query = this.em.createQuery("select u FROM Usuario u where u.usuario= :usuario");
		query.setParameter("usuario", usuario);
		Usuario u = (Usuario) query.getSingleResult();
		
		if (u != null) {
			return u;
		}
		return null;
	}

}
