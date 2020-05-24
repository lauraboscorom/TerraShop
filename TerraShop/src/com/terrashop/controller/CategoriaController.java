package com.terrashop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.terrashop.entity.Categoria;
import com.terrashop.entity.Producto;
import com.terrashop.service.CategoriaService;

@Controller
@RequestMapping(value = "/categoria")
public class CategoriaController {
	
	@Autowired
	CategoriaService categoriaService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/eliminar/{idCategoria}")
	public String eliminarProducto(@PathVariable("idCategoria") Long idCategoria, HttpServletRequest request) {

		categoriaService.eliminarCategoria(idCategoria);

		return "redirect:/producto/list";
	}
	
	@PostMapping("/crear")
	public String crearCategoria(HttpServletRequest request) {

		String nombre = request.getParameter("nombre");
		
		Categoria c = new Categoria();
		c.setNombre(nombre);
		
		categoriaService.crearCategoria(c);

		return "redirect:/producto/list";
	}

}
