package ceu.dam.ad.tema3.ejercicios.ejercicio06.modelo;

import java.time.LocalDate;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity(name="pedidoEj6")
@Table(name="pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id_pedido")
	private Long idPedido;
	private LocalDate fechaPedido;
	private LocalDate fechaEntrega;
	private String cliente;
	
	@Transient
	private List<PedidoLinea> lineas;
	
	

}
