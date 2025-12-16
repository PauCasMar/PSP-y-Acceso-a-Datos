package ceu.dam.ad.model;


import java.util.List;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="questions")
@Data
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type_code")
public abstract class Question {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	private String text;

	//Una foreign key siempre será del tipo del objeto, no del tipo del atributo de ese objeto
	@ManyToOne
	@JoinColumn(name="type_code", nullable=false, insertable=false, updatable=false)
	private QuestionType type;
	
	@OneToMany
	@JoinColumn(name="question_id", nullable= false)
	private List<Answer> answers;
	
	public abstract Boolean validar(List<Answer> answer);

}
