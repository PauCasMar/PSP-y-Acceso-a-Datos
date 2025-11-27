package ceu.dam.ad.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ceu.dam.ad.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
	Optional<Student> findByDni(String dni);
	Optional<Student> findByEmail(String email);
	List<Student> findAllByDateOfBirthBetween(LocalDate minBirth, LocalDate maxBirth, Sort order);

}
