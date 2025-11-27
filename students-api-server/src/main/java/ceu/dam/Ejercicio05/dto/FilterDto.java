package ceu.dam.Ejercicio05.dto;

import lombok.Data;


@Data
public class FilterDto {
	
	private String dni;
	private String email;
	private String name;
	private String lastName;
	private Integer age;
	private String gender;
	private String program;

}
