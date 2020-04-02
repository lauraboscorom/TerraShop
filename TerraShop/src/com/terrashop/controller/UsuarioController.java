package com.terrashop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.terrashop.entity.Usuario;
import com.terrashop.service.UsuarioService;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/myprofile")
	public String perfilPersonal(HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (session != null && session.getAttribute("idUsuario") != null)
        	return "redirect:/usuario/perfil/" + session.getAttribute("idUsuario");
        else 
        	return"redirect:/";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/perfil/{id}")
	public ModelAndView perfilUsuario(@PathVariable("id") long idUsuario,HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		Usuario usuario = usuarioService.obtenerUsuario(idUsuario);

		long idSession= (long) request.getSession().getAttribute("idUsuario");

		Boolean propietario= idSession == idUsuario;

		mav.addObject("propietario", propietario);
		mav.addObject("usuario", usuario);
		mav.setViewName("usuario_perfil");
		return mav;
	}
	
	@GetMapping("/perfil/editar/{id}")
	public ModelAndView mostrarEditarPerfilUsuario(@PathVariable("id") long idUsuario,HttpServletRequest request) {

		Usuario usuario = usuarioService.obtenerUsuario(idUsuario);

		ModelAndView mav = new ModelAndView();
		mav.addObject("usuario", usuario);
		mav.setViewName("usuario_perfil_editar");
		return mav;
	}
	
	@PostMapping("/perfil/editar/{id}")
	public String editarPerfilUsuario(@PathVariable("id") long idUsuario,@Valid Usuario usuarioFormulario,BindingResult bindingResult, HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		long idUsuarioSession= (long) request.getSession().getAttribute("idUsuario");
		if (idUsuarioSession != idUsuario) {
			return "redirect:/index";
		}
		
		if (bindingResult.hasErrors()) {
			return "usuario_perfil_editar";
		}

		Usuario usuarioBD = usuarioService.obtenerUsuario(idUsuario);
		usuarioBD.setUsuario(usuarioFormulario.getUsuario());
		usuarioBD.setNombre(usuarioFormulario.getNombre());
		usuarioBD.setApellidos(usuarioFormulario.getApellidos());
		usuarioBD.setEmail(usuarioFormulario.getEmail());
		usuarioBD.setDireccionEnvio(usuarioFormulario.getDireccionEnvio());
		usuarioBD.setBanco(usuarioFormulario.getBanco());
		usuarioBD.setNumeroTarjeta(usuarioFormulario.getNumeroTarjeta());
		usuarioBD.setTitular(usuarioFormulario.getTitular());
		usuarioBD.setCodigoSeguridad(usuarioFormulario.getCodigoSeguridad());
		usuarioBD.setDireccionFacturacion(usuarioFormulario.getDireccionFacturacion());
		
		usuarioService.editarUsuario(usuarioBD);
		
		return "redirect:/usuario/perfil/" + idUsuarioSession;
	}

}
