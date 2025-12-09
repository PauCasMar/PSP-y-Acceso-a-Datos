package ceu.dam.libro.service;

import java.util.List;

import ceu.dam.libro.model.Libro;


public interface LibroXmlService {

	public List<Libro> exportXML(String pathFile) throws LibroXMLExportException;

}