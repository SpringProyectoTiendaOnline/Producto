package tiendaOnline.Controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tiendaOnline.Entity.Banco;
import tiendaOnline.Entity.Cliente;
import tiendaOnline.Server.*;

@Controller
public class IndexController {

	@Autowired
	private ClienteServer ClienteServer;
	@Autowired
	private BancoServer BancoServer;
	
	public static Cliente cliente;

	public static Cliente getCliente() {
		return cliente;
	}

	public static void setCliente(Cliente cliente) {
		IndexController.cliente = cliente;
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ModelAndView handleLogin(HttpServletRequest request, HttpSession session, Map<String, Object> map) {
		
		ModelAndView mav = new ModelAndView();

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		System.err.println(password + " , " + email);
		if (email.isEmpty() || password.isEmpty()) {
			map.put("msg", "Los campos no pueden ser nulo");
			mav.setViewName("login");
		}

		Cliente cliente = ClienteServer.logIn(email, password);
		if (cliente != null) {
			IndexController.setCliente(cliente);
			if (cliente.getTipoCliente() == 2) {
				List<Cliente> list = ClienteServer.getAll();
				mav.addObject("Cliente", cliente);
				mav.addObject("listaCliente", list);
				mav.setViewName("list-cliente");
			} else {
				List<Banco> listBanco = BancoServer.findByCliente(cliente);
				mav.addObject("listaBanco", listBanco);
				mav.addObject("Cliente", cliente);
				mav.setViewName("profile");
			}

		} else {
			map.put("msg", "Invalid Username o Password");
			mav.setViewName("login");
		}

		return mav;
	}

	@GetMapping("/signup")
	public String showForm(Model theModel) {
		Cliente cliente = new Cliente();
		theModel.addAttribute("Cliente", cliente);
		return "signup";
	}

	@PostMapping("/create-cliente")
	public ModelAndView create_cliente(@ModelAttribute @Valid Cliente cliente, BindingResult bindingResult,
			Map<String, Object> map) {
		ModelAndView mav = new ModelAndView();
		if (bindingResult.hasErrors()) {
			mav.addObject("Cliente", cliente);
			mav.setViewName("signup");
//			String mensaje = inputValid(cliente);
//			map.put("msg", mensaje);
		} else {
			Cliente clienteSave = ClienteServer.save(cliente);
			if (clienteSave != null) {
				mav.addObject("Cliente", clienteSave);
				mav.setViewName("profile");
			}

		}

		return mav;

	}

	

	@GetMapping("/access-denied")
	public String accessDenied() {
		return "/error/access-denied";
	}

//	public String inputValid(Cliente cliente) {
//		String mensaje = "";
//		SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
//		if (cliente.getEmail().length() == 0) {
//			mensaje += "El campo de cliente.getEmail() está vacío. " + "'<br/>'";
//		}
//		if (cliente.getPassword().length() == 0 || cliente.getPassword().isEmpty()) {
//			mensaje += "El campo de contraseña está vacío \r\n";
//		}
//		if (cliente.getDireccion().length() == 0 || cliente.getDireccion().isEmpty()) {
//			mensaje += "El campo de dirección está vacío \r\n";
//		}
//		if (cliente.getNombre().length() == 0 || cliente.getNombre().isEmpty()) {
//			mensaje += "El campo de cliente.getNombre() está vacío \r\n";
//		}
//		if (cliente.getApellido().length() == 0 || cliente.getApellido().isEmpty()) {
//			mensaje += "El campo de cliente.getApellido() está vacío \r\n";
//		}
//		if (cliente.getFnacimiento().length() == 0 || cliente.getFnacimiento().isEmpty()) {
//			mensaje += "El campo de fecha de nacimiento está vacío \r\n";
//		} else {
//			try {
//				Date date = formatter.parse(cliente.getFnacimiento());
//			} catch (ParseException e) {
//				mensaje += "El campo de fecha de nacimiento no es válido \r\n";
//			}
//
//		}
//
//		return mensaje;
//
//	}

}
