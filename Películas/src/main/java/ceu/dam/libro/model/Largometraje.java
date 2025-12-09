package ceu.dam.libro.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Largometraje {
	private String titulo;
	private Integer duracion;
	private Integer año;
	private Persona direccion;
	private Persona produccion;
	private List<Persona> actores;
	
	public Largometraje() {
		
	}
	
	

	public Largometraje(String titulo, Integer duracion, Integer año) {
		super();
		this.titulo = titulo;
		this.duracion = duracion;
		this.año = año;
	}



	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Integer getAño() {
		return año;
	}

	public void setAño(Integer año) {
		this.año = año;
	}

	public Persona getDireccion() {
		return direccion;
	}

	public void setDireccion(Persona direccion) {
		this.direccion = direccion;
	}

	public Persona getProduccion() {
		return produccion;
	}

	public void setProduccion(Persona produccion) {
		this.produccion = produccion;
	}

	public List<Persona> getActores() {
		return actores;
	}

	public void setActores(List<Persona> actores) {
		this.actores = actores;
	}
	
	
	

	@Override
	public String toString() {
		return "Largometraje [titulo=" + titulo + ", duracion=" + duracion + ", año=" + año + ", direccion=" + direccion
				+ ", produccion=" + produccion + ", actores=" + actores + "]";
	}



	public static List<Largometraje> createRandomList(Integer numPeliculas) {
		List<Largometraje> peliculas = new ArrayList<>();
		for (int i = 1; i <= numPeliculas; i++) { 
			peliculas.add(createRandom());
		}

		return peliculas;
		
	}
	
	private static Largometraje createRandom() {
		Random random = new Random();
		Integer duracion = random.nextInt(80)+80;
		Integer año = random.nextInt(60)+1950;
		Integer numActores = random.nextInt(4)+3;
		Integer numPalabras = random.nextInt(2)+2;
		String titulo = generateRandomWords(numPalabras);
		
		Largometraje pelicula = new Largometraje(titulo, duracion, año);

		Integer numNacionalidadDireccion = random.nextInt(11);
		Integer numNacionalidadProduccion = random.nextInt(11);
		pelicula.setDireccion(createRandomPersona(numNacionalidadDireccion));
		pelicula.setProduccion(createRandomPersona(numNacionalidadProduccion));
		
		pelicula.setActores(new ArrayList<>());
		for (int i = 0; i < numActores; i++) {
			Integer numNacionalidadActor = random.nextInt(11);
			pelicula.getActores().add(createRandomPersona(numNacionalidadActor));
		}
		return pelicula;
	}
	
	private static Persona createRandomPersona(Integer random) {
		String[] nacionalidades = new String[] {"España", "Portugal", "China", "Suiza", "Francia", "Marruecos", "India", "Indonesia", "Bélgica", "Holanda", "Perú"};
		String nombre = generateRandomWords(3);
		return new Persona(nombre, nacionalidades[random]);
	}
	
	private static String generateRandomWords(int numberOfWords){
		Random random = new Random();
	    String randomStrings = "";
	    for(int i = 0; i < numberOfWords; i++)   {
	        char[] word = new char[random.nextInt(8)+3]; 
	        for(int j = 0; j < word.length; j++)  {
	            word[j] = (char)('a' + random.nextInt(26));
	        }
	        randomStrings += new String(word);
	        if (i<numberOfWords-1) {
	        	randomStrings += " ";
	        }
	    }
	    return randomStrings;
	}
	
	
}