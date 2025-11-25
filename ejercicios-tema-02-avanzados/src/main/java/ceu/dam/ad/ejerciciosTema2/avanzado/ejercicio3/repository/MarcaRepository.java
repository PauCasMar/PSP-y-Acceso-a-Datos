package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.modelo.Marca;
@Repository
public interface MarcaRepository extends JpaRepository<Marca, String>{

}
