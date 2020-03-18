package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Producto;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.ProductoService;

@Service("productoservice")
public class ProductoServiceImpl implements ProductoService {
	
	@Autowired
	@Qualifier("productorepository")
	private ProductoRepository productoRepository;

	@Override
	public Producto crearProducto(Producto producto) {
		// TODO Auto-generated method stub
		return productoRepository.save(producto);
	}
	
	@Override
	public List<Producto> obtenerProductos() {
		// TODO Auto-generated method stub
		return productoRepository.findAll();
	}


	 @Override
	public Producto obtenerProductoPorId(long id) {
		// TODO Auto-generated method stub
		return productoRepository.findById(id);
	}

	@Override
	public void eliminarProducto(long id) {
		// TODO Auto-generated method stub
		Producto pr= obtenerProductoPorId(id);
		if(null!=pr) {
			productoRepository.delete(pr);
		}
	}

	 
	/*
	 * @Override
	public Producto obtenerProducto(long idProducto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Producto editarProducto(Producto producto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminarProducto(long idProducto) {
		// TODO Auto-generated method stub
		return false;
	}
	 */



	/*
	 * @Override
	public List<Producto> obtenerProductosNombre(String nombre, int count, int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> obtenerProductosPorPrecio(float minPrecio, float maxPrecio, int count, int index) {
		// TODO Auto-generated method stub
		return null;
	}
	 */

	/*
	 * @Override
	public List<Producto> obtenerProductosPorNombreYPrecio(String nombre, float minPrecio, float maxPrecio, int count,
			int index) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 */
	

}
