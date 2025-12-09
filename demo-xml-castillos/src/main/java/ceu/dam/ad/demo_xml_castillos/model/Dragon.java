package ceu.dam.ad.demo_xml_castillos.model;

import lombok.Data;
import tools.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@Data
public class Dragon {
	@JacksonXmlProperty(isAttribute = true)
	private Boolean alas;
	@JacksonXmlProperty(isAttribute = true)
    private String color;
    private String raza;
    private Integer poder;

	


}
