package com.shubham.psych.game.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shubham.psych.game.Constants;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "questions")
public class Question extends Auditable {

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
	@OneToMany(mappedBy = "question")
	@Getter
	@Setter
	@JsonManagedReference
	private List<EllenAnswer> ellenAnswer=new ArrayList<>();

	public Question() {
		
	}
	
	public static class Builder {
		private String questionText;
		private String correctAnswer;
		private GameMode gameMode;

		public Builder questionText(String questionText) {
			this.questionText = questionText;
			return this;
		}

		public Builder correctAnswer(String correctAnswer) {
			this.correctAnswer = correctAnswer;
			return this;
		}

		public Builder gameMode(GameMode gameMode) {
			this.gameMode = gameMode;
			return this;
		}

		public Question build() {
			return new Question(this);
		}
	}

	private Question(Builder builder) {
		this.questionText = builder.questionText;
		this.correctAnswer = builder.correctAnswer;
		this.gameMode = builder.gameMode;
	}
}
