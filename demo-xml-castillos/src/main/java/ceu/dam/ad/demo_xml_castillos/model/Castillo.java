package ceu.dam.ad.demo_xml_castillos.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import tools.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;


@Data
public class Castillo {
	@JsonProperty("caballero")
	@JacksonXmlElementWrapper(localName = "Caballeros")
	private List<Caballero> caballeros;
	@JacksonXmlElementWrapper(localName = "Dragones")
	@JsonProperty("dragon")
    private List<Dragon> dragones;
    @JsonProperty("tieneFoso")
    private Boolean foso;

}
