package ceu.dam.ad.demo_xml_castillos.service;

import ceu.dam.ad.demo_xml_castillos.model.Castillo;

public interface CastilloXmlService {

	void exportCastilloToXml(String fileName, Castillo castillo) throws CastilloXmlException;

	
	
	
	
}