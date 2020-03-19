package tiendaOnline.Server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tiendaOnline.DAO.*;
import tiendaOnline.Entity.*;

@Service
public class ProductoServerImpl implements ProductoServer {

	@Autowired
	private ProductosDAO productoDAO;

	@Override
	@Transactional
	public Productos save(Productos producto) {
		return productoDAO.create(producto);
	}

	@Override
	@Transactional
	public Productos findById(long id) {
		return productoDAO.find(id);

	}

	@Override
	@Transactional
	public void delete(Productos producto) {
		productoDAO.delete(producto.getIdProducto());
	}

	@Override
	@Transactional
	public List<Productos> getAll() {
		return productoDAO.getAll();
	}

	@Override
	@Transactional
	public Productos findByCodProducto(long codProducto) {
		return productoDAO.findByCodProducto(codProducto);
	}

	@Override
	@Transactional
	public Productos update(Productos producto) {
		return productoDAO.update(producto);
	}
}