package des.springprueba.dao;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import des.springprueba.entity.Email;
import des.springprueba.entity.Profesor;

@Repository
@Component("ProfesorDao")
public class ProfesorDaoImpl extends GenericDaoImpl<Profesor> implements ProfesorDao {

	@Override
	public Profesor buscarPorEmail(String email) {
		Query query = this.em.createQuery("select u FROM Profesor u where u.email= :email");
		query.setParameter("email", email);
		Profesor profesor = (Profesor) query.getSingleResult();

		if (profesor != null) {
			return profesor;
		}
		return null;
	}

	@Override
	public List<Profesor> buscarPorfesorPorNombreYApellidos(String nombreyapellidos) {

		String nym = "%" + nombreyapellidos + "%";
		Query query = this.em.createQuery("select u FROM Profesor u where concat (u.nombre,' 'u apellidos) like  :nym");
		query.setParameter("nym", nym);
		List<Profesor> lProfesor = query.getResultList();

		if (lProfesor != null) {
			return lProfesor;
		}
		return null;
	}

	@Override
	public List<Profesor> listarPorfesores() {
		Query query = this.em.createQuery("FROM Profesor");
		List<Profesor> lProfesor = query.getResultList();

		if (lProfesor != null) {
			return lProfesor;
		}
		return null;
	}

	@Override
	public Profesor anadirEmail(long idProfesor, Email email) {

		Profesor profesor = this.find(idProfesor);
		profesor.addEmails(email);

		return profesor;
	}

}
