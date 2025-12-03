package Ejercicio04.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import Ejercicio04.model.Edicion;
import Ejercicio04.model.Libro;

public class LibroServiceImpl implements LibroService {


	@Override
	public void exportLibroToXML(List<Libro> libros, String pathFile) throws LibroXmlException{
		try {
			//Crear documento
			
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();
			
			//Construir/rellenar documento
			
			//Creo el nodo raiz y lo añado al documento
			Element librosTag = document.createElement("libros");
			document.appendChild(librosTag);
	
			
			//Anido unos elementos dentro de otros
			for (Libro libro : libros) {
				
				Element libroTag = document.createElement("libro");
				librosTag.appendChild(libroTag);
				libroTag.setAttribute("isbn", String.valueOf(libro.getIsbn()));
				
				Element tituloTag = document.createElement("titulo");
				libroTag.appendChild(tituloTag);
				tituloTag.setTextContent(libro.getTitulo());
							
				Element autoresTag = document.createElement("autores");
				libroTag.appendChild(autoresTag);
				
				for (String autor : libro.getAutores()) {
					
					Element autorTag = document.createElement("autor");
					libroTag.appendChild(autorTag);
					autorTag.setTextContent(autor);
				}
								
				Element edicionesTag = document.createElement("ediciones");
				libroTag.appendChild(edicionesTag);
				
				for (Edicion edicion : libro.getEdiciones()) {
					
					Element edicionTag = document.createElement("edicion");
					libroTag.appendChild(edicionTag);
					edicionTag.setTextContent(String.valueOf(edicion));
				
					Element yearTag = document.createElement("año");
					libroTag.appendChild(yearTag);
					yearTag.setTextContent(String.valueOf(edicion.getAño()));
					
					Element editorialTag = document.createElement("editorial");
					libroTag.appendChild(editorialTag);
					editorialTag.setTextContent(edicion.getEditorial());
				}
				
			}
			
			//Exportar documento a fichero
			TransformerFactory transformerFactory= TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result= new StreamResult(new File(pathFile));
			transformer.transform(source, result);
		
			
		}catch(Exception e) {
			throw new LibroXmlException("error generando XML", e);
		}	
		
	}
	
	public List<Libro> importarLibroFromXml(String fileName) throws LibroXmlException{
		
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			File file = new File(fileName);
			Document document = builder.parse(file);
			
			Element librosTag = document.getDocumentElement();
			List<Libro> libros = new ArrayList<>();
			
			NodeList libroTagList = librosTag.getElementsByTagName("libro");
		
			for (int i = 0; i < libroTagList.getLength(); i++) {
				Element libroTag= (Element) libroTagList.item(i);
				Libro libro = new Libro();
				libros.add(libro);
				libro.setIsbn(Integer.valueOf(libroTag.getAttribute("isbn")));
				
				Element tituloTag = (Element) libroTagList.item(0);
				libro.setTitulo(tituloTag.getTextContent());
				
				Element autoresTag= (Element) libroTag.getElementsByTagName("autores").item(0);
				libro.setAutores(new ArrayList<>());
				
				NodeList autorTagList = autoresTag.getElementsByTagName("autor");
				for (int j = 0; j < autorTagList.getLength(); j++) {
					Element autorTag = (Element) autorTagList.item(j);
					String autor = autorTag.getTextContent();
					libro.getAutores().add(autor);
					
				}
				
				Element edicionesTag= (Element) libroTag.getElementsByTagName("ediciones").item(0);
				libro.setEdiciones(new ArrayList<>());
				
				NodeList edicionTagList = edicionesTag.getElementsByTagName("edicion");
				for (int j = 0; j < edicionTagList.getLength(); j++) {
					Element edicionTag = (Element)edicionTagList.item(j);
					Edicion edicion = new Edicion();
					libro.getEdiciones().add(edicion);
					
					Element yearTag = (Element) edicionTag.getElementsByTagName("año").item(0);
					edicion.setAño(Integer.valueOf(yearTag.getTextContent()));
					Element editorialTag = (Element) edicionTag.getElementsByTagName("editorial").item(0);
					edicion.setEditorial(editorialTag.getTextContent());
									
				}
			}
							
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
		
		
	}

}
