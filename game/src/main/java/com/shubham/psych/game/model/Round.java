package com.shubham.psych.game.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.shubham.psych.game.exceptions.InvalidActionForGameStateException;
import com.shubham.psych.game.exceptions.InvalidInputException;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "rounds")
public class Round extends Auditable {

	@Getter
	@Setter
	@ManyToOne
	@NotNull
	@JsonBackReference
	private Game game;

	@ManyToOne
	@Getter
	@Setter
	private Question questions;

	@Getter
	@Setter
	@NotNull
	private int roundNumber;

	
	@ManyToMany(cascade = CascadeType.ALL)
    @JsonManagedReference
    @Getter
    @Setter
    private Map<Player, PlayerAnswer> submittedAnswers = new HashMap<>();

    @ManyToMany
    @Getter
    @Setter
    private Map<Player, PlayerAnswer> selectedAnswers = new HashMap<>();

    @ManyToMany
    @Getter
    @Setter
    private Set<Player> readyPlayers = new HashSet<>();

	
	public Round() {}
	
	public Round(@NotNull Game game, Question question, @NotNull int roundNumber) {
        this.game = game;
        this.questions = question;
        this.roundNumber = roundNumber;
    }


	public void submitAnswer(Player player, String answer) throws InvalidActionForGameStateException {
		if(submittedAnswers.containsKey(player))
            throw new InvalidActionForGameStateException("Player has already submitted an answer for this round");
        PlayerAnswer playerAnswer = new PlayerAnswer(answer, this, player);
        submittedAnswers.put(player, playerAnswer);
	}

	public void selectAnswer(Player player, PlayerAnswer playerAnswer) throws InvalidInputException {
        if(!playerAnswer.getRound().equals(this))
            throw new InvalidInputException("No such answer found for this round");
        selectedAnswers.put(player, playerAnswer);
    }
	
	

    public void getReady(Player player) {
        readyPlayers.add(player);
    }


}
