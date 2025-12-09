package ceu.dam.libro.model;

public class Persona {
	
	public static final String DIRECCION 		= "dirección";
	public static final String PRODUCCION 		= "producción";
	public static final String INTERPRETACION 	= "interpretación";
	
	private String nombre;
	private String nacionalidad;
	
	public Persona() {

	}

	public Persona(String nombre, String nacionalidad) {
		super();
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	@Override
	public String toString() {
		return "Persona [nombre=" + nombre + ", nacionalidad=" + nacionalidad + "]";
	}
	
	
}