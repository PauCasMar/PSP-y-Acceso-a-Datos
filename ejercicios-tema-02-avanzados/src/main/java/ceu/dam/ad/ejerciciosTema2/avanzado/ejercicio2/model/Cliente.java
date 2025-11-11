package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
	
	@Id
	private String dni;
	private String nombre;
	private String apellidos;
	
	@OneToMany(mappedBy = "cliente")	
	private Set<Pedido> pedidos;
	
	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + "]";
	}
	
	
}
