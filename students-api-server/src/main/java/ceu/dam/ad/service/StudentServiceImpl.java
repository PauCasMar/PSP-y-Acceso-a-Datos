package ceu.dam.ad.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import ceu.dam.ad.model.Student;
import ceu.dam.ad.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepo;

	@Override
	public Student create(Student student) throws StudentDuplicateException {
		try {
			student.setCreatedAt(LocalDate.now());
			if (studentRepo.findById(student.getId()).isPresent()) {
				throw new StudentDuplicateException("This student already exists");
			}
			if (studentRepo.findByEmail(student.getEmail()).isPresent()) {
				throw new StudentDuplicateException("This student already exists");
				
			} else {
				return studentRepo.save(student);
			}
		} catch (DataAccessException e) {
			e.printStackTrace();

		}
		return student;

	}

	@Override
	public void remove(Long id) throws StudentNotFoundException {
		
		if(!studentRepo.findById(id).isPresent()) {
			throw new StudentNotFoundException ("The indicated id has not result in any data");
		}
		studentRepo.findById(id).;
		
		
		
	}

	@Override
	public Student findById(Long id) throws StudentNotFoundException {

		return null;
	}

	@Override
	public List<Student> findAll() throws StudentNotFoundException {

		return null;
	}

	@Override
	public List<Student> findByAgeRange(Integer minAge, Integer maxAge) {

		return null;
	}
}