package com.shubham.psych.game.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.shubham.psych.game.Constants;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="questions")
public class Question extends Auditable{
	
	@Getter
    @Setter
    @NotBlank
    @Column(length = Constants.MAX_QUESTION_LENGTH)
	private String questionText;
	@Getter
    @Setter
    @NotBlank
    @Column(length = Constants.MAX_ANSWER_LENGTH)
	private String correctAnswer;
	@Getter
    @Setter
    @NotNull
	private GameMode gameMode;
	@OneToMany(mappedBy ="question")
	@Getter
    @Setter
	private List<EllenAnswer> ellenAnswer;

}
