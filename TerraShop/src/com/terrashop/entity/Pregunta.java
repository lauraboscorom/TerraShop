package com.terrashop.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PREGUNTA")
public class Pregunta {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_PREGUNTA")
	private Long idPregunta;
	
	@ManyToOne
	@JoinColumn(name = "ID_PRODUCTO")
	private Producto producto;
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	
	@Column(name = "TEXTO")
	private String texto;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "pregunta", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Respuesta> respuestas = new HashSet<>();

	public Pregunta() {}

	public Long getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(Long idPregunta) {
		this.idPregunta = idPregunta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<Respuesta> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(Set<Respuesta> respuestas) {
		this.respuestas = respuestas;
	}
	
	public boolean addRespuesta(Respuesta respuesta) {
		respuesta.setPregunta(this);
		return getRespuestas().add(respuesta);
	}

	public void removePregunta(Respuesta respuesta) {
		getRespuestas().remove(respuesta);
	}

}
