package com.terrashop.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCTO")
public class Producto implements Serializable {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_PRODUCTO")
	private Long idProducto;

	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "PRECIO")
	private float precio;
	
	@Column(name = "STOCK")
	private int stock;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<LineaDC> lineasDC = new HashSet<>();
	
	@Lob
	private Set<Imagen> imagenes = new HashSet<>();
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Pregunta> preguntas = new HashSet<>();
	
	@ManyToOne
	@JoinColumn(name = "ID_CATEGORIA")
	private Categoria categoria;
	
	public Producto() {
	}

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

	public Set<LineaDC> getLineasDC() {
		return lineasDC;
	}

	public void setLineasDC(Set<LineaDC> lineasDC) {
		this.lineasDC = lineasDC;
	}
	
	public boolean addLineaDC(LineaDC lineaDC) {
		lineaDC.setProducto(this);
		return getLineasDC().add(lineaDC);
	}

	public void removeLineaDC(LineaDC lineaDC) {
		getLineasDC().remove(lineaDC);
	}

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Imagen> getImagenes() {
		return imagenes;
	}

	public void setImagenes(Set<Imagen> imagenes) {
		this.imagenes = imagenes;
	}
	
	public boolean addImagen(Imagen imagen) {
		imagen.setProducto(this);
		return getImagenes().add(imagen);
	}

	public void removeImagen(Imagen imagen) {
		getImagenes().remove(imagen);
	}

	public Set<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(Set<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	
	public boolean addPregunta(Pregunta pregunta) {
		pregunta.setProducto(this);
		return getPreguntas().add(pregunta);
	}

	public void removePregunta(Pregunta pregunta) {
		getPreguntas().remove(pregunta);
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
