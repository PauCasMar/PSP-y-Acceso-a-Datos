package service;

import model.Peliculas;

public interface PeliculaXmlService {

	public Peliculas importPeliculasFromXml() throws PeliculaXmlException;

}
