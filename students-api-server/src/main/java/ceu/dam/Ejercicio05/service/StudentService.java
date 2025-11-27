package ceu.dam.Ejercicio05.service;

import java.util.List;

import ceu.dam.Ejercicio05.dto.FilterDto;
import ceu.dam.Ejercicio05.model.Student;

public interface StudentService {
	
	/**Creará el alumno en BBDD. La fecha de alta es algo que el servicio debe registrar automáticamente. 
	*Si ya existe alumno con el mismo DNI o Email lanzará un StudentDuplicateException. Devolverá el alumno creado con todos sus datos. 
	 * @throws StudentDuplicateException */
	
	public Student create(Student student) throws StudentDuplicateException;
	
	/**Eliminará el alumno con el id indicado en BBDD. Si el alumno no existe, lanzará StudentNotFoundException.
	 * @throws StudentNotfoundException */ 
	
	public void remove(Long id) throws StudentNotfoundException;
	
	/**Devolverá el alumno con el id indicado. Si no existe, lanzará StudentNotFoundException.
	 * @throws StudentNotfoundException */
	public Student findById(Long id) throws StudentNotfoundException;
	
	/**Devolverá todos los alumnos ordenados por dni ascendente. Si no hay ninguno, lanzará StudentNotFoundException.
	 * @throws StudentNotfoundException */
	public List<Student> findAll() throws StudentNotfoundException;
	
	/**Devolverá todos los alumnos con edades comprendidas entre la mínima y máxima recibidas (ambas inclusive). 
	 * La lista estará ordenada por edad ascendente. Si no hay ninguno, devolverá una lista vacía. */
	public List<Student> findByAgeRange(Integer minAge, Integer maxAge);

	/**	Devolverá todos los alumnos que cumplan con todos los filtros indicados
	 ignorando mayúsculas/minúsculas y aplicando un LIKE en las cadenas. Si no hay resultados,
	  devolverá lista vacía. La clase FilterDto tendrá:*/
	public List<Student> search(FilterDto filter);

}
