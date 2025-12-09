package ceu.dam.ad.demo_xml_castillos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import tools.jackson.dataformat.xml.annotation.JacksonXmlText;

@Data
public class Caballero {
	
	@JacksonXmlText //Esto oculta el tag indicado. Recomendado para cuando el padre solo tiene un hijo
	private String nombre;
	@JsonIgnore
    private String caballo;
    private String escudero;
    private int edad;
	
}
