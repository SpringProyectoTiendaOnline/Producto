package tiendaOnline.Server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tiendaOnline.DAO.ClienteDAO;
import tiendaOnline.Entity.Cliente;

@Transactional
@Service
public class ClienteServerImpl implements ClienteServer {

	@Autowired
	private ClienteDAO ClienteDao;

	@Transactional
	public Cliente save(Cliente Cliente) {
		Cliente.setTipoCliente(1);
		return ClienteDao.create(Cliente);
	}

	@Transactional
	public Cliente logIn(String email, String password) {
		return ClienteDao.logIn(email, password);
	}

	@Transactional
	public boolean logOut(Cliente cliente) {
		return ClienteDao.logOut(cliente);
	}

	@Transactional
	public Cliente findByEmail(String email) {
		return ClienteDao.findByEmail(email);
	}

	@Override
	public Cliente findById(long id) {
		return ClienteDao.findById(id);
	}

	@Override
	public List<Cliente> getAll() {
		return ClienteDao.getAll();
	}

	@Override
	public Cliente update(Cliente cliente) {
		return ClienteDao.update(cliente);
	}

	@Override
	public void delete(Cliente cliente) {
		ClienteDao.delete(cliente.getIdCliente());
	}

}
