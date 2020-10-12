package com.coomeva.test.model;

import java.io.Serializable;
import java.util.List;

public class FacturaDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7733461492942642327L;
	private Factura factura;
	private List<Facturadetalle> facturadetalles;
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	public List<Facturadetalle> getFacturadetalles() {
		return facturadetalles;
	}
	public void setFacturadetalles(List<Facturadetalle> facturadetalles) {
		this.facturadetalles = facturadetalles;
	}
	
	
}
