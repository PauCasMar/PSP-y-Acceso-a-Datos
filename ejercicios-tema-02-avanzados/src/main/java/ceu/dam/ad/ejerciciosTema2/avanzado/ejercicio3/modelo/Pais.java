package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
@Data
public class Pais {
	@Id
	@Column(name="cod_pais")
	private String codigo;
	private String descripcion;
	

	@Override
	public String toString() {
		return codigo + " - " + descripcion;
	}
	
}
