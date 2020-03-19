package tiendaOnline.DAO;

import java.util.List;

import tiendaOnline.Entity.Productos;

public interface ProductosDAO extends GenericDao<Productos>{


	public Productos findById(long id);

	public Productos findByCodProducto(long codProducto);

	public List<Productos> getAll();

}
