package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Producto;
import com.example.demo.service.ProductoService;

@Controller
@RequestMapping("/product")
public class ProductoController {

	@Autowired
	@Qualifier("productoservice")
	private ProductoService productoService;
	
	@GetMapping("/productsform")
	public String productsForm(Model model,
			@RequestParam(name="id", required =true) long id) {
		
		Producto pr = new Producto();
		if(id!=0) {
			pr= productoService.obtenerProductoPorId(id);
		}
		model.addAttribute("product", pr);
		return "productsform";
	}

	@GetMapping("/list")
	public ModelAndView listAllProductos() {
		ModelAndView mav = new ModelAndView("list");

		mav.addObject("productos", productoService.obtenerProductos());
		mav.addObject("product", new Producto());
		return mav;
	}

	@PostMapping("/addproducts")
	public String addProducto(@ModelAttribute(name = "products") Producto producto) {

		productoService.crearProducto(producto);

		return "redirect:/product/list";

	}
	
	 @GetMapping("/removeproducto")
	public ModelAndView removeProducto (@RequestParam(name="id", required =true) long id) {
		
		productoService.eliminarProducto(id);
		
		return listAllProductos();
		
	}
	 
	
	

}
