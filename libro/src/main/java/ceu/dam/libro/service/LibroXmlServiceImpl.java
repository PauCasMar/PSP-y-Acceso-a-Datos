package ceu.dam.libro.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import ceu.dam.libro.model.Edicion;
import ceu.dam.libro.model.Libro;


public class LibroXmlServiceImpl implements LibroXmlService {

	@Override
	public List<Libro> exportXML(String pathFile) throws LibroXMLExportException {
		try {
			// Crear documento
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			// Creo document que será el xml parseando el fichero donde está el xml
			Document xml = builder.parse(new File("C:\\Users\\Paula\\Desktop\\xmlLibros.xml"));

			// Obtengo el nodo raiz
			Element root = xml.getDocumentElement();
			List<Libro> libros = new ArrayList<>();

			// Obtengo los elementos

			NodeList listaLibros = root.getElementsByTagName("libro");
			for (int i = 0; i < listaLibros.getLength(); i++) {
				Libro newLibro = new Libro();
				Element libro = (Element) listaLibros.item(i);
				Element titulo = (Element) libro.getElementsByTagName("titulo").item(0);
				
				newLibro.setIsbn(Integer.parseInt(libro.getAttribute("isbn")));
				newLibro.setTitulo(titulo.getTextContent());
				libros.add(newLibro);

				NodeList listaAutores = libro.getElementsByTagName("autores");
				List<String> autores = new ArrayList<>();
				for (int j = 0; j < listaAutores.getLength(); j++) {
					Element autor = (Element) listaAutores.item(j);	
					autores.add(autor.getTextContent());
				}
				newLibro.setAutores(autores);				

				NodeList listaEdiciones = libro.getElementsByTagName("ediciones");
				List<Edicion> ediciones = new ArrayList<>();
				for (int k = 0; k < listaEdiciones.getLength(); k++) {
					Edicion newEdicion = new Edicion();
					Element edicion = (Element) listaEdiciones.item(k);
					Element año = (Element) edicion.getElementsByTagName("año").item(0);
					Element editorial = (Element) edicion.getElementsByTagName("editorial").item(0);
					
					newEdicion.setAño(Integer.valueOf(año.getTextContent()));
					newEdicion.setEditorial(editorial.getTextContent());
					ediciones.add(newEdicion);
				}
				newLibro.setEdiciones(ediciones);

			}
			return libros;

		} catch (Exception e) {
			throw new LibroXMLExportException("Error generando XML", e);
		}

	}
}