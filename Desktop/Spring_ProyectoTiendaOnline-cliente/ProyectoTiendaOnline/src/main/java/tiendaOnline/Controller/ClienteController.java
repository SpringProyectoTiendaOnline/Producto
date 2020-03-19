package tiendaOnline.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tiendaOnline.Entity.Banco;
import tiendaOnline.Entity.Cliente;
import tiendaOnline.Server.BancoServer;
import tiendaOnline.Server.ClienteServer;

@Controller
@RequestMapping("/Cliente")
public class ClienteController {
	

	@Autowired
	private ClienteServer ClienteServer;
	@Autowired
	private BancoServer bancoServer;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/profile")
	public ModelAndView profile(Model theModel, @ModelAttribute Cliente cliente) {
		ModelAndView mav = new ModelAndView();
		Cliente clienteObt = ClienteServer.findByEmail(cliente.getEmail());
		System.err.println(clienteObt.toString());
		mav.addObject("Cliente", cliente);
		mav.setViewName("profile");
		return mav;
	}

	@GetMapping("/list")
	public String listUsers(Model theModel) {
		List<Cliente> lCliente = ClienteServer.getAll();
		theModel.addAttribute("listaCliente", lCliente);
		return "list-cliente";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/editar-cliente/{id}")
	public String get_update_cliente(@PathVariable long id, Model model) {
		Cliente cliente = ClienteServer.findById(id);
		model.addAttribute("Cliente", cliente);
		return "update-cliente";

	}

	@RequestMapping(method = RequestMethod.POST, value = "editar-cliente/{id}")
	public ModelAndView post_update_cliente(@PathVariable long id, @ModelAttribute @Valid Cliente cliente,
			BindingResult result, Model themodel) {
		ModelAndView mav = new ModelAndView();

		Cliente clienteOrig = ClienteServer.findById(id);
		if (result.hasFieldErrors()) {
			mav.addObject("Cliente", cliente);
			mav.setViewName("update-cliente");
		} else {
			cliente.setTipoCliente(clienteOrig.getTipoCliente());
			cliente.setIdCliente(id);
			Cliente clienteMod = ClienteServer.update(cliente);
			if (clienteMod != null) {
				List<Banco> listBanco = bancoServer.findByCliente(clienteMod);
				mav.addObject("listaBanco", listBanco);
				mav.addObject("Cliente", clienteMod);
				mav.setViewName("profile");
			}

		}

		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/delete-cliente/{id}")
	public ModelAndView delete_cliente(@PathVariable long id) {
		ModelAndView mav = new ModelAndView();
		Cliente cliente = ClienteServer.findById(id);
		if (cliente != null) {
			List<Banco> listaBanco = bancoServer.findByCliente(cliente);

			if (listaBanco != null) {
				for (int i = 0; i < listaBanco.size(); i++) {
					bancoServer.delete(listaBanco.get(i).getIdBanco());
				}
			}

			ClienteServer.delete(cliente);
			List<Cliente> listaCliente = ClienteServer.getAll();
			mav.addObject("listaCliente", listaCliente);
			mav.setViewName("list-cliente");

		}
		return mav;
	}

}
