package com.coomeva.test.controller;

import java.awt.datatransfer.SystemFlavorMap;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.coomeva.test.model.Factura;
import com.coomeva.test.model.FacturaDto;
import com.coomeva.test.model.Facturadetalle;
import com.coomeva.test.model.Producto;
import com.coomeva.test.service.FacturaService;
import com.coomeva.test.service.FacturadetalleService;
import com.coomeva.test.service.ProductoService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class FacturaController {
	@Autowired
	FacturaService FacturaServiceImpl;
	@Autowired
	FacturadetalleService FacturadetalleServiceImpl;
	@Autowired
	ProductoService ProductoServiceImpl;
	
	@RequestMapping(value = "/factura/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Factura>> listAllFacturas() {
		List<Factura> facturas = FacturaServiceImpl.findAll();
		if (facturas.isEmpty()) {
			return new ResponseEntity<List<Factura>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Factura>>(facturas, HttpStatus.OK);
	}

	@RequestMapping(value = "/factura/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Optional<Factura>> getFactura(@PathVariable("id") int id) {
		Optional<Factura> factura = FacturaServiceImpl.findById(id);
		if (factura == null) {
			return new ResponseEntity<Optional<Factura>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Optional<Factura>>(factura, HttpStatus.OK);
	}

	@RequestMapping(value = "/factura/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Void> createFactura(@RequestBody FacturaDto facturaDto, UriComponentsBuilder ucBuilder) {
		Calendar date = Calendar.getInstance();
		facturaDto.getFactura().setFechaVenta(date.getTime());
		Factura factura = facturaDto.getFactura();	
		Factura newFactura = FacturaServiceImpl.save(factura);
		List<Facturadetalle> facturadetalle = facturaDto.getFacturadetalles();
		for(Facturadetalle fd : facturadetalle) {
			fd.setFactura(newFactura);
			FacturadetalleServiceImpl.save(fd);
			Producto newProducto = fd.getProducto();
			newProducto.setStock(newProducto.getStock()-fd.getCantidad());
			ProductoServiceImpl.update(newProducto);
		}
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/factura/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Factura> updateFactura(@PathVariable("id") int id, @RequestBody Factura factura) {		
		Factura currentFactura = FacturaServiceImpl.findById(id).get();
		if (currentFactura == null) {
			return new ResponseEntity<Factura>(HttpStatus.NOT_FOUND);
		}
		
		currentFactura.setCliente(factura.getCliente());
		//currentFactura.setFechaVenta(factura.getFechaVenta());
		currentFactura.setValorTotal(factura.getValorTotal());
		FacturaServiceImpl.update(currentFactura);
		return new ResponseEntity<Factura>(currentFactura,HttpStatus.OK);
	}

	@RequestMapping(value = "/factura/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Factura> deleteFactura(@PathVariable("id") int id) {
		Factura factura = FacturaServiceImpl.findById(id).get();
		if (factura == null) {
			return new ResponseEntity<Factura>(HttpStatus.NOT_FOUND);
		}
		FacturaServiceImpl.deleteById(id);
		return new ResponseEntity<Factura>(HttpStatus.NO_CONTENT);
	}
}