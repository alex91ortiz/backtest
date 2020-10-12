package com.coomeva.test.service;

import java.util.List;
import java.util.Optional;

import com.coomeva.test.model.Factura;

public interface FacturaService {
	Optional<Factura> findById(int id);

	Factura save(Factura Factura);

	boolean update(Factura Factura);

	boolean deleteById(int id);

	List<Factura> findAll();
}