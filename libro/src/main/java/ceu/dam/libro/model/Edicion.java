package ceu.dam.libro.model;

public class Edicion {
	private Integer año;
	private String editorial;
	
	public Edicion() {
	}
	
	public Edicion(Integer año, String editorial) {
		super();
		this.año = año;
		this.editorial = editorial;
	}
	public Integer getAño() {
		return año;
	}
	public void setAño(Integer año) {
		this.año = año;
	}
	public String getEditorial() {
		return editorial;
	}
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	@Override
	public String toString() {
		return "Edicion [año=" + año + ", editorial=" + editorial + "]";
	}
	
	
}