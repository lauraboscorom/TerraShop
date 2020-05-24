package com.terrashop.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.terrashop.entity.LineaDC;
import com.terrashop.entity.Producto;
import com.terrashop.entity.Rol;
import com.terrashop.entity.Venta;
import com.terrashop.service.LineasDCService;
import com.terrashop.service.ProductoService;
import com.terrashop.service.VentaService;

@Controller
@RequestMapping(value = "/venta")
public class VentaController {

	@Autowired
	VentaService ventaService;

	@Autowired
	ProductoService productoService;
	
	@Autowired
	LineasDCService lineasDCService;

	@RequestMapping(method = RequestMethod.GET, value = "/listUsuario")
	public ModelAndView listarVentasUsuario(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		Long idUsuario = (Long) session.getAttribute("idUsuario");

		List<Venta> lVentas = ventaService.listarVentas(idUsuario);

		mav.addObject("ventas", lVentas);
		mav.setViewName("ventas_lista");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/listAdmin")
	public ModelAndView listarVentasAdmin(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		List<Venta> lVentas = ventaService.listarVentas();

		mav.addObject("ventas", lVentas);
		mav.setViewName("ventas_lista");
		return mav;
	}

	@GetMapping("/eliminar/{idVenta}")
	public String eliminarVenta(@PathVariable("idVenta") long idVenta) {
		
		Venta venta = ventaService.obtenerVenta(idVenta);
		lineasDCService.eliminarLineasDC(venta);
		ventaService.eliminarVenta(idVenta);

		return "redirect:/venta/listAdmin";
	}
	
	@GetMapping("/devolver/{idVenta}")
	public String devolverProducto(@PathVariable("idVenta") long idVenta) {
		
		ventaService.eliminarVenta(idVenta);

		return "redirect:/venta/listUsuario";
	}

}
