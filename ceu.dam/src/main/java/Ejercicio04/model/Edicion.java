package Ejercicio04.model;

import lombok.Data;

@Data
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

	
	
}
