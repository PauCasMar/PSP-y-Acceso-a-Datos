package ceu.dam.ad.tema3.ejercicios.ejercicio06.modelo;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PedidoLinea {

	private Long idLinea;
	private Integer numLinea;
	private String articulo;
	private BigDecimal precio;
	

}
