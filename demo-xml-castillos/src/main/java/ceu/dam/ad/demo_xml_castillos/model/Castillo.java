package ceu.dam.ad.demo_xml_castillos.model;

import java.util.List;

import lombok.Data;

@Data
public class Castillo {
	private List<Caballero> caballeros;
    private List<Dragon> dragones;
    private Boolean foso;

}
