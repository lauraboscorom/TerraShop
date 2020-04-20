package com.terrashop.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.terrashop.entity.Pregunta;

@Repository
@Component("PreguntaDao")
public class PreguntaDaoImpl extends GenericDaoImpl<Pregunta> implements PreguntaDao {

}
