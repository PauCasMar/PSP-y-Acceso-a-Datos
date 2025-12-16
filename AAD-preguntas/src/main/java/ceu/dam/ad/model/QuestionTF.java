package ceu.dam.ad.model;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TF")
public class QuestionTF  extends Question{

	public Boolean validar(List<Answer> answers) {
		return null;
	
	}


	
}
