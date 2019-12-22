package com.shubham.psych.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubham.psych.game.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
