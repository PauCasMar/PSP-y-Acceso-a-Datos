package ceu.dam.ad.demo_xml_castillos.service;


import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import ceu.dam.ad.demo_xml_castillos.model.Castillo;
import ceu.dam.ad.demo_xml_castillos.model.Dragon;


public class CastilloXmlServiceImpl implements CastilloXmlService {

	@Override
	public void exportCastilloToXml(String fileName, Castillo castillo) throws CastilloXmlException {
		try {
		//Crear documento
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.newDocument();
		
		//Construir/rellenar documento
		
		
		//Creo el nodo raiz y lo añado al documento
		Element castilloTag = document.createElement("castillo");
		document.appendChild(castilloTag);
		
		Element fosoTag = document.createElement("foso");
		castilloTag.appendChild(fosoTag);
		fosoTag.setTextContent(castillo.getFoso().toString());
		
		//Anido unos elementos dentro de otros
		Element caballerosTag = document.createElement("caballeros");
		castilloTag.appendChild(caballerosTag);
		
		for (int i = 0; i < castillo.getCaballeros().size(); i++) {
			Element caballeroTag = document.createElement("caballero");
			caballerosTag.appendChild(caballeroTag);
						
			Element nombreTag = document.createElement("nombre");
			caballeroTag.appendChild(nombreTag);
			nombreTag.setTextContent(castillo.getCaballeros().get(i).getNombre());
			
			Element caballoTag = document.createElement("caballo");
			caballeroTag.appendChild(caballoTag);
			caballoTag.setTextContent(castillo.getCaballeros().get(i).getCaballo());
			
			Element escuderoTag = document.createElement("escudero");
			caballeroTag.appendChild(escuderoTag);
			escuderoTag.setTextContent(castillo.getCaballeros().get(i).getEscudero());
			
			Element EdadTag = document.createElement("edad");
			caballeroTag.appendChild(EdadTag);
			EdadTag.setTextContent(String.valueOf(castillo.getCaballeros().get(i).getEdad()));
			
		}
		
		
		//Creo el resto de los nodos(Elementos)
		//Añadir texto a aquellos tags que lo tengan
		Element dragonesTag = document.createElement("dragones");
		castilloTag.appendChild(dragonesTag);
				
		for (Dragon dragon: castillo.getDragones()) {
			
			Element dragonTag = document.createElement("dragon");
			dragonesTag.appendChild(dragonTag);
						
			Element razaTag = document.createElement("raza");
			dragonTag.appendChild(razaTag);
			razaTag.setTextContent(dragon.getRaza());
			
			Element poderTag = document.createElement("poder");
			dragonTag.appendChild(poderTag);
			poderTag.setTextContent(String.valueOf(dragon.getPoder()));
			
			//Pongo atributos a los nodos que los lleven
			dragonTag.setAttribute("color", String.valueOf(dragon.getColor()));
			dragonTag.setAttribute("alas", String.valueOf(dragon.getAlas()));
			
		} 
				
		//Exportar documento a fichero
		TransformerFactory transformerFactory= TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		StreamResult result= new StreamResult(new File(fileName));
		transformer.transform(source, result);
		
		}catch(Exception e) {
			throw new CastilloXmlException("error generando XML", e);
		}
		

		
	}
}
