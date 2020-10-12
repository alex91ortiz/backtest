package com.coomeva.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coomeva.test.model.Factura;
import com.coomeva.test.repository.FacturaRepository;

@Transactional
@Service("facturaService")
public class FacturaServiceImpl implements FacturaService {
	@Autowired
	FacturaRepository facturaRepository;

	@Override
	public Optional<Factura> findById(int id) {
		Optional<Factura> factura = facturaRepository.findById(id);
		return factura;
	}

	@Override
	public Factura save(Factura factura) {
		if (factura != null) {
			return facturaRepository.save(factura);
		}
		return new Factura();
	}

	@Override
	public boolean update(Factura factura) {
		facturaRepository.save(factura);
		return true;
	}

	@Override
	public boolean deleteById(int id) {
		facturaRepository.deleteById(id);
		return true;
	}

	@Override
	public List<Factura> findAll() {
		return (List<Factura>) facturaRepository.findAll();
	}
}