package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio1.repository;

import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio1.model.Trailer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrailerRepository extends JpaRepository<Trailer, Long>{


}
