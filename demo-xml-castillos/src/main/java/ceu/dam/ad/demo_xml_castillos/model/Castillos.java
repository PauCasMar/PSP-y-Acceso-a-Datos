package ceu.dam.ad.demo_xml_castillos.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import tools.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

@Data
@JsonRootName(value="Castillos")
public class Castillos {

		@JacksonXmlElementWrapper(useWrapping = false) //Así se oculta uno de los tag castillos, el que indica la lista 
		@JsonProperty("castillo") //le damos nombre a cada elemento de la lista
		private List<Castillo> castillos;

}
