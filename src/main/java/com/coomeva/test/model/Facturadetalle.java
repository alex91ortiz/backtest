package com.coomeva.test.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "test_factura_detalles")
public class Facturadetalle implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int IdFacturaDetalle;
	@ManyToOne
	@JoinColumn(name = "IdFactura", nullable = false)
	private Factura factura;
	@ManyToOne
	@JoinColumn(name = "IdProducto", nullable = false)
	private Producto producto;
	@Column(name = "Cantidad", nullable = false)
	private int Cantidad;
	@Column(name = "ValorUnidad", nullable = false)
	@JsonProperty("ValorUnidad")
	private float ValorUnidad;
	@Column(name = "ValorTotal", nullable = false)
	@JsonProperty("ValorTotal")
	private float ValorTotal;
	public int getIdFacturaDetalle() {
		return IdFacturaDetalle;
	}
	public void setIdFacturaDetalle(int idFacturaDetalle) {
		IdFacturaDetalle = idFacturaDetalle;
	}
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return Cantidad;
	}
	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}
	public float getValorUnidad() {
		return ValorUnidad;
	}
	public void setValorUnidad(float valorUnidad) {
		ValorUnidad = valorUnidad;
	}
	public float getValorTotal() {
		return ValorTotal;
	}
	public void setValorTotal(float valorTotal) {
		ValorTotal = valorTotal;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Cantidad;
		result = prime * result + IdFacturaDetalle;
		result = prime * result + Float.floatToIntBits(ValorTotal);
		result = prime * result + Float.floatToIntBits(ValorUnidad);
		result = prime * result + ((factura == null) ? 0 : factura.hashCode());
		result = prime * result + ((producto == null) ? 0 : producto.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Facturadetalle other = (Facturadetalle) obj;
		if (Cantidad != other.Cantidad)
			return false;
		if (IdFacturaDetalle != other.IdFacturaDetalle)
			return false;
		if (Float.floatToIntBits(ValorTotal) != Float.floatToIntBits(other.ValorTotal))
			return false;
		if (Float.floatToIntBits(ValorUnidad) != Float.floatToIntBits(other.ValorUnidad))
			return false;
		if (factura == null) {
			if (other.factura != null)
				return false;
		} else if (!factura.equals(other.factura))
			return false;
		if (producto == null) {
			if (other.producto != null)
				return false;
		} else if (!producto.equals(other.producto))
			return false;
		return true;
	}
	
	
}