package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="producto")
public class Producto {
	
	@Id
	@GeneratedValue
	@Column(name = "id", nullable = false)
	private long id;
	@Column(name="nombre_producto")
	private String nombre_producto;
	@Column(name="precio")
	private float precio;
	@Column(name="descuento")
	private int descuento;
	@Column(name="descripcion")
	private String descripcion;
	
	

	public Producto(String nombre_producto, float precio, int descuento, String descripcion) {
		super();
		this.nombre_producto = nombre_producto;
		this.precio = precio;
		this.descuento = descuento;
		this.descripcion= descripcion;
	}

	public Producto() {

	}

	public Producto(long id, String nombre, float precio, int descuento, String descripcion) {
		this.nombre_producto = nombre;
		this.id = id;
		this.precio = precio;
		this.descuento = descuento;
		this.descripcion= descripcion;

	}
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre_producto() {
		return nombre_producto;
	}

	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getDescuento() {
		return descuento;
	}

	public void setDescuento(int descuento) {
		this.descuento = descuento;
	}

	

	

	@Override
	public String toString() {
		return "Productos [idProducto=" + id + ", nombreProducto=" + nombre_producto + ", precio=" + precio
				+ ", descuento=" + descuento + "]";
	}

}
