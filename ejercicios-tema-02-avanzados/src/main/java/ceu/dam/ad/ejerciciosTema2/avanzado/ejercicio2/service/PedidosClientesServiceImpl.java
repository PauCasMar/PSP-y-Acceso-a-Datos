package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model.Articulo;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model.Cliente;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model.Pedido;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model.PedidoLinea;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.repository.ArticuloRepository;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.repository.ClienteRepository;
import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.repository.PedidoRepository;
import jakarta.transaction.Transactional;

@Service
public class PedidosClientesServiceImpl implements PedidosClientesService {

	@Autowired
	ArticuloRepository artRepo;
	@Autowired
	ClienteRepository cliRepo;
	@Autowired
	PedidoRepository pedRepo;

	@Override
	@Transactional
	public void crearCliente(Cliente cliente) throws PedidosClientesServiceException {
		try {
			cliRepo.save(cliente);
		} catch (DataAccessException e) {
			throw new PedidosClientesServiceException("error guardando cliente", e);
		}
	}

	@Override
	@Transactional
	public Pedido crearPedido(Pedido pedido) throws PedidosClientesServiceException {
		try {
			if (cliRepo.findById(pedido.getCliente().getDni()).isEmpty()) {
				throw new PedidosClientesServiceException("No existe el cliente del pedido");
			}
			Integer i = 1;
			for (PedidoLinea pedidoLinea : pedido.getLineas()) {
				if (artRepo.findById(pedidoLinea.getArticulo().getId()).isEmpty()) {
					throw new PedidosClientesServiceException("El articulo no existe en la BBDD ");
				}
				pedidoLinea.setNumLinea(i);
				i++;
			}
			return pedRepo.save(pedido);
		} catch (DataAccessException e) {
			throw new PedidosClientesServiceException("El articulo no existe en la BBDD ", e);
		}
	}

	@Override
	public Articulo crearArticulo(Articulo articulo) throws PedidosClientesServiceException {
		try {
			return artRepo.save(articulo);
		} catch (DataAccessException e) {
			throw new PedidosClientesServiceException("El articulo no existe en la BBDD ", e);
		}
	}

	@Override
	public void actualizarCliente(Cliente cliente) throws PedidosClientesServiceException {
		try {
			consultarCliente(cliente.getDni());
			cliRepo.save(cliente);
		} catch (NotFoundException e) {
			throw new PedidosClientesServiceException(e);

		} catch (DataAccessException e) {
			throw new PedidosClientesServiceException("El articulo no existe en la BBDD ", e);
		}
	}

	@Override
	public Cliente consultarCliente(String dni) throws NotFoundException, PedidosClientesServiceException {
		try {
			return cliRepo.findById(dni)
					.orElseThrow(() -> new NotFoundException("No he encontrado el cliente con el dni " + dni));
		} catch (DataAccessException e) {
			throw new PedidosClientesServiceException("El articulo no existe en la BBDD ", e);
		}
	}

	@Override
	public Articulo consultarArticulo(Long idArticulo) throws NotFoundException, PedidosClientesServiceException {
		return artRepo.findById(idArticulo)
				.orElseThrow(() -> new NotFoundException("No he encontrado el artículo con el id " + idArticulo));
	}

	@Override
	public Pedido consultarPedido(String uuid) throws NotFoundException, PedidosClientesServiceException {
		try {
		return pedRepo.findById(UUID.fromString(uuid))
				.orElseThrow(() -> new NotFoundException("No he encontrado el pedido con el UUID " + uuid));
		}catch (DataAccessException e) {
			throw new PedidosClientesServiceException("El pedido no existe en la BBDD ", e);
		}
	}

}