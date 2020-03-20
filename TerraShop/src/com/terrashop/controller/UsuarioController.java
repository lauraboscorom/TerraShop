package com.terrashop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.terrashop.entity.Profesor;
import com.terrashop.service.UsuarioService;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/comprobar_usuario")
	public ModelAndView comprobarUsuario(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		
		String usuario = request.getParameter("usuario");
		String contrasena = request.getParameter("contrasena");
		
		if (usuarioService.comprobarUsuario(usuario,contrasena)) {
			mav.setViewName("correcto");
		} else {
			mav.setViewName("incorrecto");
		}
		return mav;
		
	}

}
