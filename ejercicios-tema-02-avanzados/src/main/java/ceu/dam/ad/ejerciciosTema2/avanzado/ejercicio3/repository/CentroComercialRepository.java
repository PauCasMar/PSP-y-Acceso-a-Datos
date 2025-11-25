package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.modelo.CentroComercial;
@Repository
public interface CentroComercialRepository extends JpaRepository<CentroComercial, UUID>{

}
