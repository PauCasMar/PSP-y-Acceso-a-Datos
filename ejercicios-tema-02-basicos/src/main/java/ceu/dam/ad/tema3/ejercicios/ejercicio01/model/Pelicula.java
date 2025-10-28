package ceu.dam.ad.tema3.ejercicios.ejercicio01.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="film")
public class Pelicula {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="film_id")
	private Integer id;
	
	@Column(name = "title")
	private String nombre;
	
	@Column(name = "length")
	private Integer longitud;
	
	

}
