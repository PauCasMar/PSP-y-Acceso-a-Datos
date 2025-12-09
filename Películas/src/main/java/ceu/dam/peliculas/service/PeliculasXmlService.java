package ceu.dam.peliculas.service;


import java.util.List;

import ceu.dam.peliculas.model.Largometraje;

public interface PeliculasXmlService {

	public void exportarXML(List<Largometraje> peliculas, String fichero) throws PeliculaXMLExportException;

}