package ceu.dam.libro.service;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ceu.dam.libro.model.Largometraje;
import ceu.dam.libro.model.Persona;
import ceu.dam.libro.test.App;

public class PeliculaXmlServiceImpl implements PeliculasXmlService {
	
	
		Logger logger = Logger.getLogger(App.class);

	public void exportarXML(List<Largometraje> peliculas, String fichero) throws PeliculaXMLExportException {

		try {
			logger.info("Iniciando la construcción del archivo ejemplo en carpeta temporal");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xml = builder.newDocument();

			Element root = xml.createElement("peliculas");
			xml.appendChild(root);

			for (Largometraje pelicula : peliculas) {

				Element peliculaTag = xml.createElement("pelicula");
				root.appendChild(peliculaTag);

				Element tituloTag = xml.createElement("titulo");
				tituloTag.setTextContent(pelicula.getTitulo());
				peliculaTag.appendChild(tituloTag);
				logger.debug("Incluyendo título de la película");

				Element duracionTag = xml.createElement("duracion");
				duracionTag.setTextContent(String.valueOf(pelicula.getDuracion()));
				peliculaTag.appendChild(duracionTag);
				logger.info("Incluyendo duración de la película");

				Element añoTag = xml.createElement("año");
				añoTag.setTextContent(String.valueOf(pelicula.getAño()));
				peliculaTag.appendChild(añoTag);
				logger.info("Incluyendo año de la película");

				Element artistasTag = xml.createElement("artistas");
				peliculaTag.appendChild(artistasTag);

				for (Persona persona : pelicula.getActores()) {

					Element personaTag = xml.createElement("artista");
					artistasTag.appendChild(personaTag);
					// personaTag.setAttribute("tipo", persona.get);
					logger.info("Incluyendo artista de la película");

					Element nombreTag = xml.createElement("nombre");
					nombreTag.setTextContent(persona.getNombre());
					personaTag.appendChild(nombreTag);
					logger.info("Incluyendo nombre del artista de la película");

					Element nacionTag = xml.createElement("nacionalidad");
					nacionTag.setTextContent(persona.getNombre());
					personaTag.appendChild(nacionTag);
					logger.info("Incluyendo nacionalidad del artista de la película");

				}

			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(xml);
			StreamResult result = new StreamResult(new File("c:/temporal/peliculas.xml"));
			transformer.transform(source, result);

		} catch (Exception e) {
			logger.error("Erorr al generar el documento XML", e);
			throw new PeliculaXMLExportException("Error generando XML", e);
		}

	}

}
