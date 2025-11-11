package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, UUID> {


}
