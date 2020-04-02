package com.terrashop.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.terrashop.entity.Venta;
import com.terrashop.service.VentaService;

@Controller
@RequestMapping(value = "/venta")
public class VentaController {

	@Autowired
	VentaService ventaService;

	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public ModelAndView listarVentas(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		Long idUsuario = (Long) session.getAttribute("idUsuario");

		List<Venta> lVentas = ventaService.listarVentas(idUsuario);

		mav.addObject("ventas", lVentas);
		mav.setViewName("ventas_lista");
		return mav;
	}

	@GetMapping("/comprobarFecha/{fechaVenta}")
	public boolean comprobarFecha(@PathVariable("id") Date fechaVenta) {
		
		long diferencia;
		try {
			diferencia = new SimpleDateFormat("yyyy-MM-dd").parse("2014-02-14").getTime() - fechaVenta.getTime();
			float diasEntre = (diferencia / (1000*60*60*24));
			
			if (diasEntre <= 15) {
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
		
	}

}
