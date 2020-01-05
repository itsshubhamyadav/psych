package com.shubham.psych.game.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shubham.psych.game.Utils;
import com.shubham.psych.game.exceptions.InvalidActionForGameStateException;
import com.shubham.psych.game.exceptions.InvalidInputException;
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
	private boolean hasEllen = false;
	@ManyToMany(cascade = CascadeType.ALL)
	@Getter
	@Setter
	private Map<Player, Stats> palyersStats=new HashMap<>();
	@ManyToMany
	@Getter
	@Setter
	@JsonIdentityReference
	private List<Player> players=new ArrayList<>();
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
	@JsonIdentityReference
	private Player leader;

	@OneToMany(mappedBy = "game",cascade = CascadeType.ALL)
	@Getter
	@Setter
	@JsonManagedReference
	private List<Round> rounds=new ArrayList<>();

	public Game() {

	}
	private Game(Builder builder) {
		this.numRound = builder.numRound;
		this.hasEllen = builder.hasEllen;
		this.gameMode = builder.gameMode;
		this.leader = builder.leader;
		
		try {
			addPlayers(leader);
		} catch (InvalidActionForGameStateException e) {
			e.printStackTrace();
		}
	}

	private void addPlayers(Player player) throws InvalidActionForGameStateException  {
		if (!gameStatus.equals(GameStatus.JOINING))
            throw new InvalidActionForGameStateException("Cannot join game because it has already started.");
		if (palyersStats.containsKey(player)) 
			return;
        players.add(player);
        palyersStats.put(player, new Stats());	
	}

	public static class Builder {
		private int numRound;
		private boolean hasEllen;
		private GameMode gameMode;
		private Player leader;
		

		public Builder numRound(int numRound) {
			this.numRound = numRound;
			return this;
		}

		public Builder hasEllen(boolean hasEllen) {
			this.hasEllen = hasEllen;
			return this;
		}

		public Builder gameMode(GameMode gameMode) {
			this.gameMode = gameMode;
			return this;
		}

		public Builder leader(Player leader) {
			this.leader = leader;
			return this;
		}

		public Game build() {
			return new Game(this);
		}
	}

	
	public boolean hasPlayer(Player player) {
        return palyersStats.containsKey(player);
    }
	

    public void start() throws InvalidActionForGameStateException {
        if(!gameStatus.equals(GameStatus.JOINING))
            throw new InvalidActionForGameStateException("Game has already started");
        startNewRound();
    }
    
    private void startNewRound() {
        Round round = new Round(this, Utils.getRandomQuestion(), 1);
        rounds.add(round);
        gameStatus = GameStatus.SUBMITTING_ANSWERS;
    }
    public void submitAnswer(Player player, String answer) throws InvalidActionForGameStateException {
        if(!gameStatus.equals(GameStatus.SUBMITTING_ANSWERS))
            throw new InvalidActionForGameStateException("Not accepting any answers at the moment");
        Round round = currentRound();
        round.submitAnswer(player, answer);
        if(round.getSubmittedAnswers().size() == players.size()) {
            gameStatus = GameStatus.SELECTING_ANSWERS;
        }
    }
    public Round currentRound() {
        return rounds.get(rounds.size() - 1);
    }
    
    public void selectAnswer(Player player, PlayerAnswer playerAnswer) throws InvalidActionForGameStateException, InvalidInputException {
        if(!gameStatus.equals(GameStatus.SELECTING_ANSWERS))
            throw new InvalidActionForGameStateException("Not selecting any answers at the moment");
        Round round = currentRound();
        round.selectAnswer(player, playerAnswer);
        if(round.getSelectedAnswers().size() == players.size()) {
            if(rounds.size() < numRound)
                gameStatus = GameStatus.GETTING_READY;
            else {
                gameStatus = GameStatus.OVER;
                // todo: update the stats of ellen
            }
        }
    }
    public void getReady(Player player) {
        Round round = currentRound();
        round.getReady(player);
        if(round.getReadyPlayers().size() == players.size())
            startNewRound();
    }

    public String getState() {
        return "";
        // - current round state - submitting-answer, selecting-answers-round-over
    }

	
	
}
