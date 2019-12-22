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

import com.shubham.psych.game.model.Stats;
import com.shubham.psych.game.repository.StatsRepository;

@RestController
@RequestMapping("/api")
public class StatsController {
	
	@Autowired
    private StatsRepository adminRepository;

    @GetMapping("/stats")
    public List<Stats> getAllAdmins() {
        return adminRepository.findAll();
    }

    @PostMapping("/stats")
    public Stats createAdmin(@Valid @RequestBody Stats admin) {
        return adminRepository.save(admin);
    }

    @GetMapping("/stats/{id}")
    public Stats getAdminById(@PathVariable(value = "id") Long id) throws Exception {
        return adminRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
    }

    @PutMapping("/stats/{id}")
    public Stats updatePlayer(@PathVariable(value = "id") Long id, @Valid @RequestBody Stats admin) throws Exception {
    	Stats p = adminRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
        //p.setAnswer(admin.getAnswer());
        return adminRepository.save(p);
    }

    @DeleteMapping("/stats/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable(value = "id") Long id) throws Exception {
    	Stats p = adminRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
        adminRepository.delete(p);
        return ResponseEntity.ok().build();
    }

}
