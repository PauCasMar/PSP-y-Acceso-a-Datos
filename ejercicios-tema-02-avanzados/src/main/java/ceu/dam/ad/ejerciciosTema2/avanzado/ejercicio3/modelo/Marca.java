package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio3.modelo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="marcas")
public class Marca {
	
	@Id
	private String codigo;
	
	private String nombreComercial;
	
	@ManyToOne
	@JoinColumn(name="cod_pais")
	private Pais pais;


	@ManyToMany
	@JoinTable(name="centro_comercial_marcas",
	joinColumns = {@JoinColumn(name = "cod_marca")},
	inverseJoinColumns = {@JoinColumn(name="uuid_centro")})
	private List<CentroComercial> centrosComerciales;
	
	
	public Marca() {
		super();
	}
	public Marca(String codigo, String nombreComercial, Pais pais) {
		super();
		this.codigo = codigo;
		this.nombreComercial = nombreComercial;
		this.pais = pais;
	}

	@Override
	public String toString() {
		return "Marca [codigo=" + codigo + ", nombreComercial=" + nombreComercial + ", pais=" + pais + "]";
	}
	
	
	

}
