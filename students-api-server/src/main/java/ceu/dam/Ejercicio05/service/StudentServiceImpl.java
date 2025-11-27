package ceu.dam.Ejercicio05.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ceu.dam.Ejercicio05.dto.FilterDto;
import ceu.dam.Ejercicio05.model.Student;
import ceu.dam.Ejercicio05.repository.StudentRepository;


@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository stuRepo;

	@Override
	public Student create(Student student) throws StudentDuplicateException {
		try {
			if (stuRepo.findByDni(student.getDni()).isPresent()
					|| stuRepo.findByEmail(student.getEmail()).isPresent()) {
				throw new StudentDuplicateException("This student already exists");
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		student.setCreatedAt(LocalDate.now());
		return stuRepo.save(student);
	}

	@Override
	public void remove(Long id) throws StudentNotfoundException {
		try {
			if (!stuRepo.findById(id).isPresent()) {
				throw new StudentNotfoundException("This student doens't exists in the Database");
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		stuRepo.deleteById(id);
	}

	@Override
	public Student findById(Long id) throws StudentNotfoundException {
		try {
			if (!stuRepo.findById(id).isPresent()) {
				throw new StudentNotfoundException("This student doens't exists in the Database");
			}
			return stuRepo.findById(id).get();
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<Student> findAll() throws StudentNotfoundException {
		try {
			if (!stuRepo.findAll().isEmpty()) {
				throw new StudentNotfoundException("This student doens't exists in the Database");
			}
			return stuRepo.findAll(Sort.by("dni").ascending());
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Student> findByAgeRange(Integer minAge, Integer maxAge) {
		try {
			LocalDate today = LocalDate.now();
			LocalDate maxDateBirth = today.minusYears(maxAge + 1);
			LocalDate minDateBirth = today.minusYears(minAge);

			return stuRepo.findAllByDateOfBirthBetween(minDateBirth, maxDateBirth, Sort.by("dateOfBirth").ascending());

		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}

	}
	public List<Student> search(FilterDto filter){
		try {
		Student student = new Student();
		student.setDni(filter.getDni());
		student.setEmail(filter.getEmail());
		student.setFirstName(filter.getName());
		student.setLastName(filter.getLastName());
		student.setGender(filter.getGender());
		student.setProgram(filter.getProgram());
		student.setDateOfBirth(LocalDate.now().minusYears(filter.getAge()));
						
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<Student> example = Example.of(student,matcher);
		return stuRepo.findAll(example);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
			
		}
		
	}
		
		
		
	

}
