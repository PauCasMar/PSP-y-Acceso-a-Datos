package ceu.dam.ad.tema3.ejercicios.ejercicio05.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class User {

	private Long id;
	private String username;
	private String email;
	private String password;
	private LocalDate createdDate;
	private LocalDate lastLoginDate;
	
	
	

}
