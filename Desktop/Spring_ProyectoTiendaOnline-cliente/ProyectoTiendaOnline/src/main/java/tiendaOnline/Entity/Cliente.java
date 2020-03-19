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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;




import javax.persistence.GenerationType;

@Entity
@Table(name = "Cliente", uniqueConstraints = @UniqueConstraint(columnNames = { "idCliente" }))
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCliente", nullable = false, unique = true)
	private long idCliente;
	@Column(name = "nombre")
	@NotNull(message = "Debes especificar el nombre")
	private String nombre;
	@Column(name = "apellido")
	@NotNull(message = "Debes especificar el apellido")
	private String apellido;
	@Column(name = "email")
	@NotNull(message = "Debes especificar el email")
	private String email;
	@Column(name = "fnacimiento")
	@NotNull(message = "Debes especificar la fecha de nacimiento")
	private String fnacimiento;
	@Column(name = "password")
	@NotNull(message = "Debes especificar la contraseña")
	private String password;
	@Column(name = "direccion")
	@NotNull(message = "Debes especificar la dirección")
	private String direccion;
	@Column(name = "tipoCliente")
	private int tipoCliente;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "CLIENTE_PRODUCTO", 
	joinColumns = @JoinColumn(name = "idCliente"),
	inverseJoinColumns = @JoinColumn(name = "idProducto"))
	private Set<Productos> producto = new HashSet<>();

	
	public Cliente() {

	}

	public Cliente(String nombre, String apellido, String email, String fnacimiento, String password,
			String direccion) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fnacimiento = fnacimiento;
		this.password = password;
		this.direccion = direccion;
	}
	public Set<Productos> getProducto() {
		return producto;
	}

	public void setProducto(Set<Productos> producto) {
		this.producto = producto;
	}


	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFnacimiento() {
		return fnacimiento;
	}

	public void setFnacimiento(String fnacimiento) {
		this.fnacimiento = fnacimiento;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(int tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nombre=" + nombre + ", apellido=" + apellido + ", email=" + email
				+ ", fnacimiento=" + fnacimiento + ", password=" + password + ", direccion=" + direccion
				+ ", tipoCliente= " + tipoCliente + " ]";
	}

}
