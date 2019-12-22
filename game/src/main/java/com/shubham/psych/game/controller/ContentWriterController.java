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

import com.shubham.psych.game.model.ContentWriter;
import com.shubham.psych.game.repository.ContentWriterRepository;

@RestController
@RequestMapping("/api")
public class ContentWriterController {
	
	@Autowired
    private ContentWriterRepository adminRepository;

    @GetMapping("/contentWriters")
    public List<ContentWriter> getAllAdmins() {
        return adminRepository.findAll();
    }

    @PostMapping("/contentWriters")
    public ContentWriter createAdmin(@Valid @RequestBody ContentWriter admin) {
        return adminRepository.save(admin);
    }

    @GetMapping("/contentWriters/{id}")
    public ContentWriter getAdminById(@PathVariable(value = "id") Long id) throws Exception {
        return adminRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
    }

    @PutMapping("/contentWriters/{id}")
    public ContentWriter updatePlayer(@PathVariable(value = "id") Long id, @Valid @RequestBody ContentWriter admin) throws Exception {
    	ContentWriter p = adminRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
        p.setName(admin.getName());
        return adminRepository.save(p);
    }

    @DeleteMapping("/contentWriters/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable(value = "id") Long id) throws Exception {
    	ContentWriter p = adminRepository.findById(id).orElseThrow(() -> new Exception("something went wrong"));
        adminRepository.delete(p);
        return ResponseEntity.ok().build();
    }


}
