package tiendaOnline.Server;

import java.util.List;

import tiendaOnline.Entity.Cliente;

public interface ClienteServer {

	public Cliente save(Cliente Cliente);

	public Cliente logIn(String email, String password);

	public boolean logOut(Cliente cliente);

	public Cliente findByEmail(String email);

	public Cliente findById(long id);

	public List<Cliente> getAll();

	public Cliente update(Cliente cliente);

	public void delete(Cliente cliente);

}
