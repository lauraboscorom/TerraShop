package com.terrashop.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductoDto {
	
	private Long idProducto;
	
	private String nombre;
	
	private float precio;
	
	private int stock;
	
	private List<Long> idImagenes = new ArrayList<Long>();
	
	private Set<PreguntaDto> preguntas = new HashSet<>();
	
	public Long getIdProducto() {
		return idProducto;
	}
	
	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public float getPrecio() {
		return precio;
	}
	
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	
	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<Long> getIdImagenes() {
		return idImagenes;
	}

	public void setIdImagenes(List<Long> idImagenes) {
		this.idImagenes = idImagenes;
	}

	public Set<PreguntaDto> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(Set<PreguntaDto> preguntas) {
		this.preguntas = preguntas;
	}

}
