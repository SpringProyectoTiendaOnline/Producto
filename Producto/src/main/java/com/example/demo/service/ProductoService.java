package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Producto;

public interface ProductoService {
	
	public abstract Producto crearProducto(Producto producto);

	public abstract Producto obtenerProductoPorId(long id);
	
	public abstract void eliminarProducto(long id);

	public abstract List<Producto> obtenerProductos();

	//public Producto editarProducto(Producto producto);

	

	//public List<Producto> obtenerProductosNombre(String nombre, int count, int index);

	//public List<Producto> obtenerProductosPorPrecio(float minPrecio, float maxPrecio, int count, int index);

	//public List<Producto> obtenerProductosPorNombreYPrecio(String nombre, float minPrecio, float maxPrecio,
			 //int count, int index);


}
