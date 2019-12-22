package com.shubham.psych.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubham.psych.game.model.Stats;

public interface StatsRepository extends JpaRepository<Stats, Long> {

}
