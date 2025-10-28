package ceu.dam.ad.tema3.ejercicios.ejercicio04.service;


import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ceu.dam.ad.tema3.ejercicios.ejercicio03.service.PagosService;
import ceu.dam.ad.tema3.ejercicios.ejercicio04.modelo.Pedido;
import ceu.dam.ad.tema3.ejercicios.ejercicio04.modelo.PedidoLinea;
import ceu.dam.ad.tema3.ejercicios.ejercicio04.repository.PedidoLineaRepository;
import ceu.dam.ad.tema3.ejercicios.ejercicio04.repository.PedidoRepository;
import jakarta.transaction.Transactional;

@Service
public class PedidosService {

 	private static final Logger log = LoggerFactory.getLogger(PedidosService.class);

	@Autowired
	private PedidoRepository repoPedido;
	@Autowired
	private PedidoLineaRepository repoPedidoLi;

	//El transactional sirve para que se ejecute todo o nada. como el commit y rollback
	@Transactional
	public Pedido consultarPedido(Long idPedido) throws PedidoException, PedidoNotFoundException {
		log.debug("Consultando pedido con id " + idPedido);
		try {
			Optional<Pedido> optPedido = repoPedido.findById(idPedido);
			if (optPedido.isEmpty()) { //se comprueba que esté vacía, así o con (!optPedido.isPresent())
				log.warn("No se ha encontrado el pedido con id " + idPedido);
				throw new PedidoNotFoundException("No existe pedido con id " + idPedido);
			}
			Pedido pedido = optPedido.get();
			pedido.setLineas(repoPedidoLi.findByIdPedido(idPedido));
			return pedido;
		} catch (DataAccessException e) {
			log.error("Error registrando pedido", e);
			throw new PedidoException("Error al registrar pedido", e);
		}
	}

	@Transactional
	public Long crearPedido(Pedido pedido) throws PedidoException {
		log.debug("Creando nuevo pedido....");
		try {

			log.debug("Insertando datos generales del pedido...");
			repoPedido.save(pedido);
			//El pedido dentro de save ya está actualizado cuando se hace el save. Ya tenemos el id actualizado
			Integer numLinea = 1;
			System.out.println(pedido);
			log.debug("Insertando líneas del pedido...");
			for (PedidoLinea linea : pedido.getLineas()) {
				linea.setIdPedido(pedido.getIdPedido());
				linea.setNumLinea(numLinea);
				numLinea++;
				repoPedidoLi.save(linea);
			}

			log.info("Pedido registrado con id " + pedido.getIdPedido());
			return pedido.getIdPedido();

		} catch (DataAccessException e) {
			log.error("Error creando pedido", e);
			throw new PedidoException("Error al registrar pedido", e);
		}
	}

}
