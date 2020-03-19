package tiendaOnline.Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.mapping.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import tiendaOnline.Entity.Cliente;
import tiendaOnline.Entity.Productos;
import tiendaOnline.Server.ProductoServer;

@Controller
@RequestMapping("/Producto")
public class ProductoController {

	@Autowired
	private ProductoServer productoServer;
	
	@GetMapping("/add-producto")
	public String productsForm(Model model) {
		Productos producto = new Productos();
		model.addAttribute("products", producto);
		return "add-producto";
	}
	@GetMapping("/list-product-user")
	public ModelAndView listAllProductosForClients() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("listaProductos", productoServer.getAll());
		mav.setViewName("list-product-user");
		return mav;
	}
	
	@PostMapping("/create-productos")
	public ModelAndView addProducto(@ModelAttribute @Valid Productos producto, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		System.err.println(producto);
		if (bindingResult.hasFieldErrors()) {
			mav.addObject("products", producto);
			mav.setViewName("add-producto");
		} else {
			Productos productoSave = productoServer.save(producto);
			if (productoSave != null) {
				List<Productos> listaProducto = productoServer.getAll();
				mav.addObject("listaProductos", listaProducto);
				mav.setViewName("list-producto");
			}

		}
		return mav;
	}
	
	
	@GetMapping("/editar-producto/{id}")
	public String update_producto(@PathVariable("id") long id,Model model) {
		model.addAttribute("products", productoServer.findById(id));
		return "update-producto";
	}
	
	@PostMapping("editar-producto/{id}")
	public ModelAndView update_producto_post(@PathVariable("id") long id, @ModelAttribute @Valid Productos producto, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView();
		System.err.println(producto);
		producto.setIdProducto(id);
		if (bindingResult.hasFieldErrors()) {
			mav.addObject("products", producto);
			mav.setViewName("update-producto");
		} else {
			Productos productoSave = productoServer.update(producto);
			if (productoSave != null) {
				List<Productos> listaProducto = productoServer.getAll();
				mav.addObject("listaProductos", listaProducto);
				mav.setViewName("list-producto");
			}

		}
		return mav;
	}
	
	
	

	@GetMapping("/list-producto")
	public ModelAndView listAllProductos() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("listaProductos", productoServer.getAll());
		mav.setViewName("list-producto");
		return mav;
	}

	

	@GetMapping("/removeproducto")
	public ModelAndView removeProducto(@RequestParam(name = "id", required = true) long id) {
		Productos producto = productoServer.findById(id);
		productoServer.delete(producto);
		
		return listAllProductos();

	}
	
	@GetMapping("/comprarproducto")
	public ModelAndView comprarProducto(@RequestParam(name = "id", required = true) long id) {
		Productos producto = productoServer.findById(id);
		HashSet<Cliente> listaCliente= new HashSet<Cliente>();
		Cliente cliente= IndexController.getCliente();
		listaCliente.add(cliente);
		producto.setClientes(listaCliente);
		productoServer.update(producto);
		return listAllProductos();

	}
	

}
