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

import com.shubham.psych.game.model.GameMode;
import com.shubham.psych.game.repository.GameModeRepository;


@RestController
@RequestMapping("/api")
public class GameModeController {
	
	@Autowired
    private GameModeRepository adminRepository;

    @GetMapping("/gameModes")
    public List<GameMode> getAllAdmins() {
        return adminRepository.findAll();
    }

    @PostMapping("/gameModes")
    public GameMode createAdmin(@Valid @RequestBody GameMode admin) {
        return adminRepository.save(admin);
    }

    @GetMapping("/gameModes/{id}")
    public GameMode getAdminById(@PathVariable(value = "id") Long id) throws Exception {
        return adminRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
    }

    @PutMapping("/gameModes/{id}")
    public GameMode updatePlayer(@PathVariable(value = "id") Long id, @Valid @RequestBody GameMode admin) throws Exception {
    	GameMode p = adminRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
        //p.setAnswer(admin.getAnswer());
        return adminRepository.save(p);
    }

    @DeleteMapping("/gameModes/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable(value = "id") Long id) throws Exception {
    	GameMode p = adminRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
        adminRepository.delete(p);
        return ResponseEntity.ok().build();
    }


}
