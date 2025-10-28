package ceu.dam.ad.tema3.ejercicios.ejercicio04.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.ad.tema3.ejercicios.ejercicio04.modelo.PedidoLinea;
import ceu.dam.ad.tema3.ejercicios.ejercicio04.modelo.PedidoLineaKey;
import java.util.List;


@Repository
public interface PedidoLineaRepository extends JpaRepository<PedidoLinea,PedidoLineaKey> {
	
	List<PedidoLinea> findByIdPedido(Long idPedido);	
	

}
