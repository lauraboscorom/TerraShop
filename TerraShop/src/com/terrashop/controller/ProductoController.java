package com.terrashop.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.terrashop.entity.LineaDC;
import com.terrashop.entity.Producto;
import com.terrashop.entity.Usuario;
import com.terrashop.entity.Venta;
import com.terrashop.service.ProductoService;
import com.terrashop.service.UsuarioService;
import com.terrashop.service.VentaService;


@Controller
@RequestMapping(value = "/producto")
public class ProductoController {
	
	@Autowired
	ProductoService productoService;
	
	@Autowired
	UsuarioService usuarioService;
	
	@Autowired
	VentaService ventaService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public ModelAndView listarProductos() {

		ModelAndView mav = new ModelAndView();

		List<Producto> lProductos = productoService.listarProductos();
		
		mav.addObject("productos", lProductos);
		mav.setViewName("productos_lista2");
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/eliminar/{id}")
	public String eliminarProducto(@PathVariable("id") long idProducto, HttpServletRequest request) {

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
	
	@RequestMapping("/editar/{id}")
	public String mostrarEditarProducto(@PathVariable("id") Long idProducto, ModelMap model) {
		Producto producto = productoService.obtenerProducto(idProducto);
		model.addAttribute("idProducto", producto.getIdProducto());
		model.addAttribute("nombre", producto.getNombre());
		model.addAttribute("precio",producto.getPrecio());
		model.addAttribute("stock",producto.getStock());
		return "productos_lista :: modalContents";
	}
	
	@PostMapping("/editar/{id}")
	public String editarProducto(@PathVariable("id") Long idProducto, Producto productoFormulario, HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		Producto productoBD = productoService.obtenerProducto(idProducto);
		productoBD.setNombre(productoFormulario.getNombre());
		productoBD.setPrecio(productoFormulario.getPrecio());
		productoBD.setStock(productoFormulario.getStock());
		
		productoService.editarProducto(productoBD);
		
		return "redirect:/producto/list";
	}
	
	@RequestMapping(value=("/comprar/{id}"), method=RequestMethod.POST)
	public String comprarProducto(Model model, @PathVariable("id") Long idProducto, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Long idUsuario = (Long) session.getAttribute("idUsuario");
		
		Usuario usuario = usuarioService.obtenerUsuario(idUsuario);
		Producto producto = productoService.obtenerProducto(idProducto);
		int unidades = Integer.parseInt(request.getParameter("unidades"));

		Venta venta = new Venta();
		Set<LineaDC> lineasDC = new HashSet<>();
		
		venta.setDescuento(0);
		venta.setFechaVenta(new Date());
		venta.setUsuario(usuario);
		Venta ventaCreada = ventaService.crearVenta(venta);
		
		for (int i = 0; i < unidades; i++) {
			LineaDC lineaDC = new LineaDC();
			lineaDC.setProducto(producto);
			lineaDC.setPrecioProducto(producto.getPrecio());
			lineasDC.add(lineaDC);
			producto.addLineaDC(lineaDC);
			ventaCreada.addLineaDC(lineaDC);
		}
		
		producto.setStock(producto.getStock()-unidades);
		productoService.editarProducto(producto);
		
		return "redirect:/producto/list";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/perfil/{id}")
	public ModelAndView mostrarProducto(@PathVariable("id") long idProducto,HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		Producto producto = productoService.obtenerProducto(idProducto);
		
		mav.addObject("producto", producto);
		mav.setViewName("producto_perfil");
		return mav;
	}

}
