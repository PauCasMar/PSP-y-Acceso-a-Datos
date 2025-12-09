package ceu.dam.ad.demo_xml_castillos.test;

import java.util.ArrayList;
import java.util.List;

import ceu.dam.ad.demo_xml_castillos.model.Caballero;
import ceu.dam.ad.demo_xml_castillos.model.Castillo;
import ceu.dam.ad.demo_xml_castillos.model.Dragon;
import ceu.dam.ad.demo_xml_castillos.service.CastilloXmlException;
import ceu.dam.ad.demo_xml_castillos.service.CastilloXmlService;
import ceu.dam.ad.demo_xml_castillos.service.CastilloXmlServiceImpl;

public class Test {

	public static void main(String[] args) {
		
		//Castillo castillo = createCastillo();
		CastilloXmlService service = new CastilloXmlServiceImpl();
		try {
			List<Castillo> castillos = new ArrayList<>();
			castillos.add(createCastillo());
			castillos.add(createCastillo());
			castillos.add(createCastillo());
			service.exportCastillosToXmlJackson("c:/temporal/castillosJackson.xml", castillos);
			
		//	Castillo castillo = createCastillo();
		//	service.exportCastilloToXmlJackson("c:/temporal/castilloJackson.xml", castillo);
			
			
//			Castillo castillo = service.importarCastilloFromXml("c:/temporal/castillo.xml");
//			System.out.println("CASTILLO LEIDO DE XML CON LOS SIGUIENTES DATOS:");
//			System.out.println("Foso:" + castillo.getFoso());
//			System.out.println("Caballeros: ");
//			castillo.getCaballeros().forEach(System.out::println);
//			System.out.println("Dragones: ");
//			castillo.getDragones().forEach(System.out::println);
			
		}catch (CastilloXmlException e) {
			e.printStackTrace();
		}
		
		
	/*	try {
		service.exportCastilloToXml("c:/temporal/castillo.xml",  castillo);
		}catch(CastilloXmlException e) {
			e.printStackTrace();
		}	*/
	}
		
	public static Castillo createCastillo() {
		// Crear castillo
		Castillo castillo = new Castillo();

		// ===== CABALLEROS =====
		List<Caballero> listaCaballeros = new ArrayList<>();

		Caballero c1 = new Caballero();
		c1.setNombre("Blas de los montes");
		c1.setCaballo("Rocinante");
		c1.setEscudero("Juanma de los cantalejos");
		c1.setEdad(31);

		Caballero c2 = new Caballero();
		c2.setNombre("Kuko de los Kukos");
		c2.setCaballo("Sombragris");
		c2.setEscudero("Sardinilla de los Pacos");
		c2.setEdad(12);

		listaCaballeros.add(c1);
		listaCaballeros.add(c2);

		castillo.setCaballeros(listaCaballeros);

		// ===== DRAGONES =====
		List<Dragon> listaDragones = new ArrayList<>();

		Dragon d1 = new Dragon();
		d1.setAlas(false);
		d1.setColor("verde");
		d1.setRaza("wida");
		d1.setPoder(32);

		Dragon d2 = new Dragon();
		d2.setAlas(true);
		d2.setColor("amarillo");
		d2.setRaza("smaug");
		d2.setPoder(200);

		// Tercer dragón inventado
		Dragon d3 = new Dragon();
		d3.setAlas(true);
		d3.setColor("rojo");
		d3.setRaza("drakon");
		d3.setPoder(150);

		listaDragones.add(d1);
		listaDragones.add(d2);
		listaDragones.add(d3);

		castillo.setDragones(listaDragones);

		// ===== FOSO =====
		castillo.setFoso(true);

		// (Opcional) imprimir para comprobar
		System.out.println("Castillo creado con " + castillo.getCaballeros().size() + " caballeros y "
				+ castillo.getDragones().size() + " dragones.");
		
		
		return castillo;
	}

}
