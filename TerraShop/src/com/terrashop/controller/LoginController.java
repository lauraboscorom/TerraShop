package com.terrashop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.terrashop.entity.Usuario;
import com.terrashop.service.UsuarioService;

@Controller
public class LoginController {

	@Autowired
	UsuarioService usuarioService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/access-denied")
	public String accessDenied() {
		return "/error/access-denied";
	}

	@PostMapping("/crearUsuario")
	public String createUser(@ModelAttribute("usuario") Usuario elUsuario) {
		Usuario usuario = usuarioService.crearUsuario(elUsuario);

		return "redirect:/index";
	}

	@GetMapping("/signup")
	public String showForm() {
		return "signup";
	}

	@PostMapping("/signup")
	public String crearUsuario(HttpServletRequest request) {

		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		String nombre = request.getParameter("nombre");
		String apellidos = request.getParameter("apellidos");
		String email = request.getParameter("email");
		String direccionEnvio = request.getParameter("direccionEnvio");
		String banco = request.getParameter("banco");
		int numeroTarjeta = Integer.parseInt(request.getParameter("numeroTarjeta"));
		String titular = request.getParameter("titular");
		int codigoSeguridad = Integer.parseInt(request.getParameter("codigoSeguridad"));
		String direccionFacturacion = request.getParameter("direccionFacturacion");
		
		Usuario u = new Usuario();
		u.setUsuario(usuario);
		u.setPassword(password);
		u.setNombre(nombre);
		u.setApellidos(apellidos);
		u.setEmail(email);
		u.setDireccionEnvio(direccionEnvio);
		u.setBanco(banco);
		u.setNumeroTarjeta(numeroTarjeta);
		u.setTitular(titular);
		u.setCodigoSeguridad(codigoSeguridad);
		u.setDireccionFacturacion(direccionFacturacion);
		
		System.out.println("Usuario " + u.toString());
		usuarioService.crearUsuario(u);

		return "redirect:/";
	}

}