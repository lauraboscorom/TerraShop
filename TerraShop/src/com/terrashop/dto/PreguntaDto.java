package com.terrashop.dto;

import java.util.HashSet;
import java.util.Set;

import com.terrashop.entity.Respuesta;

public class PreguntaDto {
	
	private Long idPregunta;
	
	private UsuarioDto usuario;
	
	private String texto;
	
	private Set<RespuestaDto> respuestas = new HashSet<>();

	public Long getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(Long idPregunta) {
		this.idPregunta = idPregunta;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public UsuarioDto getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDto usuario) {
		this.usuario = usuario;
	}

	public Set<RespuestaDto> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(Set<RespuestaDto> respuestas) {
		this.respuestas = respuestas;
	}

}
