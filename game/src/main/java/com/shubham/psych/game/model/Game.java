package com.shubham.psych.game.model;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "games")
public class Game extends Auditable {

	@Getter
	@Setter
	@NotNull
	private int numRound;
	@Getter
	@Setter
	private int currentRound = 0;
	@ManyToMany
	@Getter
	@Setter
	private Map<Player, Stats> palyersStats;
	@ManyToMany
	@Getter
	@Setter
	private List<Player> players;
	@Getter
	@Setter
	@NotNull
	private GameMode gameMode;
	@Getter
	@Setter
	private GameStatus gameStatus = GameStatus.JOINING;
	@ManyToOne
	@Getter
	@Setter
	@NotNull
	private Player leader;

	@OneToMany(mappedBy = "game")
	@Getter
	@Setter
	private List<Round> rounds;

}
