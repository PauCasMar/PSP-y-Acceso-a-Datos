package ceu.dam.ad.ejerciciosTema2.avanzado.ejercicio2.model;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class PedidoLinea {
	
	@Id
	@GeneratedValue
	@JdbcTypeCode(java.sql.Types.VARCHAR)
	private UUID uidLinea;
	private Integer numLinea;
	private Integer cantidad;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	@JoinColumn(name="id_articulo")
	private Articulo articulo;	
	
	@Override
	public String toString() {
		return "PedidoLinea [uidLinea=" + uidLinea + ", numLinea=" + numLinea + ", cantidad=" + cantidad + ", articulo="
				+ articulo + "]";
	}
}
