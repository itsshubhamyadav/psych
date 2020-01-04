package com.shubham.psych.game.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.psych.game.Constants;
import com.shubham.psych.game.Pair;
import com.shubham.psych.game.Utils;
import com.shubham.psych.game.model.GameMode;
import com.shubham.psych.game.model.Player;
import com.shubham.psych.game.model.Question;
import com.shubham.psych.game.repository.PlayerRepository;
import com.shubham.psych.game.repository.QuestionRepository;



@RestController
@RequestMapping("/dev")
public class PopulateDB {

	@Autowired
    private PlayerRepository playerRepository;
	
	@Autowired
    private QuestionRepository questionRepository;
	
	int questionNumber = 0;
	@GetMapping("/add-questions-from-file")
    public void addAllQuestions() throws FileNotFoundException {
		questionRepository.deleteAll();
		for (Map.Entry<String, GameMode> entry : Constants.QA_FILES.entrySet()) {
            int questionNumber = 0;
            String filename = entry.getKey();
            GameMode gameMode = entry.getValue();
            for (Pair<String, String> question_answer : Utils.readQAFile(filename)) {
                Question q = new Question();
                q.setQuestionText(question_answer.getFirst());
                q.setCorrectAnswer(question_answer.getSecond());
                q.setGameMode(gameMode);
                questionRepository.save(q);
                questionNumber++;
                if (questionNumber > Constants.MAX_QUESTIONS_TO_READ) {
                    break;
                }
            }
        }
	}
	
	@GetMapping("/add-dummy-players")
    public void addDummyPlayers() throws IOException {
        playerRepository.deleteAll();
        Player luffy = new Player();
        luffy.setName("Shubham Yadav");
        luffy.setPicUrl("https://i.imgur.com/PrCEBd7.png");
        luffy.setPsychFaceURL("https://i.imgur.com/SPzynwl.png");
        Player robin = new Player();
        robin.setName("Nico Robin");
        robin.setPicUrl("https://i.imgur.com/kB7StJm.png");
        robin.setPsychFaceURL("https://i.imgur.com/tnJTeaG.png");
        playerRepository.save(luffy);
        playerRepository.save(robin);
    }
	
}
