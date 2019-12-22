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

import com.shubham.psych.game.model.Round;
import com.shubham.psych.game.repository.RoundRepository;

@RestController
@RequestMapping("/api")
public class RoundRepositoryController {
	
	@Autowired
    private RoundRepository adminRepository;

    @GetMapping("/rounds")
    public List<Round> getAllAdmins() {
        return adminRepository.findAll();
    }

    @PostMapping("/rounds")
    public Round createAdmin(@Valid @RequestBody Round admin) {
        return adminRepository.save(admin);
    }

    @GetMapping("/rounds/{id}")
    public Round getAdminById(@PathVariable(value = "id") Long id) throws Exception {
        return adminRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
    }

    @PutMapping("/rounds/{id}")
    public Round updatePlayer(@PathVariable(value = "id") Long id, @Valid @RequestBody Round admin) throws Exception {
    	Round p = adminRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
        //p.setAnswer(admin.getAnswer());
        return adminRepository.save(p);
    }

    @DeleteMapping("/rounds/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable(value = "id") Long id) throws Exception {
    	Round p = adminRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
        adminRepository.delete(p);
        return ResponseEntity.ok().build();
    }

}
