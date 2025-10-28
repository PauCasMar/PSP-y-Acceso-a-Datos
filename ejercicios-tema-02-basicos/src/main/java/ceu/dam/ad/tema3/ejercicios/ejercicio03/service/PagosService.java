package ceu.dam.ad.tema3.ejercicios.ejercicio03.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ceu.dam.ad.tema3.ejercicios.ejercicio02.model.Cliente;
import ceu.dam.ad.tema3.ejercicios.ejercicio02.repository.ClienteRepository;

import ceu.dam.ad.tema3.ejercicios.ejercicio03.model.Pago;
import ceu.dam.ad.tema3.ejercicios.ejercicio03.repository.PagoRepository;

@Service
public class PagosService {

	private static final Logger log = LoggerFactory.getLogger(PagosService.class);

	@Autowired
	private PagoRepository repoPago;
	@Autowired
	private ClienteRepository repoCliente;

	public Map<String, List<Pago>> consultarPagosClientes() throws PagosException {
		try {

			Map<String, List<Pago>> mapa = new HashMap<>();

			// 1. Obtener lista de clientes de ClienteDao
			List<Cliente> clientes = repoCliente.findAll();
			// 2. Recorrer la lista de clientes
			for (Cliente cliente : clientes) {
				// 4. Por cada cliente --> Llamar a PagosDao y obtener su lista de pagos
				List<Pago> pagos = repoPago.findAllByCustomerId(cliente.getId());
				// 5. Meter en el mapa el email del cliente + su lista de pagos
				mapa.put(cliente.getEmail(), pagos);
			}
			return mapa;

		} catch (DataAccessException e) {
			log.error("Error al obtener pagos de clientes de bbdd", e);
			throw new PagosException("Error al obtener pagos de cliente", e);
		}
	}
}
