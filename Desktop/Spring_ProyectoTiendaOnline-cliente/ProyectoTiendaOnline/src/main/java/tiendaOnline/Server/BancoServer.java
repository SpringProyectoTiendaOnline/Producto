package tiendaOnline.Server;

import java.util.List;

import tiendaOnline.Entity.Banco;
import tiendaOnline.Entity.Cliente;


public interface BancoServer {

	public Banco save(Banco banco);

	public void delete(long id);

	public Banco update(Banco banco);
	
	public List<Banco> getAll();
	
	public List<Banco> findByCliente(Cliente cliente);
	
	public Banco findById(long id);


}
