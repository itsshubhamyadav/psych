package com.shubham.psych.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubham.psych.game.model.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {
	
}