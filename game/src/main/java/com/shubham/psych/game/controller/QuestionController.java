package com.shubham.psych.game.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubham.psych.game.model.Question;
import com.shubham.psych.game.repository.QuestionRepository;

@RestController
@RequestMapping("/api")
public class QuestionController {
	
	@Autowired
    private QuestionRepository adminRepository;

    @GetMapping("/questions")
    public List<Question> getAllAdmins() {
        return adminRepository.findAll();
    }

    @PostMapping("/questions")
    public Question createAdmin(@Valid @RequestBody Question admin) {
        return adminRepository.save(admin);
    }

    @GetMapping("/questions/{id}")
    public Question getAdminById(@PathVariable(value = "id") Long id) throws Exception {
        return adminRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
    }

    @PutMapping("/questions/{id}")
    public Question updatePlayer(@PathVariable(value = "id") Long id, @Valid @RequestBody Question admin) throws Exception {
    	Question p = adminRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
        //p.setAnswer(admin.getAnswer());
        return adminRepository.save(p);
    }

    @DeleteMapping("/questions/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable(value = "id") Long id) throws Exception {
    	Question p = adminRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
        adminRepository.delete(p);
        return ResponseEntity.ok().build();
    }



}
