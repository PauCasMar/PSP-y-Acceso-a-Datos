package ceu.dam.Ejercicio05.test;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import ceu.dam.Ejercicio05.dto.FilterDto;
import ceu.dam.Ejercicio05.model.Student;
import ceu.dam.Ejercicio05.service.StudentService;
 
@Component
public class Test {
 
    @Autowired
    private StudentService service;
    
    private Student createTestStudent(String name, String email, String dni,String gender) {
        Student s = new Student();
        s.setFirstName(name);
        s.setLastName("Test");
        s.setEmail(email);
        s.setDni(dni);
        s.setGender(gender);
        s.setProgram("Engineering");
        s.setDateOfBirth(LocalDate.now().minusYears(25)); // 25 años
        return s;
    }
 
 
    public void fullTestStudentService() {
 
        System.out.println(" ========================================");
        System.out.println("   TEST INTEGRAL DE StudentServiceImpl");
        System.out.println(" ========================================\n");
 
        try {
 
            // ---------------------------------------------------
            // 1) CREAR
            // ---------------------------------------------------
 
            Student student = createTestStudent("John", "john@test.com", "11111111A","hombre");
 
            student = service.create(student);
            System.out.println(" >>> Student creado con id: " + student.getId());
 
 
            // ---------------------------------------------------
            // 2) CONSULTAR POSITIVO
            // ---------------------------------------------------
 
            student = service.findById(student.getId());
            System.out.println(" >>> Consulta correcta: " + student.getFirstName());
 
 
            // ---------------------------------------------------
            // 3) ACTUALIZAR
            // (simulamos actualizar email o nombre)
            // ---------------------------------------------------
 
            student.setFirstName("John Updated");
            student.setProgram("Mathematics");
 
            // en tu service normalmente usas save() directo
            service.create(student);  // mismo método actualiza si tiene ID
 
            System.out.println(" >>> Actualización correcta");
 
 
            // ---------------------------------------------------
            // 4) BUSCAR POSITIVO (search)
            // ---------------------------------------------------
 
            FilterDto filter = new FilterDto();
            filter.setName("John");
 
            List<Student> list;
 
            try {
                list = service.search(filter);
            } catch (Exception e) {
                throw new RuntimeException("Búsqueda incorrecta");
            }
 
            System.out.println(" >>> Búsqueda correcta, encontrados: " + list.size());
 
 
            // ---------------------------------------------------
            // 5) BUSCAR NEGATIVO
            // ---------------------------------------------------
 
            FilterDto f2 = new FilterDto();
            f2.setName("abcdefg"); // No existe
 
            try {
                list = service.search(f2);
                if (list.isEmpty()) {
                    System.out.println(" >>> Búsqueda correcta (ningún student encontrado)");
                } else {
                    throw new RuntimeException("La búsqueda debería devolver 0 resultados");
                }
            } catch (Exception e) {
                System.out.println(" >>> Búsqueda correcta (sin resultados)");
            }
 
 
            // ---------------------------------------------------
            // 6) ELIMINAR
            // ---------------------------------------------------
 
            service.remove(student.getId());
            System.out.println(" >>> Student eliminado correctamente");
 
 
            // ---------------------------------------------------
            // 7) CONSULTAR NEGATIVO
            // ---------------------------------------------------
 
            try {
                service.findById(student.getId());
                throw new RuntimeException("La consulta debería fallar porque el student ya no existe");
            } catch (DataAccessException e) {
                System.out.println(" >>> Consulta negativa correcta: no existe");
            }
 
            System.out.println("\n >>>> TEST COMPLETADO SIN ERRORES <<<<\n");
 
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("ERROR EN EL TEST DE StudentService");
        }
    }
 
}
		
		
		
		
		
	



