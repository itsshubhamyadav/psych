package com.shubham.psych.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.psych.game.model.Game;
import com.shubham.psych.game.repository.GameRepository;

@RestController
@RequestMapping("/dev")
public class GameController {

	@Autowired
    private GameRepository gameRepository;

    @GetMapping("/games")
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @GetMapping("/games/{id}")
    public Game getGame(@PathVariable(value = "id") Long id) {
        return gameRepository.findById(id).orElseThrow(null);
    }
    
}
