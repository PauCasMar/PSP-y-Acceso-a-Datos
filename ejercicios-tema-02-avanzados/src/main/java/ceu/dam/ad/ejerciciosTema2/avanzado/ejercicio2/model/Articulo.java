package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Articulo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descripcion;
	private String codBarras;
		
	@Override
	public String toString() {
		return "Articulo [id=" + id + ", descripcion=" + descripcion + ", codBarras=" + codBarras + "]";
	}
}
