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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import tiendaOnline.Entity.Banco;
import tiendaOnline.Entity.Cliente;
import tiendaOnline.Server.BancoServer;
import tiendaOnline.Server.ClienteServer;

@Controller
@RequestMapping("/Banco")
public class BancoController {
	@Autowired
	private ClienteServer ClienteServer;
	@Autowired
	private BancoServer bancoServer;

	@GetMapping("/create-banco/{id}")
	public String banco_showFrom(@PathVariable long id, Model theModel) {
		Banco banco = new Banco();
		banco.setCliente(ClienteServer.findById(id));
		theModel.addAttribute("Banco", banco);
		return "signup-banco";

	}

	@PostMapping("create-banco/{id}")
	public ModelAndView create_banco(@PathVariable("id") long id, @ModelAttribute @Valid Banco banco,
			BindingResult result) {
		ModelAndView mav = new ModelAndView();
		Cliente cliente = ClienteServer.findById(id);
		

		if (!result.hasFieldErrors()) {
			if (banco != null) {
				banco.setCliente(cliente);
				System.err.println(banco.toString());
				Banco bancoGuard = bancoServer.save(banco);
				if (bancoGuard != null) {
					System.out.println("Banco : Guardado");
					mav.addObject("Cliente", cliente);
					List<Banco> listaBanco = bancoServer.findByCliente(cliente);
					mav.addObject("listaBanco", listaBanco);
					mav.setViewName("profile");
				} else {
					System.out.println("Banco : No ha sido Guardado");
					mav.setViewName("signup-banco");

				}
			}
		} else {
			mav.setViewName("signup-banco");
		}
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/editar-banco/{id}")
	public ModelAndView get_update_banco(@PathVariable long id) {
		Banco banco = bancoServer.findById(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("Banco", banco);
		mav.setViewName("update-banco");
		return mav;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/delete-banco/{id}")
	public ModelAndView delete_banco(@PathVariable long id) {
		ModelAndView mav = new ModelAndView();
		Banco banco = bancoServer.findById(id);
		Cliente cliente = banco.getCliente();
		if (banco != null) {
			bancoServer.delete(banco.getIdBanco());
			List<Banco> listaBanco = bancoServer.getAll();
			mav.addObject("listaBanco", listaBanco);
			mav.addObject("Cliente", cliente);
			mav.setViewName("profile");
		}
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST, value = "editar-banco/{id}")
	public ModelAndView post_update_banco(@PathVariable long id, @ModelAttribute @Valid Banco banco,
			BindingResult result) {
		ModelAndView mav = new ModelAndView();
		Banco bancoO = bancoServer.findById(id);
		if (result.hasFieldErrors()) {
			mav.addObject("Banco", bancoO);
			mav.setViewName("update-banco");
		} else {
			banco.setIdBanco(id);
			banco.setCliente(bancoO.getCliente());
			Banco bancoMod = bancoServer.update(banco);
			if (bancoMod != null) {
				List<Banco> listaBanco = bancoServer.findByCliente(bancoMod.getCliente());
				mav.addObject("listaBanco", listaBanco);
				mav.addObject("Cliente", bancoMod.getCliente());
				mav.setViewName("profile");
			}

		}

		return mav;
	}

}
