package com.terrashop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.terrashop.entity.Profesor;
import com.terrashop.entity.Usuario;
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
			Usuario usuarioIntroducido = usuarioService.buscarUsuarioPorUsuario(usuario);
			
			mav.addObject("usuario", usuarioIntroducido);
			mav.setViewName("correcto");
		} else {
			mav.setViewName("usuario_perfil");
		}
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/signup")
	public ModelAndView mostrarFormularioRegistro() {

		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("signup");
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/crear_usuario")
	public ModelAndView crearUsuario(HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		
		String usuario = request.getParameter("usuario");
		String contrasena = request.getParameter("contrasena");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String email = request.getParameter("email");
		String direccionEnvio = request.getParameter("direccionEnvio");
		String banco = request.getParameter("banco");
		Long numeroTarjeta = Long.parseLong(request.getParameter("numeroTarjeta"));
		String titular = request.getParameter("titular");
		Long codigoSeguridad = Long.parseLong(request.getParameter("codigoSeguridad"));
		String direccionFacturacion = request.getParameter("direccionFacturacion");
		
		Usuario nuevoUsuario = new Usuario(nombre, apellidos, email, direccionEnvio, banco, numeroTarjeta, titular, codigoSeguridad, direccionFacturacion, usuario, contrasena);
		usuarioService.crearUsuario(nuevoUsuario);
		
		mav.setViewName("index");
		return mav;
	}

}