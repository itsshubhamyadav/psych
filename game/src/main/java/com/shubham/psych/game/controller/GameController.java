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

import com.shubham.psych.game.model.Game;
import com.shubham.psych.game.repository.GameRepository;

@RestController
@RequestMapping("/api")
public class GameController {
	
	@Autowired
    private GameRepository adminRepository;

    @GetMapping("/games")
    public List<Game> getAllAdmins() {
        return adminRepository.findAll();
    }

    @PostMapping("/games")
    public Game createAdmin(@Valid @RequestBody Game admin) {
        return adminRepository.save(admin);
    }

    @GetMapping("/games/{id}")
    public Game getAdminById(@PathVariable(value = "id") Long id) throws Exception {
        return adminRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
    }

    @PutMapping("/games/{id}")
    public Game updatePlayer(@PathVariable(value = "id") Long id, @Valid @RequestBody Game admin) throws Exception {
    	Game p = adminRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
        //p.setAnswer(admin.getAnswer());
        return adminRepository.save(p);
    }

    @DeleteMapping("/games/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable(value = "id") Long id) throws Exception {
    	Game p = adminRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
        adminRepository.delete(p);
        return ResponseEntity.ok().build();
    }

}
