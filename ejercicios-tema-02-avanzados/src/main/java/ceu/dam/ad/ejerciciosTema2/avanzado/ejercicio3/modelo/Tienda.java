package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="tiendas")
public class Tienda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String ubicacion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_marca")
	private Marca marca;

	@Override
	public String toString() {
		return "Tienda [id=" + id + ", ubicacion=" + ubicacion + "]";
	}

}
