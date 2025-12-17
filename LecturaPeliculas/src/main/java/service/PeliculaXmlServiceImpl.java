package service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import model.Artista;
import model.Pelicula;
import model.Peliculas;

public class PeliculaXmlServiceImpl implements PeliculaXmlService {
	private static final Logger logger = LoggerFactory.getLogger(PeliculaXmlServiceImpl.class);

	@Override
	public Peliculas importPeliculasFromXml() throws PeliculaXmlException {

		Peliculas peliculaIndex = new Peliculas();

		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document xml = builder.parse(new File("C:\\Users\\Paula\\Desktop\\xmlPeliculas.xml"));
			Element peliculasRoot = xml.getDocumentElement();

			List<Pelicula> listaPeliculas = new ArrayList<>();
			peliculaIndex.setPeliculas(listaPeliculas);

			
			NodeList listaDirecciones = peliculasRoot.getElementsByTagName("pelicula");

			for (int i = 0; i < listaDirecciones.getLength(); i++) {
				Element peliculaTag = (Element) listaDirecciones.item(i);
				Pelicula pelicula = new Pelicula();
				{
					Element titulo = (Element) peliculaTag.getElementsByTagName("titulo").item(0);
					Element duracion = (Element) peliculaTag.getElementsByTagName("duracion").item(0);
					Element año = (Element) peliculaTag.getElementsByTagName("año").item(0);

					pelicula.setTitulo(titulo.getTextContent());
					pelicula.setDuracion(Integer.valueOf(duracion.getTextContent()));
					pelicula.setAño(Integer.valueOf(año.getTextContent()));
					listaPeliculas.add(pelicula);

					Element artistas = (Element) peliculaTag.getElementsByTagName("artistas").item(0);
					List<Artista> listaArtistas = new ArrayList<>();

					NodeList listaArtistasTag = artistas.getElementsByTagName("artista");

					for (int j = 0; j < listaArtistasTag.getLength(); j++) {
						Element artistaTag = (Element) listaArtistasTag.item(j);
						Artista artista = new Artista();

						String tipo = artistaTag.getAttribute("tipo");
						Element nombre = (Element) artistaTag.getElementsByTagName("nombre").item(0);
						Element nacionalidad = (Element) artistaTag.getElementsByTagName("nacionalidad").item(0);

						artista.setTipo(tipo);
						artista.setNacionalidad(nombre.getTextContent());
						artista.setNombre(nombre.getTextContent());

						listaArtistas.add(artista);

					}
					pelicula.setArtistas(listaArtistas);
				}
			}

		} catch (Exception e) {
			throw new PeliculaXmlException("Error leyendo XML", e);
		}

	
		return  peliculaIndex;
	}

}
