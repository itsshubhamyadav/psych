package com.shubham.psych.game.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.psych.game.model.Question;
import com.shubham.psych.game.repository.QuestionRepository;

@RestController
@RequestMapping("/dev")
public class QuestionController {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@GetMapping("/questions")
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
	
	@GetMapping("/questions/{id}")
    public Question getQuestionById(@PathVariable(value = "id") Long id) throws Exception {
        return questionRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
    }

}
