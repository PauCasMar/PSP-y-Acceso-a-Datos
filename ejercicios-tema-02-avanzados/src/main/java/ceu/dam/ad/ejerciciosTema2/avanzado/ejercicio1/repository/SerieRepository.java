package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio1.model.Serie;
import java.util.List;

@Repository
public interface SerieRepository extends JpaRepository<Serie, Long> {
	
	List<Serie> findByDescripcionContaining(String descripcion);

}
