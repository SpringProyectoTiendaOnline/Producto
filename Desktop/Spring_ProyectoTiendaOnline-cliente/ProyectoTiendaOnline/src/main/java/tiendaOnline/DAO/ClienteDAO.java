package tiendaOnline.DAO;



import java.util.List;

import tiendaOnline.Entity.Cliente;

public interface ClienteDAO extends GenericDao<Cliente>{
	
	public Cliente logIn(String email, String password);

	public boolean logOut(Cliente cliente);

	public Cliente findByEmail(String email);

	public List<Cliente> getAll();
	
	public Cliente findById(long id);

	
//	public Cliente save(Cliente Cliente);
//
//	public Cliente logIn(String email,String password);
//
//	public boolean logOut(Cliente cliente);
//	
//	public Cliente update(Cliente cliente);
//	
//	public boolean delete(Cliente cliente);
//	
//	public List<Cliente> getAll();
//	
//	public Cliente findByEmail(String email);
//
//	public Cliente findById(long id);

}
