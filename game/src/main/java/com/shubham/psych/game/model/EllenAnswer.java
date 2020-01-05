package com.shubham.psych.game.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.shubham.psych.game.Constants;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="ellenAnswers")
public class EllenAnswer extends Auditable{
	
	@ManyToOne
	@Getter
    @Setter
    @JsonBackReference
	private Question question;
	
	@Getter
    @Setter
    @NotBlank
    @Column(length = Constants.MAX_ANSWER_LENGTH)
	private String answer;
	
	@Getter
    @Setter
	private Long votes=0L;

}
