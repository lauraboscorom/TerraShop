package com.terrashop.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.terrashop.entity.Imagen;

@Repository
@Component("ImagenDao")
public class ImagenDaoImpl extends GenericDaoImpl<Imagen> implements ImagenDao {

}
