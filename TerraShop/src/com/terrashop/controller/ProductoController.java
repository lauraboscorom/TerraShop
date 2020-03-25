package com.terrashop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.terrashop.entity.Producto;
import com.terrashop.entity.Usuario;
import com.terrashop.service.ProductoService;
import com.terrashop.service.UsuarioService;


@Controller
@RequestMapping(value = "/producto")
public class ProductoController {
	
	@Autowired
	ProductoService productoService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/list/{idUsuario}")
	public ModelAndView listarProductos(@PathVariable("idUsuario") Long idUsuario) {

		ModelAndView mav = new ModelAndView();

		List<Producto> lProductos = productoService.listarProductos();
		Usuario usuario = usuarioService.obtenerUsuario(idUsuario);
		
		mav.addObject("productos", lProductos);
		mav.addObject("usuario", usuario);
		mav.setViewName("productos_lista");
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/comprar/{idUsuario}/{idProducto}/{unidades}")
	public ModelAndView comprarProducto(@PathVariable("idUsuario") Long idUsuario, @PathVariable("idProducto") Long idProducto, @PathVariable("unidades") int unidades) {

		ModelAndView mav = new ModelAndView();
		
		Producto producto = productoService.obtenerProducto(idProducto);
		if (producto.getStock()>=unidades) {
			producto.setStock(producto.getStock()-unidades);
			productoService.editarProducto(producto);
		}
		
		List<Producto> lProductos = productoService.listarProductos();
		Usuario usuario = usuarioService.obtenerUsuario(idUsuario);
		
		mav.addObject("productos", lProductos);
		mav.addObject("usuario", usuario);
		mav.setViewName("productos_lista");
		return mav;
	}

}
