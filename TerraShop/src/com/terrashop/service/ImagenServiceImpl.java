package com.terrashop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.terrashop.dao.ImagenDao;
import com.terrashop.entity.Imagen;

@Transactional
@Service
public class ImagenServiceImpl implements ImagenService {

	@Autowired
	ImagenDao imagenDao;
	
	@Override
	public Imagen crearImagen(Imagen imagen) {
		return imagenDao.create(imagen);
	}

	@Override
	public Imagen getImagen(Long id) {
        return imagenDao.find(id);
    }

}
