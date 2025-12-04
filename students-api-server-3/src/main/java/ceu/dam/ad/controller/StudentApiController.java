package ceu.dam.ad.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ceu.dam.ad.dto.FilterDto;
import ceu.dam.ad.dto.request.NewStudentRequestDto;
import ceu.dam.ad.dto.response.StudentResponseDto;
import ceu.dam.ad.exception.StudentNotFoundException;
import ceu.dam.ad.model.Student;
import ceu.dam.ad.service.StudentDuplicateException;
import ceu.dam.ad.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/students")
@SecurityRequirement(name="ApiKeyAuthentication")
public class StudentApiController {
	@Autowired
	private StudentService service;

	//Este metodo debera usar post, mandar info por el body y crear un student DTO y devolverá otro dto
	@PostMapping
	@Operation(description="Crea un alumno en base de datos con los datos recibidos. Devuelve un estudiante")
	public StudentResponseDto create(@RequestBody @Valid NewStudentRequestDto studentDto) throws StudentDuplicateException {
		ModelMapper modelMapper = new ModelMapper();
		Student studentEntity= modelMapper.map(studentDto,  Student.class);
		Student studentCreated = service.create(studentEntity);
		return modelMapper.map(studentCreated, StudentResponseDto.class);		
	}

	//Este metodo usara DELETE, mandando url /ID, con path variable.
	@DeleteMapping("/{idStudent}")
	public void remove(@PathVariable Long idStudent) throws StudentNotFoundException {
		service.remove(idStudent);
		
	}
	
	//Este metodo usara GET, mandando url /ID, con path variable. y podrá usar un dto para devolver el student
	@GetMapping("/{idStudent}")
	public StudentResponseDto findById(Long id) throws StudentNotFoundException {
		Student student = service.findById(id);
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(student, StudentResponseDto.class);
	}

	//un metodo GET, con una lista de DTO, sin nada en la url
	@GetMapping()
	public List<StudentResponseDto> findAll() throws StudentNotFoundException {
		List<Student> students = service.findAll();
		ModelMapper modelMapper = new ModelMapper();
		return students.stream().map(s-> modelMapper.map(s,StudentResponseDto.class)).toList();
	}
	
	
	//	metodo GET con 2 request Param y la URL/students/age para no confundirlo con el de arriba
	@GetMapping("/age")
	public List<StudentResponseDto> findByAgeRange(@RequestParam(required=false) Integer minAge, @RequestParam(required=false) Integer maxAge) {

		if(minAge==null) {
			minAge= 0;
		}
		if(maxAge==null) {
			maxAge= Integer.MAX_VALUE;
		}
		
		List<Student> students = service.findByAgeRange(minAge, maxAge);
		ModelMapper modelMapper = new ModelMapper();
		return students.stream().map(s-> modelMapper.map(s,StudentResponseDto.class)).toList();
	
	}
	
	//metodo GET con url/search y usamos el filter DTO
	@GetMapping("/search")
	public List<StudentResponseDto> search(@RequestBody @Valid FilterDto filter) {
		List<Student> students = service.search(filter);
		ModelMapper modelMapper = new ModelMapper();
		return students.stream().map(s-> modelMapper.map(s,StudentResponseDto.class)).toList();
	

	}

}
