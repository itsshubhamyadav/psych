package com.shubham.psych.game.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rounds")
public class Round extends Auditable{
	
	@ManyToOne
	@Getter
    @Setter
	private Question question;
	@ManyToOne
	@Getter
    @Setter
	private Game game;
	
}
