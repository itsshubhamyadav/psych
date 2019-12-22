package com.shubham.psych.game.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stats")
public class Stats extends Auditable{
	
	@OneToMany
	@Getter
    @Setter
	private Player player;
	@Getter
    @Setter
	private int correct_answer;
	@Getter
    @Setter
	private int wrong_answer;
	@Getter
    @Setter
	private int total_question;
	@OneToOne
	@Getter
    @Setter
	private Game game;
	
	
	

}
