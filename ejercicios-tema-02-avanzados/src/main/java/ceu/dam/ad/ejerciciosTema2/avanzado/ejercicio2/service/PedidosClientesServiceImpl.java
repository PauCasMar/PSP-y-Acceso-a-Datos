package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.service;
 
import java.util.List;
import java.util.UUID;

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
	
	ArticuloRepository artRepo;
	ClienteRepository cliRepo;
	PedidoRepository pedRepo;
  
	@Override
	@Transactional
	public void crearCliente(Cliente cliente) throws PedidosClientesServiceException {
		cliRepo.save(cliente);
		}
 
 
	@Override
	@Transactional
	public Pedido crearPedido(Pedido pedido) throws PedidosClientesServiceException {
		try {
		if(!cliRepo.findById(pedido.getCliente().getDni()).isEmpty()){
			List<PedidoLinea> lineas = pedido.getLineas();
			Integer i = 0;
			for (PedidoLinea pedidoLinea : lineas) {
			i++;
				pedidoLinea.setNumLinea(i);
				if (artRepo.findById(pedidoLinea.getArticulo().getId()) == null) {
					throw new NotFoundException("El artículo no existe en la BBDD ");
				}				
			}
		}
		return pedRepo.save(pedido);

		}catch(NotFoundException e) {
			throw new PedidosClientesServiceException ("El artículo no existe en la BBDD ",e);
		}
		
	}
 
 
	@Override
	@Transactional
	public Articulo crearArticulo(Articulo articulo) throws PedidosClientesServiceException {
		return artRepo.save(articulo);
	}
	@Override
	@Transactional
	public void actualizarCliente(Cliente cliente) throws PedidosClientesServiceException {
		cliRepo.save(cliente);
		
	}
 
 
	@Override
	public Cliente consultarCliente(String dni) throws NotFoundException, PedidosClientesServiceException {
		return cliRepo.findById(dni).orElseThrow(()-> new NotFoundException("No he encontrado el cliente con el dni " +  dni));
	}
 
 
	@Override
	public Articulo consultarArticulo(Long idArticulo) throws NotFoundException, PedidosClientesServiceException {
		return artRepo.findById(idArticulo).orElseThrow(()-> new NotFoundException("No he encontrado el artículo con el id " +  idArticulo));
	}
 
 
	@Override
	public Pedido consultarPedido(String uuid) throws NotFoundException, PedidosClientesServiceException {
		return pedRepo.findById(UUID.fromString(uuid)).orElseThrow(()-> new NotFoundException("No he encontrado el pedido con el UUID " +  uuid));
		
	}

}