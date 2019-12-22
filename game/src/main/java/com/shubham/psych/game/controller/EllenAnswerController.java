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

import com.shubham.psych.game.model.EllenAnswer;
import com.shubham.psych.game.repository.EllenAnswerRepository;


@RestController
@RequestMapping("/api")
public class EllenAnswerController {
	
	@Autowired
    private EllenAnswerRepository adminRepository;

    @GetMapping("/ellenAnswers")
    public List<EllenAnswer> getAllAdmins() {
        return adminRepository.findAll();
    }

    @PostMapping("/ellenAnswers")
    public EllenAnswer createAdmin(@Valid @RequestBody EllenAnswer admin) {
        return adminRepository.save(admin);
    }

    @GetMapping("/ellenAnswers/{id}")
    public EllenAnswer getAdminById(@PathVariable(value = "id") Long id) throws Exception {
        return adminRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
    }

    @PutMapping("/ellenAnswers/{id}")
    public EllenAnswer updatePlayer(@PathVariable(value = "id") Long id, @Valid @RequestBody EllenAnswer admin) throws Exception {
    	EllenAnswer p = adminRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
        p.setAnswer(admin.getAnswer());
        return adminRepository.save(p);
    }

    @DeleteMapping("/ellenAnswers/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable(value = "id") Long id) throws Exception {
    	EllenAnswer p = adminRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
        adminRepository.delete(p);
        return ResponseEntity.ok().build();
    }



}
