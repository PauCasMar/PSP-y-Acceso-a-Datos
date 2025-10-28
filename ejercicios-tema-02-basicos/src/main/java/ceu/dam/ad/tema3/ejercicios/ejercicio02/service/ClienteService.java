package ceu.dam.ad.tema3.ejercicios.ejercicio02.service;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceu.dam.ad.tema3.ejercicios.ejercicio02.model.Cliente;
import ceu.dam.ad.tema3.ejercicios.ejercicio02.repository.ClienteRepository;

@Service
public class ClienteService {

	private static final Logger log = LoggerFactory.getLogger(ClienteService.class);
	
	@Autowired	
	private ClienteRepository repo; 
	
	
	public Map<String, Cliente> consultarMapaClientes() throws ClientesException{
			log.info("Consultamos todos los clientes");
			List<Cliente> clientes = repo.findAll();
			return clientes.stream().collect(Collectors.toMap(Cliente::getEmail, c->c));
		
	}
	
}
