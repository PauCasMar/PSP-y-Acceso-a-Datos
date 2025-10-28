package ceu.dam.ad.tema3.ejercicios.ejercicio01.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ceu.dam.ad.tema3.ejercicios.ejercicio01.model.Pelicula;
import ceu.dam.ad.tema3.ejercicios.ejercicio01.services.PeliculasException;
import ceu.dam.ad.tema3.ejercicios.ejercicio01.services.PeliculasService;

@Component
public class TestEj1 {
	
	@Autowired
	private PeliculasService service;

	public void test() {
		
		try {
			System.out.println("Todas las pelis que hay en la BBDD");
			List<Pelicula> peliculas = service.consultarPeliculas();
			peliculas.forEach(System.out::println);
			
			System.out.println("Peliculas con menos de 50 min");
			peliculas = service.consultarPeliculas(50);
			peliculas.forEach(System.out::println);
			
			
			
			
			
		} catch (PeliculasException e) {
			e.printStackTrace();
		}
	}

}
