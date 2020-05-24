package com.terrashop.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import com.terrashop.entity.Categoria;
import com.terrashop.entity.Imagen;
import com.terrashop.entity.LineaDC;
import com.terrashop.entity.Pregunta;
import com.terrashop.entity.Producto;
import com.terrashop.entity.Puntuacion;
import com.terrashop.entity.Respuesta;
import com.terrashop.entity.Usuario;
import com.terrashop.entity.Venta;
import com.terrashop.service.CategoriaService;
import com.terrashop.service.ImagenService;
import com.terrashop.service.PreguntaService;
import com.terrashop.service.ProductoService;
import com.terrashop.service.PuntuacionService;
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
	
	@Autowired
	PuntuacionService puntuacionService;

	@Autowired
	CategoriaService categoriaService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/buscarProducto")
	public ModelAndView buscarProducto(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		String nombre = request.getParameter("nombre");
		List<Producto> lProductos = productoService.listarProductosPorNombre(nombre);
		
		mav.addObject("productos", lProductos);
		mav.setViewName("productos_lista");
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/eliminar/{id}")
	public String eliminarProducto(@PathVariable("id") Long idProducto, HttpServletRequest request) {

		productoService.eliminarProducto(idProducto);

		return "redirect:/producto/list";
	}
	
	@PostMapping("/crear")
	public String crearProducto(HttpServletRequest request) {

		String nombre = request.getParameter("nombre");
		float precio = Float.parseFloat(request.getParameter("precio"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		String categoria = request.getParameter("categoria");
		
		Categoria c = categoriaService.obtenerProductoPorNombre(categoria);
		
		Producto p = new Producto();
		p.setNombre(nombre);
		p.setPrecio(precio);
		p.setStock(stock);
		p.setCategoria(c);
		
		productoService.crearProducto(p);

		return "redirect:/producto/list";
	}
	
	@RequestMapping("/mostrarEditar/{id}")
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

		Producto productoBD = productoService.obtenerProducto(idProducto);
		productoBD.setNombre(productoFormulario.getNombre());
		productoBD.setPrecio(productoFormulario.getPrecio());
		productoBD.setStock(productoFormulario.getStock());
		
		productoService.editarProducto(productoBD);

		return "redirect:/producto/perfil/"+idProducto;
		
	}
	
	@RequestMapping(value=("/comprar/{id}"), method=RequestMethod.POST)
	public @ResponseBody ResponseEntity comprarProducto(Model model, @PathVariable("id") Long idProducto, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Long idUsuario = (Long) session.getAttribute("idUsuario");
		
		Producto producto = productoService.obtenerProducto(idProducto);
		int unidades = Integer.parseInt(request.getParameter("unidades"));
		
		if(unidades>producto.getStock()) {
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		} else {
		
			Usuario usuario = usuarioService.obtenerUsuario(idUsuario);
	
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
			return new ResponseEntity(HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/perfil/{id}")
	public ModelAndView mostrarProducto(@PathVariable("id") Long idProducto,HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();

		ProductoDto producto = productoService.recogerProducto(idProducto);
		
		//Get media de puntuaciones
		int media = 0;
		List<Puntuacion> LPuntuaciones = puntuacionService.listarPuntuacionPorProducto(producto);
		
		if(LPuntuaciones.size()>0) {
			int cincoEstrellas=0;
			int cuatroEstrellas=0;
			int tresEstrellas=0;
			int dosEstrellas=0;
			int unaEstrella=0;
			for (int i = 0; i < LPuntuaciones.size(); i++) {
				switch (LPuntuaciones.get(i).getValor()) {
				case 5:
					cincoEstrellas += 1;
					break;
				case 4:
					cuatroEstrellas += 1;
					break;
				case 3:
					tresEstrellas += 1;
					break;
				case 2:
					dosEstrellas += 1;
					break;
				case 1:
					unaEstrella += 1;
					break;
				default:
					break;
				}
			}
			
			int f1 = 5*cincoEstrellas + 4*cuatroEstrellas + 3*tresEstrellas + 2*dosEstrellas + 1*unaEstrella;
			media = f1/LPuntuaciones.size();
		
		}
		mav.addObject("producto", producto);
		
		mav.setViewName("producto_perfil");
		
		mav.addObject("media",media);
		
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/perfilAjax/{id}")
	public String perfilAjax(@PathVariable("id") Long idProducto,HttpServletRequest request) {
		return "redirect:/producto/perfil/" + idProducto;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/search/{nombreProducto}")
	public @ResponseBody List<ProductoDto> buscarProductoPorNombre(
			@PathVariable("nombreProducto") String nombreProducto) {
	
		List<ProductoDto> LProductos = productoService.listarProductoPorNombre(nombreProducto);
	
		return LProductos;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/list")
    public String listarProductos(Model model, @RequestParam("page") Optional<Integer> page) {
		List<Categoria> lCategorias = categoriaService.listarCategorias();
		model.addAttribute("categorias", lCategorias);
		 
        int currentPage = page.orElse(1);
        int pageSize = 4;
 
        Page<ProductoDto> productoPage = productoService.findPaginated(PageRequest.of(currentPage - 1, pageSize));
 
        model.addAttribute("productoPage", productoPage);
 
        int totalPages = productoPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
 
        return "productos_lista.html";
    }
	
	@RequestMapping(method = RequestMethod.GET, value = "/list/{idCategoria}")
    public String listarProductosPorCategoria2(Model model, @RequestParam("page") Optional<Integer> page, @PathVariable("idCategoria") Long idCategoria) {
		List<Categoria> lCategorias = categoriaService.listarCategorias();
		model.addAttribute("categorias", lCategorias);
		 
        int currentPage = page.orElse(1);
        int pageSize = 4;
 
        Categoria categoria = categoriaService.obtenerCategoria(idCategoria);
        Page<ProductoDto> productoPage = productoService.findPaginatedByCategory(PageRequest.of(currentPage - 1, pageSize),categoria);
 
        model.addAttribute("productoPage", productoPage);
 
        int totalPages = productoPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
 
        return "productos_lista.html";
    }
	
	@RequestMapping(method = RequestMethod.GET, value = "/list2/{idCategoria}")
	public ModelAndView listarProductosPorCategoria(@PathVariable("idCategoria") Long idCategoria) {

		ModelAndView mav = new ModelAndView();
		
		Categoria categoria = categoriaService.obtenerCategoria(idCategoria);
		List<ProductoDto> lProductos = productoService.listarProductosPorCategoria(categoria);
		List<Categoria> lCategorias = categoriaService.listarCategorias();
		int numPaginas = (int)lProductos.size()/4;
		if(lProductos.size()%4 != 0) { numPaginas += 1; }
		
		mav.addObject("paginas",numPaginas);
		mav.addObject("productos", lProductos);
		mav.addObject("categorias", lCategorias);
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
	
	@RequestMapping(value=("/enviarPuntuacion/{id}/{valorPuntuacion}"), method=RequestMethod.POST)
	public String enviarPuntuacion(Model model, @PathVariable("id") Long idProducto, @PathVariable("valorPuntuacion") int valorPuntuacion, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		Long idUsuario = (Long) session.getAttribute("idUsuario");
		
		Usuario usuario = usuarioService.obtenerUsuario(idUsuario);
		Producto producto = productoService.obtenerProducto(idProducto);

		Puntuacion puntuacion = new Puntuacion();
		puntuacion.setProducto(producto);
		puntuacion.setUsuario(usuario);
		puntuacion.setValor(valorPuntuacion);
		
		puntuacionService.crearPuntuacion(puntuacion);
		
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
