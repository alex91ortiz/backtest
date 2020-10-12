package com.coomeva.test.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_productos")
public class Producto implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int IdProducto;
	@Column(name = "Codigo", nullable = false)
	private String Codigo;
	@Column(name = "Nombre", nullable = false)
	private String Nombre;
	@Column(name = "ValorUnidad", nullable = false)
	private float ValorUnidad;
	@Column(name = "Stock", nullable = false)
	private int Stock;
	public int getIdProducto() {
		return IdProducto;
	}
	public void setIdProducto(int idProducto) {
		IdProducto = idProducto;
	}
	public String getCodigo() {
		return Codigo;
	}
	public void setCodigo(String codigo) {
		Codigo = codigo;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public float getValorUnidad() {
		return ValorUnidad;
	}
	public void setValorUnidad(float valorUnidad) {
		ValorUnidad = valorUnidad;
	}
	public int getStock() {
		return Stock;
	}
	public void setStock(int stock) {
		Stock = stock;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Codigo == null) ? 0 : Codigo.hashCode());
		result = prime * result + IdProducto;
		result = prime * result + ((Nombre == null) ? 0 : Nombre.hashCode());
		result = prime * result + Stock;
		result = prime * result + Float.floatToIntBits(ValorUnidad);
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
		Producto other = (Producto) obj;
		if (Codigo == null) {
			if (other.Codigo != null)
				return false;
		} else if (!Codigo.equals(other.Codigo))
			return false;
		if (IdProducto != other.IdProducto)
			return false;
		if (Nombre == null) {
			if (other.Nombre != null)
				return false;
		} else if (!Nombre.equals(other.Nombre))
			return false;
		if (Stock != other.Stock)
			return false;
		if (Float.floatToIntBits(ValorUnidad) != Float.floatToIntBits(other.ValorUnidad))
			return false;
		return true;
	}
	
	
}