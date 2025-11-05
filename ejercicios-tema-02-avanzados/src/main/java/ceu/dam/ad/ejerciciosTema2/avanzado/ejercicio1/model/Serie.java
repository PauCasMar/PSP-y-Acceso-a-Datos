package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio1.model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Serie {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String descripcion;
	
	@Column(name="fecha_estreno")
	private Date estreno;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="id_trailer", nullable = false)
	private Trailer trailer;
	
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name= "id_serie", nullable = false)
	private List<Temporada> temporadas;

	
}
