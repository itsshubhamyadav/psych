package com.shubham.psych.game.model;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ellenAnswers")
public class EllenAnswer extends Auditable{
	
	@ManyToOne
	@Getter
    @Setter
	private Question question;
	@Getter
    @Setter
	private String answer;
	@Getter
    @Setter
	private int vote_count;
	
	
}
