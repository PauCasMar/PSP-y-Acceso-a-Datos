package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {


}
