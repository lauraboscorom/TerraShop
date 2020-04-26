package com.terrashop.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RESPUESTA")
public class Respuesta {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_RESPUESTA")
	private Long idRespuesta;
	
	@ManyToOne
	@JoinColumn(name = "ID_PREGUNTA")
	private Pregunta pregunta;
	
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;
	
	@Column(name = "TEXTO")
	private String texto;

	public Respuesta() {
	}

	public Long getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(Long idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public Pregunta getPregunta() {
		return pregunta;
	}

	public void setPregunta(Pregunta pregunta) {
		this.pregunta = pregunta;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
