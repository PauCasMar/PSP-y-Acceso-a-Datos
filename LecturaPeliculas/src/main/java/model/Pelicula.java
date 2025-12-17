package model;

import java.util.List;

import lombok.Data;

@Data
public class Pelicula {

	private String titulo;
	private Integer duracion;
	private Integer año;
	private List<Artista> artistas;

}
