package com.coomeva.test.service;

import java.util.List;
import java.util.Optional;

import com.coomeva.test.model.Facturadetalle;

public interface FacturadetalleService {
	Optional<Facturadetalle> findById(int id);

	Facturadetalle save(Facturadetalle Facturadetalle);

	boolean update(Facturadetalle Facturadetalle);

	boolean deleteById(int id);

	List<Facturadetalle> findAll();
}