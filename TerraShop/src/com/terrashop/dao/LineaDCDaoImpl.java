package com.terrashop.dao;

import javax.persistence.Query;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.terrashop.entity.LineaDC;
import com.terrashop.entity.Producto;
import com.terrashop.entity.Venta;

@Repository
@Component("LineaDCDao")
public class LineaDCDaoImpl extends GenericDaoImpl<LineaDC> implements LineaDCDao {

}
