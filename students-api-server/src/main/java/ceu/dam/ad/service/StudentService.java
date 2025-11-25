package ceu.dam.ad.service;

import java.util.List;

import ceu.dam.ad.model.Student;

public interface StudentService {
	
	
	/**Creará el alumno en BBDD. La fecha de alta es algo
 * que el servicio debe registrar automáticamente. Si ya existe alumno con el
 * mismo DNI o Email lanzará un StudentDuplicateException. Devolverá el alumno
 * creado con todos sus datos. */
	public Student create(Student student) throws StudentDuplicateException;
		
	
	/**Eliminará el alumno con el id indicado en BBDD. Si el alumno no existe,
	  lanzará StudentNotFoundException. */
	public void remove(Long id) throws StudentNotFoundException;
		
	
	/**Devolverá el alumno con el id indicado. Si no existe,
 * lanzará StudentNotFoundException*/
	public Student findById(Long id) throws StudentNotFoundException;

    /**findAll(). Devolverá todos los alumnos
 * ordenados por dni ascendente. Si no hay ninguno, lanzará
 * StudentNotFoundException.*/
	public List<Student> findAll() throws StudentNotFoundException;

	/** findByAgeRange(Integer minAge, Integer maxAge).
 * Devolverá todos los alumnos con edades comprendidas entre la mínima y máxima
 * recibidas (ambas inclusive). La lista estará ordenada por edad ascendente. Si
 * no hay ninguno, devolverá una lista vacía.*/
    public List<Student> findByAgeRange(Integer minAge, Integer maxAge);

    /** findByAgeRange(Integer minAge, Integer maxAge).
 * Devolverá todos los alumnos con edades comprendidas entre la mínima y máxima
 * recibidas (ambas inclusive). La lista estará ordenada por edad ascendente. Si
 * no hay ninguno, devolverá una lista vacía.*/
   /*public List<Student> search(FilterDto filter);*/
}


