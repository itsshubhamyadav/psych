package com.shubham.psych.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubham.psych.game.model.GameMode;

public interface GameModeRepository extends JpaRepository<GameMode, Long> {

}
