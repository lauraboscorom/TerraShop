package com.terrashop.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_USUARIO")
	private Long idUsuario;

	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "APELLIDOS")
	private String apellidos;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "DIRECCION_ENVIO")
	private String direccionEnvio;
	
	@Column(name = "BANCO")
	private String banco;
	
	@Column(name = "NUMERO_TARJETA")
	private Long numeroTarjeta;
	
	@Column(name = "TITULAR")
	private String titular;
	
	@Column(name = "CODIGO_SEGURIDAD")
	private Long codigoSeguridad;
	
	@Column(name = "DIRECCION_FACTURACION")
	private String direccionFacturacion;
	
	@Column(name = "USUARIO")
	private String usuario;
	
	@Column(name = "CONTRASENA")
	private String contrasena;
	
	public Usuario(String nombre, String apellidos, String email, String direccionEnvio, String banco,
			Long numeroTarjeta, String titular, Long codigoSeguridad, String direccionFacturacion, String usuario,
			String contrasena) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.direccionEnvio = direccionEnvio;
		this.banco = banco;
		this.numeroTarjeta = numeroTarjeta;
		this.titular = titular;
		this.codigoSeguridad = codigoSeguridad;
		this.direccionFacturacion = direccionFacturacion;
		this.usuario = usuario;
		this.contrasena = contrasena;
	}
	
	public Usuario() {
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreUsuario() {
		return nombre;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombre = nombreUsuario;
	}

	public String getApellidosUsuario() {
		return apellidos;
	}

	public void setApellidosUsuario(String apellidosUsuario) {
		this.apellidos = apellidosUsuario;
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

	public Long getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(Long numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public Long getCodigoSeguridad() {
		return codigoSeguridad;
	}

	public void setCodigoSeguridad(Long codigoSeguridad) {
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

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

}
