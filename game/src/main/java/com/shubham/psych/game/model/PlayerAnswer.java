package com.shubham.psych.game.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.shubham.psych.game.Constants;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="playerAnswers")
public class PlayerAnswer extends Auditable{
	
	@Getter
    @Setter
    @Column(length = Constants.MAX_ANSWER_LENGTH)
	private String answer;
	@Getter
    @Setter
    @JsonBackReference
	private Round round;
	@Getter
    @Setter
	private Player player;
	
	public PlayerAnswer(String answer, Round round, Player player) {
		super();
		this.answer = answer;
		this.round = round;
		this.player = player;
	}
	
	

}
