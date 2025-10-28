package ceu.dam.ad.tema3.ejercicios.ejercicio01.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceu.dam.ad.tema3.ejercicios.ejercicio01.model.Pelicula;
import ceu.dam.ad.tema3.ejercicios.ejercicio01.repository.PeliculasRepository;

@Service
public class PeliculasService {

	private static final Logger log = LoggerFactory.getLogger(PeliculasService.class);
	
	@Autowired
	private PeliculasRepository repo; //El repo equivale a un dao
	
	public Pelicula crearPelicula(Pelicula peli) throws PeliculasException {
		repo.save(peli);
		return peli;
	}
	
	
	public List<Pelicula> consultarPeliculas() throws PeliculasException {
		return consultarPeliculas(100);
	}
	
	public List<Pelicula> consultarPeliculas(Integer min) throws PeliculasException{
		
		return repo.findAll().stream()
				.filter(p -> p.getLongitud() < min)
				.toList();
	}

	



}
