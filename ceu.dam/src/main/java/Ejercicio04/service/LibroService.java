package Ejercicio04.service;

import java.util.List;
import Ejercicio04.model.Libro;

public interface LibroService {

	public void exportLibroToXML(List<Libro> libros, String pathFile) throws LibroXmlException;
	
	public List<Libro> importarLibroFromXml(String fileName) throws LibroXmlException;

}
