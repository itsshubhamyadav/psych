package com.shubham.psych.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.psych.game.Utils;
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
    
    @GetMapping("/create-game/{pid}/{gm}/{nr}")
    public String getAllPlayers(@PathVariable(value = "pid") Long playerId,
            @PathVariable(value = "gm") int gameMode,
            @PathVariable(value = "nr") int numRounds) {
    	Player player = playerRepository.findById(playerId).orElseThrow(null);
    	GameMode mode=GameMode.IS_THIS_A_FACT;
    	
    	Game game=new Game();
    	game.setNumRound(numRounds);
    	game.setLeader(player);
    	game.setGameMode(mode);
    	game.getPlayers().add(player);
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

    
    
}
