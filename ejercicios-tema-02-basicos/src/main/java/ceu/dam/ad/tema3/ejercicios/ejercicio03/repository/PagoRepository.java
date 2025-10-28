package ceu.dam.ad.tema3.ejercicios.ejercicio03.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.ad.tema3.ejercicios.ejercicio03.model.Pago;

@Repository	
public interface PagoRepository extends JpaRepository<Pago, Integer>{
	
	List<Pago> findAllByCustomerId(Integer customerId);

}
