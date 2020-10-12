package com.coomeva.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coomeva.test.model.Facturadetalle;
import com.coomeva.test.repository.FacturadetalleRepository;

@Transactional
@Service("facturadetalleService")
public class FacturadetalleServiceImpl implements FacturadetalleService {
	@Autowired
	FacturadetalleRepository facturadetalleRepository;

	@Override
	public Optional<Facturadetalle> findById(int id) {
		Optional<Facturadetalle> facturadetalle = facturadetalleRepository.findById(id);
		return facturadetalle;
	}

	@Override
	public Facturadetalle save(Facturadetalle facturadetalle) {
		if (facturadetalle != null) {
			return facturadetalleRepository.save(facturadetalle);
		}
		return new Facturadetalle();
	}

	@Override
	public boolean update(Facturadetalle facturadetalle) {
		facturadetalleRepository.save(facturadetalle);
		return true;
	}

	@Override
	public boolean deleteById(int id) {
		facturadetalleRepository.deleteById(id);
		return true;
	}

	@Override
	public List<Facturadetalle> findAll() {
		return (List<Facturadetalle>) facturadetalleRepository.findAll();
	}
}