package tiendaOnline.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import tiendaOnline.Entity.Productos;

@Repository
@Component("ProductoDAO")
public class ProductosDAOImpl extends GenericDaoImpl<Productos> implements ProductosDAO {

	@Override
	public Productos findById(long id) {
		try {
			Query query = this.em.createQuery("Select p From Productos p Where idProducto =:id");
			query.setParameter("id", id);
			Productos producto = (Productos) query.getSingleResult();
			if (producto != null) {
				return producto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Productos findByCodProducto(long codProducto) {
		try {
			Query query = this.em.createQuery("Select p From Productos p Where codigoProducto =:codProducto");
			query.setParameter("codProducto", codProducto);
			Productos producto = (Productos) query.getSingleResult();
			if (producto != null) {
				return producto;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Productos> getAll() {
		List<Productos> listaProducto = new ArrayList<Productos>();

		try {
			listaProducto = this.em.createQuery("Select p From Productos p").getResultList();
			if (listaProducto != null) {
				return listaProducto;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
