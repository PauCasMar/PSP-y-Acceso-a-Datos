package ceu.dam.peliculas.test;

import java.util.ArrayList;
import java.util.List;

import ceu.dam.peliculas.model.Largometraje;
import ceu.dam.peliculas.service.PeliculaXMLExportException;
import ceu.dam.peliculas.service.PeliculaXmlServiceImpl;
import ceu.dam.peliculas.service.PeliculasXmlService;

/**
 * Hello world!s
 *
 */
public class App {

	static PeliculasXmlService service = new PeliculaXmlServiceImpl();

	public static void main(String[] args) {

		try {
			System.out.println("Vamos a probar");

			List<Largometraje> peliculas = new ArrayList<>();
			String ruta = "";

			service.exportarXML(peliculas, ruta);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
}
