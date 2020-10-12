package com.coomeva.test.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "test_facturas")
public class Factura implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int IdFactura;
	@ManyToOne
	@JoinColumn(name = "IdCliente", nullable = false)
	private Cliente cliente;
	@Column(name = "FechaVenta", nullable = false)

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Date FechaVenta;
	@Column(name = "ValorTotal", nullable = false)
	@JsonProperty("ValorTotal")
	private float ValorTotal;
	
	public int getIdFactura() {
		return IdFactura;
	}
	public void setIdFactura(int idFactura) {
		IdFactura = idFactura;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Date getFechaVenta() {
		return FechaVenta;
	}
	public void setFechaVenta(Date fechaVenta) {
		FechaVenta = fechaVenta;
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
		result = prime * result + ((FechaVenta == null) ? 0 : FechaVenta.hashCode());
		result = prime * result + IdFactura;
		result = prime * result + Float.floatToIntBits(ValorTotal);
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
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
		Factura other = (Factura) obj;
		if (FechaVenta == null) {
			if (other.FechaVenta != null)
				return false;
		} else if (!FechaVenta.equals(other.FechaVenta))
			return false;
		if (IdFactura != other.IdFactura)
			return false;
		if (Float.floatToIntBits(ValorTotal) != Float.floatToIntBits(other.ValorTotal))
			return false;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		return true;
	}
	
}