package com.coomeva.test.controller;

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

import com.coomeva.test.model.Producto;
import com.coomeva.test.service.ProductoService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ProductoController {
	@Autowired
	ProductoService ProductoServiceImpl;

	@RequestMapping(value = "/producto/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Producto>> listAllProductos() {
		List<Producto> productos = ProductoServiceImpl.findAll();
		if (productos.isEmpty()) {
			return new ResponseEntity<List<Producto>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/buscarproducto/{nombre}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Producto>> findProducto(@PathVariable("nombre") String nombre) {
		List<Producto> productos = ProductoServiceImpl.findByNombre(nombre);
		if (productos.isEmpty()) {
			return new ResponseEntity<List<Producto>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
	}

	@RequestMapping(value = "/agregarproducto/{codigo}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Producto>> addProducto(@PathVariable("codigo") String codigo) {
		List<Producto> productos = ProductoServiceImpl.findByCodigo(codigo);
		if (productos == null) {
			return new ResponseEntity<List<Producto>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Producto>>(productos, HttpStatus.OK);
	}

	@RequestMapping(value = "/producto/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Optional<Producto>> getProducto(@PathVariable("id") int id) {
		Optional<Producto> producto = ProductoServiceImpl.findById(id);
		if (producto == null) {
			return new ResponseEntity<Optional<Producto>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Optional<Producto>>(producto, HttpStatus.OK);
	}

	@RequestMapping(value = "/producto/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Void> createProducto(@RequestBody Producto producto, UriComponentsBuilder ucBuilder) {
		List<Producto> productoOld = ProductoServiceImpl.findByCodigo(producto.getCodigo());
		if (!productoOld.isEmpty()) {
			System.out.println("A Companie with Nombre " + producto.getCodigo() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		ProductoServiceImpl.save(producto);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/producto/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Producto> updateProducto(@PathVariable("id") int id, @RequestBody Producto producto) {
		Producto currentProducto = ProductoServiceImpl.findById(id).get();
		if (currentProducto == null) {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
		currentProducto.setCodigo(producto.getCodigo());
		currentProducto.setNombre(producto.getNombre());
		currentProducto.setValorUnidad(producto.getValorUnidad());
		currentProducto.setStock(producto.getStock());
		ProductoServiceImpl.update(currentProducto);
		return new ResponseEntity<Producto>(currentProducto, HttpStatus.OK);
	}

	@RequestMapping(value = "/producto/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Producto> deleteProducto(@PathVariable("id") int id) {
		Producto producto = ProductoServiceImpl.findById(id).get();
		if (producto == null) {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
		ProductoServiceImpl.deleteById(id);
		return new ResponseEntity<Producto>(HttpStatus.NO_CONTENT);
	}
}