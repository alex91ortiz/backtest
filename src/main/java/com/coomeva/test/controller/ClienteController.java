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

import com.coomeva.test.model.Cliente;
import com.coomeva.test.service.ClienteService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class ClienteController {
	@Autowired
	ClienteService ClienteServiceImpl;

	@RequestMapping(value = "/cliente/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Cliente>> listAllClientes() {
		List<Cliente> clientes = ClienteServiceImpl.findAll();
		if (clientes.isEmpty()) {
			return new ResponseEntity<List<Cliente>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
	}

	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Cliente> getCliente(@PathVariable("id") int id) {
		Cliente cliente = ClienteServiceImpl.findById(id).get();
		if (cliente == null) {
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	@RequestMapping(value = "/buscarcliente/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Cliente>> getClienteByIdentificacion(@PathVariable("id") int id) {
		List<Cliente> cliente = ClienteServiceImpl.findByIdentificacion(id);
		if (cliente == null) {
			return new ResponseEntity<List<Cliente>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Cliente>>(cliente, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cliente/", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Void> createCliente(@RequestBody Cliente cliente, UriComponentsBuilder ucBuilder) {
		List<Cliente> clienteOld = ClienteServiceImpl.findByIdentificacion(cliente.getIdentificacion());
		if (!clienteOld.isEmpty()) {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		ClienteServiceImpl.save(cliente);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Cliente> updateCliente(@PathVariable("id") int id, @RequestBody Cliente cliente) {
		
		Cliente currentCliente = ClienteServiceImpl.findById(id).get();
		if (currentCliente == null) {
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}
		currentCliente.setIdentificacion(cliente.getIdentificacion());
		currentCliente.setNombres(cliente.getNombres());
		currentCliente.setApellidos(cliente.getApellidos());
		currentCliente.setDireccion(cliente.getDireccion());
		currentCliente.setTelefono(cliente.getTelefono());
		currentCliente.setEmail(cliente.getEmail());
		ClienteServiceImpl.update(currentCliente);
		return new ResponseEntity<Cliente>(currentCliente, HttpStatus.OK);
	}

	@RequestMapping(value = "/cliente/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Cliente> deleteCliente(@PathVariable("id") int id) {
		Cliente cliente = ClienteServiceImpl.findById(id).get();
		if (cliente == null) {
			return new ResponseEntity<Cliente>(HttpStatus.NOT_FOUND);
		}
		ClienteServiceImpl.deleteById(id);
		return new ResponseEntity<Cliente>(HttpStatus.NO_CONTENT);
	}
}