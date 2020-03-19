package tiendaOnline.Entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;



import javax.persistence.Table;

@Entity
@Table(name = "Productos", uniqueConstraints = @UniqueConstraint(columnNames = { "idProducto" }))
public class Productos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "idProducto", nullable = false)
	private long idProducto;
	@Column(name = "titulo")
	@NotNull(message = "Debes especificar el título del producto")
	private String titulo;
	@Column(name = "descripcion")
	@NotNull(message = "Debes especificar la descripción del producto")
	private String descripcion;
	@Column(name = "codProducto", unique = true)
	private long codProducto;
	@Column(name = "precio")
	@NotNull(message = "Debes especificar el precio del producto")
	private float precio;
	@Column(name = "descuento")
	@NotNull(message = "Debes especificar el descuento del producto")
	private float descuento;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "CLIENTE_PRODUCTO", joinColumns = @JoinColumn(name = "idProducto"), inverseJoinColumns = @JoinColumn(name = "idCliente"))
	private Set<Cliente> clientes = new HashSet<>();

	public Productos() {

	}

	public Productos(long idProducto, @NotNull(message = "Debes especificar el título del producto") String titulo,
			@NotNull(message = "Debes especificar la descripción del producto") String descripcion,
			@NotNull(message = "Debes especificar el código del producto") long codProducto,
			@NotNull(message = "Debes especificar el precio del producto") float precio,
			@NotNull(message = "Debes especificar el descuento del producto") float descuento) {
		super();
		this.idProducto = idProducto;
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.codProducto = codProducto;
		this.precio = precio;
		this.descuento = descuento;
	}
	public Set<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}

	public long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public long getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(long codProducto) {
		this.codProducto = codProducto;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	@Override
	public String toString() {
		return "Productos [idProducto=" + idProducto + ", titulo=" + titulo + ", descripcion=" + descripcion
				+ ", codProducto=" + codProducto + ", precio=" + precio + ", descuento=" + descuento + "]";
	}

}