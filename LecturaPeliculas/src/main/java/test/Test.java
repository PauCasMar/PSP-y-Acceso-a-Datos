package test;

import model.Peliculas;
import service.PeliculaXmlException;
import service.PeliculaXmlService;
import service.PeliculaXmlServiceImpl;

public class Test {

	public static void main(String[] args) {

		PeliculaXmlService service = new PeliculaXmlServiceImpl();

		try {
			Peliculas peliculas = service.importPeliculasFromXml();
			
			
			System.out.println(peliculas.getPeliculas());
			
			
		} catch (PeliculaXmlException e) {
			e.printStackTrace();
		}

	}

}
