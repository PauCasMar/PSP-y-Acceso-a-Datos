package ceu.dam.ad.demo_xml_castillos.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import ceu.dam.ad.demo_xml_castillos.model.Caballero;
import ceu.dam.ad.demo_xml_castillos.model.Castillo;
import ceu.dam.ad.demo_xml_castillos.model.Castillos;
import ceu.dam.ad.demo_xml_castillos.model.Dragon;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.dataformat.xml.XmlMapper;

public class CastilloXmlServiceImpl implements CastilloXmlService {
	

	@Override
	public void exportCastilloToXmlJackson(String fileName, Castillo castillo) throws CastilloXmlException {
		//XmlMapper Mapper = new XmlMapper();
		JsonMapper mapper = new JsonMapper();
		File file = new File(fileName);
		mapper.writeValue(file, castillo);		
	}
	@Override
	public void exportCastillosToXmlJackson(String fileName, List<Castillo> castillos) throws CastilloXmlException {
		XmlMapper mapper = new XmlMapper();
		Castillos castillosObject = new Castillos();
		castillosObject.setCastillos(castillos);
		File file = new File(fileName);
		mapper.writeValue(file, castillosObject);		
	}	
	
	
	public Castillo importCastilloToXmlJackson(String fileName) throws CastilloXmlException {
		XmlMapper mapper = new XmlMapper();
		File file = new File(fileName);
		return mapper.readValue(file, Castillo.class);
	}
	
	
	@Override
	public void exportCastilloToXml(String fileName, Castillo castillo) throws CastilloXmlException {
		try {
			// Crear documento
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.newDocument();

			// Construir/rellenar documento

			// Creo el nodo raiz y lo añado al documento
			Element castilloTag = document.createElement("castillo");
			document.appendChild(castilloTag);

			Element fosoTag = document.createElement("foso");
			castilloTag.appendChild(fosoTag);
			fosoTag.setTextContent(castillo.getFoso().toString());

			// Anido unos elementos dentro de otros
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

			// Creo el resto de los nodos(Elementos)
			// Añadir texto a aquellos tags que lo tengan
			Element dragonesTag = document.createElement("dragones");
			castilloTag.appendChild(dragonesTag);

			for (Dragon dragon : castillo.getDragones()) {

				Element dragonTag = document.createElement("dragon");
				dragonesTag.appendChild(dragonTag);

				Element razaTag = document.createElement("raza");
				dragonTag.appendChild(razaTag);
				razaTag.setTextContent(dragon.getRaza());

				Element poderTag = document.createElement("poder");
				dragonTag.appendChild(poderTag);
				poderTag.setTextContent(String.valueOf(dragon.getPoder()));

				// Pongo atributos a los nodos que los lleven
				dragonTag.setAttribute("color", String.valueOf(dragon.getColor()));
				dragonTag.setAttribute("alas", String.valueOf(dragon.getAlas()));

			}

			// Exportar documento a fichero
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new File(fileName));
			transformer.transform(source, result);

		} catch (Exception e) {
			throw new CastilloXmlException("error generando XML", e);
		}

	}

	@Override
	public Castillo importarCastilloFromXml(String fileName) throws CastilloXmlException {
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			File file = new File(fileName);
			Document document = builder.parse(file);
			
			Element castilloTag = document.getDocumentElement();
			Castillo castillo = new Castillo();
			
			Element fosoTag = (Element)castilloTag.getElementsByTagName("foso").item(0);	
			castillo.setFoso(Boolean.valueOf(fosoTag.getTextContent()));
			
			Element caballerosTag = (Element)castilloTag.getElementsByTagName("caballeros").item(0);
			castillo.setCaballeros(new ArrayList<>());
			
			NodeList caballeroTagList = caballerosTag.getElementsByTagName("caballero");
			for (int i = 0; i < caballeroTagList.getLength(); i++) {
				Element caballeroTag= (Element) caballeroTagList.item(i);
				Caballero caballero = new Caballero();
				castillo.getCaballeros().add(caballero);
				
				Element caballoTag = (Element)caballeroTag.getElementsByTagName("caballo").item(0);
				caballero.setCaballo(caballoTag.getTextContent());
				
				Element escuderoTag = (Element)caballeroTag.getElementsByTagName("escudero").item(0);
				caballero.setEscudero(escuderoTag.getTextContent());
		
				Element edadTag = (Element)caballeroTag.getElementsByTagName("edad").item(0);
				caballero.setEdad(Integer.valueOf(edadTag.getTextContent()));
				
				Element nombreTag = (Element)caballeroTag.getElementsByTagName("nombre").item(0);
				caballero.setNombre(nombreTag.getTextContent());
				
				
			}
			
			Element dragonesTag = (Element)castilloTag.getElementsByTagName("dragones").item(0);
			castillo.setDragones(new ArrayList<>());
			
			NodeList dragonTagList = dragonesTag.getElementsByTagName("dragon");
			for (int i = 0; i < dragonTagList.getLength(); i++) {
				Element dragonTag = (Element) dragonTagList.item(i);
				Dragon dragon = new Dragon();
				castillo.getDragones().add(dragon);
				
				dragon.setAlas(Boolean.valueOf(dragonTag.getAttribute("alas")));
				dragon.setColor(dragonTag.getAttribute("color"));
				
				Element razaTag = (Element) dragonTag.getElementsByTagName("raza").item(0);
				dragon.setRaza(razaTag.getTextContent());				
				
				Element poderTag = (Element) dragonTag.getElementsByTagName("poder").item(0);
				dragon.setPoder(Integer.valueOf(poderTag.getTextContent()));
				
			}		
			
			return castillo;
		} catch (Exception e) {
			throw new CastilloXmlException("Error generando XML", e);
		}
		
	}

}
