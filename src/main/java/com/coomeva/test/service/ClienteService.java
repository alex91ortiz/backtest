package com.coomeva.test.service;

import java.util.List;
import java.util.Optional;

import com.coomeva.test.model.Cliente;

public interface ClienteService {
	Optional<Cliente> findById(int id);

	List<Cliente> findByIdentificacion(int Identificacion);

	Cliente save(Cliente Cliente);

	boolean update(Cliente Cliente);

	boolean deleteById(int id);

	List<Cliente> findAll();
}