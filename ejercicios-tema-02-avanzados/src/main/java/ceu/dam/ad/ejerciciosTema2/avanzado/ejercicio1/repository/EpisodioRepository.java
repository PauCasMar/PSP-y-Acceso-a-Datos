package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio1.model.Episodio;

@Repository
public interface EpisodioRepository extends JpaRepository<Episodio, Long> {


}
