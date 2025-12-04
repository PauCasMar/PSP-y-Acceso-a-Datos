package ceu.dam.ad.dto.response;

import java.time.LocalDate;


import lombok.Data;


@Data
public class StudentResponseDto {
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
