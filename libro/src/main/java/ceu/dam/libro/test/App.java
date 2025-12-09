package ceu.dam.libro.test;

import java.util.List;

import ceu.dam.libro.model.Libro;
import ceu.dam.libro.service.LibroXMLExportException;
import ceu.dam.libro.service.LibroXmlService;
import ceu.dam.libro.service.LibroXmlServiceImpl;

/**
 * Hello world!
 *
 */
public class App {

	
	
	
    public static void main( String[] args ){
    	
    	LibroXmlService service = new LibroXmlServiceImpl();
    	
    	System.out.println( "Vamos a probar" );
    	try {
			List<Libro> libros = service.exportXML("C:\\Users\\Paula\\Desktop\\xmlLibros.xml");
			
			for (Libro libro : libros) {
				System.out.println(libro);
			}
		} catch (LibroXMLExportException e) {
			
			e.printStackTrace();
		}
        
    }
}
