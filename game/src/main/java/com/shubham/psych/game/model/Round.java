package com.shubham.psych.game.model;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rounds")
public class Round extends Auditable {

	@Getter
	@Setter
	@ManyToOne
	@NotNull
	private Game game;

	@ManyToOne
	@Getter
	@Setter
	private Question questions;

	@Getter
	@Setter
	@NotNull
	private int roundNumber;

	@Getter
	@Setter
	@ManyToMany
	private Map<Player, PlayerAnswer> playerAnswer;

}
