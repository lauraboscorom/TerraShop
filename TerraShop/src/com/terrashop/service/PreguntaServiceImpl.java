package com.terrashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.terrashop.dao.PreguntaDao;
import com.terrashop.entity.Pregunta;

@Transactional
@Service
public class PreguntaServiceImpl implements PreguntaService {

	@Autowired
	PreguntaDao preguntaDao;
	
	@Override
	public Pregunta obtenerPregunta(Long idPregunta) {
		return preguntaDao.find(idPregunta);
	}
	
	@Override
	public Pregunta editarPregunta(Pregunta pregunta) {
		return preguntaDao.update(pregunta);
	}

}
