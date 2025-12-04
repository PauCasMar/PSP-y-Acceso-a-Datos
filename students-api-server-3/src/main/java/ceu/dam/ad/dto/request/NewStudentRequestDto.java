package ceu.dam.ad.dto.request;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

@Data
public class NewStudentRequestDto {
	
	@NotBlank
	@Schema(description = "DNI del alumno")
	private String dni;
	@NotBlank
	private String firstName;
	@NotBlank
	private String lastName;
	@NotBlank
	@Email
	private String email;
	@NotNull
	@PastOrPresent
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private LocalDate dateOfBirth;
	
	private String gender;
	
	private String program;



}
