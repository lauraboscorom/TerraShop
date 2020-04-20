package com.terrashop.dto;

public class ProductoDto {
	
	private Long idProducto;
	
	private String nombre;
	
	private float precio;
	
	private int stock;
	
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

}
