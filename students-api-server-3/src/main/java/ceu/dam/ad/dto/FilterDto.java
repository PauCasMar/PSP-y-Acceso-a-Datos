package ceu.dam.ad.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import lombok.Data;


@Data
public class FilterDto {
	
	private String dni;
	private String firstname;
	private String lastName;
	@Email
	private String email;
	@Positive
	private Integer age;
	private String gender;
	private String program;

}
