package com.terrashop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.terrashop.entity.Email;
import com.terrashop.entity.Profesor;
import com.terrashop.service.ProfesorService;

@Controller
@RequestMapping(value = "/profesor")
public class ProfesorController {

	@Autowired
	ProfesorService profesorService;

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public ModelAndView listarPorfesores() {

		ModelAndView mav = new ModelAndView();

		List<Profesor> lProfesor = profesorService.listarProfesores();

		mav.addObject("profesores", lProfesor);
		mav.setViewName("profesor_lista");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/perfil/{id}")
	public ModelAndView perfilProfesor(@PathVariable("id") long idProfesor) {

		ModelAndView mav = new ModelAndView();

		Profesor profesor = profesorService.obtenerProfesor(idProfesor);

		mav.addObject("profesor", profesor);
		mav.setViewName("profesor_perfil");
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/anadir_email/{id}")
	public String anadirEmail(@PathVariable("id") long idProfesor, HttpServletRequest request) {

		String direccion_email = request.getParameter("nuevoemail");

		Email email = new Email();
		email.setDireccionEmail(direccion_email);

		Profesor profesor = profesorService.anadirEmail(idProfesor, email);
		
		return "redirect:/profesor/perfil/"+idProfesor;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/eliminar_email/{idProfesor}/{idEmail}")
	public String eliminarEmail(@PathVariable("idEmail") long idEmail,@PathVariable("idProfesor") long idProfesor) {

		Email email= new Email();
		email.setIdEmail(idEmail);
		
		profesorService.eliminarEmail(idProfesor, email);
		
		return "redirect:/profesor/perfil/"+idProfesor;
	}
}
