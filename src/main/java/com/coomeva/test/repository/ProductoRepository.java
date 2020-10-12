package com.coomeva.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.coomeva.test.model.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Integer> {
	@Query("from Producto l  where l.Codigo=:Codigo")
	public List<Producto> findByCodigo(@Param("Codigo") String Codigo);
	
	@Query("from Producto l  where l.Nombre=:Nombre")
	public List<Producto> findByNombre(@Param("Nombre") String Nombre);
}