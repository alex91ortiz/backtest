package com.coomeva.test.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.coomeva.test.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
	@Query("from Cliente l  where l.Identificacion=:identificacion")
	public List<Cliente> findByIdentificacion(@Param("identificacion") int identificacion);
}