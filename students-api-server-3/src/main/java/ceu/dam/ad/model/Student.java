package ceu.dam.ad.model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.aot.generate.GeneratedTypeReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder 
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name="students")
public class Student {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String dni;
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate dateOfBirth;
	private String gender;
	private String program;
	private LocalDate createdAt;

 public Integer getAge() {
	  
	 return dateOfBirth.until(LocalDate.now()).getYears();
 }
}