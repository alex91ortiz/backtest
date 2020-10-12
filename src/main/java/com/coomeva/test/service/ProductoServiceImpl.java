package com.coomeva.test.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.coomeva.test.model.Producto;
import com.coomeva.test.repository.ProductoRepository;

@Transactional
@Service("productoService")
public class ProductoServiceImpl implements ProductoService {
	@Autowired
	ProductoRepository productoRepository;

	@Override
	public Optional<Producto> findById(int id) {
		Optional<Producto> producto = productoRepository.findById(id);
		return producto;
	}

	@Override
	public List<Producto> findByCodigo(String Codigo) {
		  List<Producto> producto = productoRepository.findByCodigo(Codigo);

         return  producto;
	}

	@Override
	public List<Producto> findByNombre(String Nombre) {
		  List<Producto> producto = productoRepository.findByNombre(Nombre);

         return  producto;
	}
	
	@Override
	public Producto save(Producto producto) {
		if (producto != null) {
			return productoRepository.save(producto);
		}
		return new Producto();
	}

	@Override
	public boolean update(Producto producto) {
		productoRepository.save(producto);
		return true;
	}

	@Override
	public boolean deleteById(int id) {
		productoRepository.deleteById(id);
		return true;
	}

	@Override
	public List<Producto> findAll() {
		return (List<Producto>) productoRepository.findAll();
	}
}