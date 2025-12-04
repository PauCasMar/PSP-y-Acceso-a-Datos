package ceu.dam.ad.service;

import java.time.LocalDate;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ceu.dam.ad.dto.FilterDto;
import ceu.dam.ad.exception.StudentNotFoundException;
import ceu.dam.ad.model.Student;
import ceu.dam.ad.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepository stuRepo;

	@Override
	public Student create(Student student) throws StudentDuplicateException {
		if (stuRepo.findByDni(student.getDni()).isPresent() || stuRepo.findByEmail(student.getEmail()).isPresent()) {
			throw new StudentDuplicateException("This student already exists");
		}

		student.setCreatedAt(LocalDate.now());
		return stuRepo.save(student);
	}

	@Override
	public void remove(Long id) throws StudentNotFoundException {
		findById(id);
		stuRepo.deleteById(id);
	}

	@Override
	public Student findById(Long id) throws StudentNotFoundException {

		return stuRepo.findById(id).orElseThrow(() -> new StudentNotFoundException("This student doens't exists in the Database"));

	}

	@Override
	public List<Student> findAll() throws StudentNotFoundException {

		if (!stuRepo.findAll().isEmpty()) {
			throw new StudentNotFoundException("There are no students in the databa");
		}
		return stuRepo.findAll(Sort.by("dni").ascending());

	}

	@Override
	public List<Student> findByAgeRange(Integer minAge, Integer maxAge) {

		LocalDate maxDateBirth = LocalDate.now().minusYears(maxAge);
		LocalDate minDateBirth = LocalDate.now().minusYears(minAge);
		return stuRepo.findAllByDateOfBirthBetween(maxDateBirth, minDateBirth, Sort.by("dateOfBirth").descending());
	}

	public List<Student> search(FilterDto filter) {
		Student student = new ModelMapper().map(filter, Student.class);
		ExampleMatcher matcher = ExampleMatcher.matchingAll().withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);
		Example<Student> example = Example.of(student, matcher);
		List<Student> students=  stuRepo.findAll(example);
		
		if(filter.getAge()!=null){
			return students.stream().filter(s->s.getAge().equals(filter.getAge())).toList();
		}
		return students;
	}

}
