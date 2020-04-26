package com.terrashop.service;

import com.terrashop.entity.Pregunta;

public interface PreguntaService {

	public Pregunta obtenerPregunta(Long idPregunta);

	public Pregunta editarPregunta(Pregunta pregunta);

}
