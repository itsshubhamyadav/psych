package com.shubham.psych.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.psych.game.Utils;
import com.shubham.psych.game.exceptions.IllegalGameException;
import com.shubham.psych.game.exceptions.InsufficientPlayersException;
import com.shubham.psych.game.exceptions.InvalidActionForGameStateException;
import com.shubham.psych.game.model.Game;
import com.shubham.psych.game.model.GameMode;
import com.shubham.psych.game.model.Player;
import com.shubham.psych.game.repository.GameRepository;
import com.shubham.psych.game.repository.PlayerAnswerRepository;
import com.shubham.psych.game.repository.PlayerRepository;
import com.shubham.psych.game.repository.QuestionRepository;
import com.shubham.psych.game.repository.RoundRepository;

@RestController
@RequestMapping("/game")
public class PlayersEndPoint {

    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    GameRepository gameRepository;
    @Autowired
    RoundRepository roundRepository;
    @Autowired
    PlayerAnswerRepository playerAnswerRepository;
    
    @GetMapping("/create-game/{pid}/{gm}/{nr}/{ellen}")
    public String getAllPlayers(@PathVariable(value = "pid") Long playerId,
            @PathVariable(value = "gm") int gameMode,
            @PathVariable(value = "nr") int numRounds,
            @PathVariable(value = "ellen") int hasEllen) {
    	Player player = playerRepository.findById(playerId).orElseThrow(null);
    	GameMode mode=GameMode.fromValue(gameMode);
    	
    	Game game = new Game.Builder()
                .hasEllen(hasEllen == 1)
                .numRound(numRounds)
                .gameMode(mode)
                .leader(player)
                .build();
    	gameRepository.save(game);

    	return ""+game.getId()+" "+Utils.getSecretCodeFromId(game.getId());
    }
    
    @GetMapping("/join-game/{pid}/{gc}")
    public String joinGame(@PathVariable(value = "pid") Long playerId,
                           @PathVariable(value = "gc") String gameCode)  {
        Game game = gameRepository.findById(Utils.getGameIdFromSecretCode(gameCode)).orElseThrow(null);
        Player player = playerRepository.findById(playerId).orElseThrow(null);
        game.getPlayers().add(player);
        gameRepository.save(game);
        return "joined game";
    }

    @GetMapping("/start-game/{pid}/{gid}")
    public String startGame(@PathVariable(value = "pid") Long playerId,
                           @PathVariable(value = "gid") Long gameId) throws IllegalGameException, InsufficientPlayersException, InvalidActionForGameStateException {
        Game game = gameRepository.findById(gameId).orElseThrow(null);
        Player player = playerRepository.findById(playerId).orElseThrow(null);
        if(!game.getLeader().equals(player)) {
            throw new IllegalGameException("Player hasn't joined any such game");
        }
        if(game.getPlayers().size() < 2) {
            throw new InsufficientPlayersException("Cannot start a game without any friends");
        }
        game.start();
        gameRepository.save(game);
        return "game started";
    }
    
    @GetMapping("/submit-answer/{pid}/{gid}/{answer}")
    public String submitAnswer(@PathVariable(value = "pid") Long playerId,
                               @PathVariable(value = "gid") Long gameId,
                               @PathVariable(value = "answer") String answer) throws InvalidActionForGameStateException, IllegalGameException {
        Game game = gameRepository.findById(gameId).orElseThrow(null);
        Player player = playerRepository.findById(playerId).orElseThrow(null);
        if(!game.hasPlayer(player)) {
            throw new IllegalGameException("Player has not joined the game yet");
        }
        game.submitAnswer(player, answer);
        gameRepository.save(game);
        return "submitted answer";
    }
    @GetMapping("/get-ready/{pid}/{gid}")
    public String getReady(@PathVariable(value = "pid") Long playerId,
                               @PathVariable(value = "gid") Long gameId) throws IllegalGameException {
        Game game = gameRepository.findById(gameId).orElseThrow(null);
        Player player = playerRepository.findById(playerId).orElseThrow(null);
        if(!game.hasPlayer(player)) {
            throw new IllegalGameException("Player has not joined the game yet");
        }
        game.getReady(player);
        gameRepository.save(game);
        return "player ready";
    }
    @GetMapping("/game-state/{gid}")
    public String gameState(@PathVariable(value = "gid") Long gameId) {
        Game game = gameRepository.findById(gameId).orElseThrow(null);
        return game.getState();
    }
    
}
