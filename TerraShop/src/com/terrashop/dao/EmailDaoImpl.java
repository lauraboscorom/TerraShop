package com.terrashop.dao;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.terrashop.entity.Email;

@Repository
@Component("EmailDao")
public class EmailDaoImpl extends GenericDaoImpl<Email> implements EmailDao{

}
