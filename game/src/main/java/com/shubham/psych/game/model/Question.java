package com.shubham.psych.game.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "questions")
public class Question extends Auditable{
	
	@Getter
    @Setter
	private String quetionText;
	@Getter
    @Setter
	private String answer;
	@OneToMany
	@Getter
    @Setter
	private Round round;
	@OneToMany
	@Getter
    @Setter
	private EllenAnswer ellenAnswer;
	@ManyToMany
	@Getter
    @Setter
	private ContentWriter contentWriter;
	
	
	

}
