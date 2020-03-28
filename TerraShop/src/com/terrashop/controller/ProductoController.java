package com.terrashop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public ModelAndView listarProductos() {

		ModelAndView mav = new ModelAndView();

		List<Producto> lProductos = productoService.listarProductos();
		
		mav.addObject("productos", lProductos);
		mav.setViewName("productos_lista");
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/eliminar/{id}")
	public String eliminarProducto(@PathVariable("id") long idProducto, HttpServletRequest request) {

		String direccion_email = request.getParameter("nuevoemail");

		productoService.eliminarProducto(idProducto);

		return "redirect:/producto/list";
	}
	
	@PostMapping("/crear")
	public String crearProducto(HttpServletRequest request) {

		String nombre = request.getParameter("nombre");
		float precio = Float.parseFloat(request.getParameter("precio"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		
		Producto p = new Producto();
		p.setNombre(nombre);
		p.setPrecio(precio);
		p.setStock(stock);
		
		System.out.println("Producto " + p.toString());
		productoService.crearProducto(p);

		return "redirect:/producto/list";
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