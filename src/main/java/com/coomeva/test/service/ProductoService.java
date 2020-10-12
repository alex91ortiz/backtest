package com.coomeva.test.service;

import java.util.List;
import java.util.Optional;

import com.coomeva.test.model.Producto;

public interface ProductoService {
	Optional<Producto> findById(int id);

	List<Producto> findByNombre(String Nombre);
	
	List<Producto> findByCodigo(String Codigo);

	Producto save(Producto Producto);

	boolean update(Producto Producto);

	boolean deleteById(int id);

	List<Producto> findAll();
}