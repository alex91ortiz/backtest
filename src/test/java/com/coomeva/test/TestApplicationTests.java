package com.coomeva.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.coomeva.test.model.Cliente;
import com.coomeva.test.repository.ClienteRepository;

@SpringBootTest
class TestApplicationTests {
	  @MockBean
	  private ClienteRepository clienteRepository;

	  

	@Test
	void contextLoads() {
		
		Iterable<Cliente> cliente = clienteRepository.findAll();
		 assertNotNull(cliente);
	}

}
