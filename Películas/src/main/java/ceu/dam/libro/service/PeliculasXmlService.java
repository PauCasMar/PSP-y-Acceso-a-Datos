package ceu.dam.libro.service;


import java.util.List;
import ceu.dam.libro.model.Largometraje;

public interface PeliculasXmlService {

	public void exportarXML(List<Largometraje> peliculas, String fichero) throws PeliculaXMLExportException;

}