package ceu.dam.ad.tema3.ejercicios.ejercicio06.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ceu.dam.ad.tema3.ejercicios.ejercicio04.modelo.Pedido;
import ceu.dam.ad.tema3.ejercicios.ejercicio04.modelo.PedidoLinea;
import ceu.dam.ad.tema3.ejercicios.ejercicio04.repository.PedidoDao;
import ceu.dam.ad.tema3.ejercicios.ejercicio04.repository.PedidoLineaDao;

public class PedidosService { // Este servicio tendrás que implementarlo igual que el ej.4, pero utilizando las relaciones entre entidades

	private static final Logger log = LoggerFactory.getLogger(PedidosService.class);
	
	private PedidoDao daoPedido;
	private PedidoLineaDao daoLinea;

	public PedidosService() {
		daoPedido = new PedidoDao();
		daoLinea = new PedidoLineaDao();
	}

	public Pedido consultarPedido(Long idPedido) throws PedidoException, PedidoNotFoundException {
		log.debug("Consultando pedido con id " + idPedido);
		try (Connection conn = null){
			Pedido pedido = daoPedido.consultar(conn, idPedido);
			if (pedido == null) {
				log.warn("No se ha encontrado el pedido con id " + idPedido);
				throw new PedidoNotFoundException("No existe pedido con id " + idPedido);
			}
			pedido.setLineas(daoLinea.consultar(conn, idPedido));
			return pedido;
		}
		catch(SQLException e) {
			log.error("Error registrando pedido", e);
			throw new PedidoException("Error al registrar pedido", e);
		}
	}
	
	public Long crearPedido(Pedido pedido) throws PedidoException {
		log.debug("Creando nuevo pedido....");
		try (Connection conn = null){
			conn.setAutoCommit(false);
			try {
				log.debug("Insertando datos generales del pedido...");
				Long idPedido = daoPedido.insertar(conn, pedido);
				Integer numLinea = 1;
				log.debug("Insertando líneas del pedido...");
				for (PedidoLinea linea : pedido.getLineas()) {
					linea.setIdPedido(idPedido);
					linea.setNumLinea(numLinea);
					numLinea++;
					daoLinea.insertar(conn, linea);
				}
				
				conn.commit();
				log.info("Pedido registrado con id " + idPedido);
				return idPedido;
			}
			catch(SQLException e) {
				conn.rollback();
				throw e;
			}
		}
		catch(SQLException e) {
			log.error("Error creando pedido", e);
			throw new PedidoException("Error al registrar pedido", e);
		}
	}
	
}
