package com.terrashop.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.terrashop.config.PersistenceJPAHibernateConfig;
import com.terrashop.config.WebMvcConfig;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	 
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {/*PersistenceJPAHibernateConfig.class*/ };
	}
 
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { WebMvcConfig.class };
	}
 
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
 
}