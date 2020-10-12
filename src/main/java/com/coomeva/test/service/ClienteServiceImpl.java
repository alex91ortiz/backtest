package com.coomeva.test.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.coomeva.test.model.Cliente;
import com.coomeva.test.repository.ClienteRepository;

@Transactional
@Service("clienteService")
public class ClienteServiceImpl implements ClienteService {
	@Autowired
	ClienteRepository clienteRepository;

	@Override
	public Optional<Cliente> findById(int id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente;
	}

	@Override
	public List<Cliente> findByIdentificacion(int Identificacion) {
		List<Cliente> cliente =  clienteRepository.findByIdentificacion(Identificacion);

         return cliente;
	}

	@Override
	public Cliente save(Cliente cliente) {
		if (cliente != null) {
			return clienteRepository.save(cliente);
		}
		return new Cliente();
	}

	@Override
	public boolean update(Cliente cliente) {
		clienteRepository.save(cliente);
		return true;
	}

	@Override
	public boolean deleteById(int id) {
		clienteRepository.deleteById(id);
		return true;
	}

	@Override
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteRepository.findAll();
	}
}