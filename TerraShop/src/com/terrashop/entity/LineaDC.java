package com.terrashop.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LINEADC")
public class LineaDC {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID_LINEADC")
	private Long idLineaDC;
	
	@ManyToOne
	@JoinColumn(name = "ID_PRODUCTO")
	private Producto producto;
	
	@ManyToOne
	@JoinColumn(name = "ID_VENTA")
	private Venta venta;
	
	@Column(name = "PRECIO_PRODUCTO")
	private float precioProducto;

	public LineaDC() {
	}

	public Long getIdLineaDC() {
		return idLineaDC;
	}

	public void setIdLineaDC(Long idLineaDC) {
		this.idLineaDC = idLineaDC;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Venta getVenta() {
		return venta;
	}

	public void setVenta(Venta venta) {
		this.venta = venta;
	}

	public float getPrecioProducto() {
		return precioProducto;
	}

	public void setPrecioProducto(float precioProducto) {
		this.precioProducto = precioProducto;
	}

	@Override
	public String toString() {
		return "LineaDC [idLineaDC=" + idLineaDC + ", producto=" + producto + ", venta=" + venta + ", precioProducto="
				+ precioProducto + "]";
	}

}
