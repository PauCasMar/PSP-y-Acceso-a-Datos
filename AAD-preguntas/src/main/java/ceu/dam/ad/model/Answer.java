package ceu.dam.ad.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="answers")
public class Answer {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String text;
	private Boolean correct;

}
