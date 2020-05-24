package com.terrashop.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import java.util.HashSet;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

	private static final long serialVersionUID = -8668594760203621162L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_USUARIO")
	private Long idUsuario;

	@NotNull @Size(min=4, max=15)
	@Column(name = "NOMBRE")
	private String nombre;

	@NotNull @Size(min=4, max=15)
	@Column(name = "APELLIDOS")
	private String apellidos;
	
	@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"+"(?:[a-z0-9](?:[a-z0-9-]*"+
	        "[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",message="La dirección debe tener la forma email@email.com")
	@Column(name = "EMAIL")
	private String email;

	@NotNull @Size(min=4, max=30)
	@Column(name = "DIRECCION_ENVIO")
	private String direccionEnvio;

	@NotNull @Size(min=3, max=10)
	@Column(name = "BANCO")
	private String banco;
	
	@NotNull
	@Column(name = "NUMERO_TARJETA")
	private int numeroTarjeta;

	@NotNull @Size(min=6, max=30)
	@Column(name = "TITULAR")
	private String titular;
	
	@NotNull
	@Column(name = "CODIGO_SEGURIDAD")
	private int codigoSeguridad;

	@NotNull @Size(min=4, max=30)
	@Column(name = "DIRECCION_FACTURACION")
	private String direccionFacturacion;

	@NotNull @Size(min=6, max=15)
	@Column(name = "USUARIO")
	private String usuario;
	
	@NotNull
	@Column(name = "PASSWORD")
	private String password;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Venta> ventas = new HashSet<>();
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Pregunta> preguntas = new HashSet<>();
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Puntuacion> puntuaciones = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USUARIO_ROL", 
	joinColumns = @JoinColumn(name = "ID_USUARIO"),
	inverseJoinColumns = @JoinColumn(name = "ID_ROL"))
	private Set<Rol> roles = new HashSet<>();

	public Usuario() {
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccionEnvio() {
		return direccionEnvio;
	}

	public void setDireccionEnvio(String direccionEnvio) {
		this.direccionEnvio = direccionEnvio;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public int getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(int numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public int getCodigoSeguridad() {
		return codigoSeguridad;
	}

	public void setCodigoSeguridad(int codigoSeguridad) {
		this.codigoSeguridad = codigoSeguridad;
	}

	public String getDireccionFacturacion() {
		return direccionFacturacion;
	}

	public void setDireccionFacturacion(String direccionFacturacion) {
		this.direccionFacturacion = direccionFacturacion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(Set<Venta> ventas) {
		this.ventas = ventas;
	}
	
	public boolean addVenta(Venta venta) {
		venta.setUsuario(this);
		return getVentas().add(venta);
	}

	public void removeLineaDC(Venta venta) {
		getVentas().remove(venta);
	}

	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	public Set<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(Set<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	
	public boolean addPregunta(Pregunta pregunta) {
		pregunta.setUsuario(this);
		return getPreguntas().add(pregunta);
	}

	public void removePregunta(Pregunta pregunta) {
		getPreguntas().remove(pregunta);
	}

	public Set<Puntuacion> getPuntuaciones() {
		return puntuaciones;
	}

	public void setPuntuaciones(Set<Puntuacion> puntuaciones) {
		this.puntuaciones = puntuaciones;
	}
	
	public boolean addPuntuacion(Puntuacion puntuacion) {
		puntuacion.setUsuario(this);
		return getPuntuaciones().add(puntuacion);
	}

	public void removePuntuacion(Puntuacion puntuacion) {
		getPuntuaciones().remove(puntuacion);
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellidos=" + apellidos + ", email="
				+ email + ", direccionEnvio=" + direccionEnvio + ", banco=" + banco + ", numeroTarjeta=" + numeroTarjeta
				+ ", titular=" + titular + ", codigoSeguridad=" + codigoSeguridad + ", direccionFacturacion="
				+ direccionFacturacion + ", usuario=" + usuario + ", password=" + password + ", ventas=" + ventas
				+ ", roles=" + roles + "]";
	}
}
