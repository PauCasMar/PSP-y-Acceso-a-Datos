package Ejercicio04.Test;

import java.util.List;

import Ejercicio04.model.Libro;
import Ejercicio04.service.LibroService;
import Ejercicio04.service.LibroServiceImpl;
import Ejercicio04.service.LibroXmlException;


public class Test1 {

	public static void main(String[] args) {

		// Castillo castillo = createCastillo();
		LibroService service = new LibroServiceImpl();

	//	List<Libro> libros = Libro.createRandomList(5);

		try {
			List<Libro> libros = service.importarLibroFromXml("C:\\Users\\pcastro0107\\Desktop\\PSP-y-Acceso-a-Datos\\xmlLibros.xml");
			System.out.println("Libros creados aleatoriamente");
			for (Libro libro : libros) {
				System.out.println(libro);
			}
		} catch (LibroXmlException e) {
			e.printStackTrace();
		}

		
	}
}
