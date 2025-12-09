package ceu.dam.ad.demo_xml_castillos.service;

import java.util.List;

import ceu.dam.ad.demo_xml_castillos.model.Castillo;

public interface CastilloXmlService {

	void exportCastilloToXml(String fileName, Castillo castillo) throws CastilloXmlException;

	Castillo importarCastilloFromXml(String string) throws CastilloXmlException;

	void exportCastilloToXmlJackson(String fileName, Castillo castillo) throws CastilloXmlException;

	void exportCastillosToXmlJackson(String fileName, List<Castillo> castillos) throws CastilloXmlException;

	
	
	
	
}