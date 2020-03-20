package com.terrashop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.terrashop.entity.Modulo;
import com.terrashop.entity.Profesor;
import com.terrashop.service.ModuloService;

@Controller
@RequestMapping(value = "/modulo")
public class ModuloController {

	@Autowired
	ModuloService moduloService;
	
	
	@RequestMapping(method = RequestMethod.GET,value = "/{id}")
	public ModelAndView perfilProfesor(@PathVariable ("id") long idModulo) {

		ModelAndView mav = new ModelAndView();

		Modulo modulos = moduloService.obtenerModulo(idModulo);
		
		mav.addObject("modulo", modulos);
		mav.setViewName("modulo_perfil");
		return mav;
	}
	
}
