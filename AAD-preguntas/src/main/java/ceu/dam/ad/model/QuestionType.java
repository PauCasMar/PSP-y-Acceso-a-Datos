package ceu.dam.ad.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="question_types")
public class QuestionType {

	@Id
	private String code;
	private String description;
	
}
