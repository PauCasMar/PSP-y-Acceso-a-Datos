package ejercicio1.App;

import ejercicio1.Services.PeliculaService;
import ejercicio1.Services.PeliculaServiceException;

public class App {

	public static void main(String[] args) throws PeliculaServiceException {
		PeliculaService service = new PeliculaService();
	//	System.out.println(service.findShortFilms());
		
		service.findShortFilms().stream().forEach(f->System.out.println(f.getId() + " - " + f.getTitulo() + " duración: " + f.getDuracion() + " minutos."));
	
		
		
	}
}
