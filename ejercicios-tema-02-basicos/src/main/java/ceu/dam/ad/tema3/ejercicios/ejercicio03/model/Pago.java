package ceu.dam.ad.tema3.ejercicios.ejercicio03.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="payment")
public class Pago {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="payment_id")
	private Integer id;
	
	@Column(name="customer_id")
	private Integer customerId;
	
	@Column(name="payment_date")
	private LocalDate fecha;
	
	@Column(name="amount")
	private BigDecimal importe;
	
	
}
