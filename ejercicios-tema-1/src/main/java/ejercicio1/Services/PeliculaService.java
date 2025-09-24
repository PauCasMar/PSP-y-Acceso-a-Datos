package ejercicio1.Services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import ejercicio1.Dao.PeliculasDao;
import ejercicio1.Model.Pelicula;

public class PeliculaService extends Service {
	private PeliculasDao d;

	public List<Pelicula> findShortFilms() throws PeliculaServiceException {
		d = new PeliculasDao();
		// 1. abrir conexión

		try (Connection c = abrirConexionSakilaCentral()) {
			// invocar el metodo dao.findAll(conn)
			List<Pelicula> allShortFilms = d.findAll(c);

			// con la lista que me devuelve, filtrar las cortas y llenar otra lista
			allShortFilms = allShortFilms.stream().filter(p -> p.getDuracion() < 100).toList();
			// devolver la nueva lista filtrada
			return allShortFilms;
			// capturar excepciones
			// filtrar las cortas y devolver una lista

		} catch (SQLException e) {
			throw new PeliculaServiceException("Error SQL Exception");
			
			
		}

	}

}
