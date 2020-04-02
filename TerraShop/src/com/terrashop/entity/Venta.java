package com.terrashop.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "VENTA")
public class Venta implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_VENTA")
	private Long idVenta;

	@Column(name = "FECHA_VENTA")
	private Date fechaVenta;

	@Column(name = "DESCUENTO")
	private float descuento;

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO")
	private Usuario usuario;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<LineaDC> lineasDC = new HashSet<>();

	public Venta() {
	}

	public Long getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Long idVenta) {
		this.idVenta = idVenta;
	}

	public Date getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	public float getDescuento() {
		return descuento;
	}

	public void setDescuento(float descuento) {
		this.descuento = descuento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<LineaDC> getLineasDC() {
		return lineasDC;
	}

	public void setLineasDC(Set<LineaDC> lineasDC) {
		this.lineasDC = lineasDC;
	}

	public boolean addLineaDC(LineaDC lineaDC) {
		lineaDC.setVenta(this);
		return getLineasDC().add(lineaDC);
	}

	public void removeLineaDC(LineaDC lineaDC) {
		getLineasDC().remove(lineaDC);
	}

	@Override
	public String toString() {
		return "Venta [idVenta=" + idVenta + ", fechaVenta=" + fechaVenta + ", descuento=" + descuento + ", usuario="
				+ usuario + ", lineasDC=" + lineasDC + "]";
	}

}
