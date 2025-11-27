package ceu.dam.Ejercicio05.model;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Student {
	@Id
	private Long id;
	private String dni;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate dateOfBirth;
	private String gender;
	private String program;
	private LocalDate createdAt;
}
