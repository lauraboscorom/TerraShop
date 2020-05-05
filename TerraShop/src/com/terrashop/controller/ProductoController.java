package com.terrashop.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;

import com.terrashop.dto.ProductoDto;
import com.terrashop.entity.Imagen;
import com.terrashop.entity.LineaDC;
import com.terrashop.entity.Pregunta;
import com.terrashop.entity.Producto;
import com.terrashop.entity.Respuesta;
import com.terrashop.entity.Usuario;
import com.terrashop.entity.Venta;
import com.terrashop.service.ImagenService;
import com.terrashop.service.PreguntaService;
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
	
	@Autowired
	PreguntaService preguntaService;
	
	@Autowired
	ImagenService imagenService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/list2")
	public ModelAndView listarProductos2() {

		ModelAndView mav = new ModelAndView();

		List<Producto> lProductos = productoService.listarProductos2();

		mav.addObject("productos", lProductos);
		mav.setViewName("productos_lista");
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/buscarProducto")
	public ModelAndView buscarProducto(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		String nombre = request.getParameter("nombre");
		List<Producto> lProductos = productoService.listarProductosPorNombre(nombre);
		
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
		return "producto_perfil :: modalContents";
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
		
		venta.setDescuento(0);
		venta.setFechaVenta(new Date());
		venta.setUsuario(usuario);
		Venta ventaCreada = ventaService.crearVenta(venta);
		
		for (int i = 0; i < unidades; i++) {
			LineaDC lineaDC = new LineaDC();
			lineaDC.setProducto(producto);
			lineaDC.setPrecioProducto(producto.getPrecio());
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

		ProductoDto producto = productoService.recogerProducto(idProducto);
		
		mav.addObject("producto", producto);
		
		mav.setViewName("producto_perfil");
		
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/search/{nombreProducto}")
	public @ResponseBody List<ProductoDto> buscarProductoPorNombre(
			@PathVariable("nombreProducto") String nombreProducto) {
	
		List<ProductoDto> LProductos = productoService.listarProductoPorNombre(nombreProducto);
	
		return LProductos;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/list")
	public ModelAndView listarProductos() {

		ModelAndView mav = new ModelAndView();
		
		List<ProductoDto> lProductos = productoService.listarProductos();
	
		mav.addObject("productos", lProductos);
		mav.setViewName("productos_lista");
		return mav;
	}
	
	@RequestMapping(value=("/enviarPregunta/{id}"), method=RequestMethod.POST)
	public String enviarPregunta(Model model, @PathVariable("id") Long idProducto, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Long idUsuario = (Long) session.getAttribute("idUsuario");
		
		Usuario usuario = usuarioService.obtenerUsuario(idUsuario);
		Producto producto = productoService.obtenerProducto(idProducto);
		String texto = request.getParameter("texto"); 

		Pregunta pregunta = new Pregunta();
		pregunta.setProducto(producto);
		pregunta.setUsuario(usuario);
		pregunta.setTexto(texto);
		
		producto.addPregunta(pregunta);

		productoService.editarProducto(producto);
		
		return "redirect:/producto/perfil/"+idProducto;
	}
	
	@RequestMapping(value=("/enviarRespuesta/{idProducto}/{idPregunta}"), method=RequestMethod.POST)
	public String enviarRespuesta(Model model, @PathVariable("idProducto") Long idProducto, @PathVariable("idPregunta") Long idPregunta, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Long idUsuario = (Long) session.getAttribute("idUsuario");
		
		Usuario usuario = usuarioService.obtenerUsuario(idUsuario);
		Pregunta pregunta = preguntaService.obtenerPregunta(idPregunta);
		String texto = request.getParameter("texto"); 

		Respuesta respuesta = new Respuesta();
		respuesta.setPregunta(pregunta);
		respuesta.setUsuario(usuario);
		respuesta.setTexto(texto);
		
		pregunta.addRespuesta(respuesta);

		preguntaService.editarPregunta(pregunta);
		
		return "redirect:/producto/perfil/"+idProducto;
	}
	
	@PostMapping("/fileupload/{id}")
    public String fileUpload(@RequestParam("file") MultipartFile file, @PathVariable("id") Long idProducto) {
		try {
            byte[] data = file.getBytes();
            Producto producto = productoService.obtenerProducto(idProducto);
            
            Imagen imagen = new Imagen();            
            imagen.setData(data);
            imagen.setProducto(producto);
            
            imagenService.crearImagen(imagen);
            
            return "redirect:/producto/perfil/"+idProducto;
        } catch (Exception e) {
            return "error";
        }
    }
	
	@RequestMapping(value = "/imagen/{id}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity getImageAsResponseEntity(@PathVariable("id") Long idImagen) {

        try {
            Imagen imagesObj = imagenService.getImagen(idImagen);
            byte[] media = imagesObj.getData();
            HttpHeaders headers = new HttpHeaders();
            headers.setCacheControl(CacheControl.noCache().getHeaderValue());

            ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, headers, HttpStatus.OK);
            return responseEntity;

        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }
	
}
