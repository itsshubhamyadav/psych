package com.shubham.psych.game.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "gameModes")
public class GameMode extends Auditable{
	
	@Getter
    @Setter
	private String level;

}
