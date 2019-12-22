package com.shubham.psych.game.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "games")
public class Game extends Auditable{
	
	@OneToMany
	@Getter
    @Setter
	private Player player;
	@Getter
    @Setter
	private Player leader;
	@Getter
    @Setter
	private boolean is_over;
	@OneToMany
	@Getter
    @Setter
	private Round round;
	@OneToOne
	@Getter
    @Setter
	private Stats stats;
	@ManyToOne
	@Getter
    @Setter
	private GameMode gameMode;
	
	

}
