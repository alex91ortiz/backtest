package com.coomeva.test.repository;

import org.springframework.data.repository.CrudRepository;

import com.coomeva.test.model.Factura;

public interface FacturaRepository extends CrudRepository<Factura, Integer> {
}