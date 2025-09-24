package ejercicio1.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ejercicio1.Model.Pelicula;


public class PeliculasDao {

	public List<Pelicula> findAll(Connection c) throws SQLException {
		// 1. Abrir conexion

		// 2. Query
		String sql = "SELECT * FROM FILM";

		// 3. prepared Statment
		PreparedStatement stmt = c.prepareStatement(sql);

		// 5. Ejecutar
		ResultSet rs = stmt.executeQuery();

		// 6. Leer resultSet
		// 7. crear lista y la lleno recorriendo el result set
		List<Pelicula> allFilms = new ArrayList<>();

		while (rs.next()) {
			Pelicula p = new Pelicula();
			p.setId(rs.getInt("film_id"));
			p.setTitulo(rs.getString("title"));
			p.setDuracion(rs.getInt("length"));
			allFilms.add(p);

		}
		// 8. return lista
		return allFilms;

	}

}
