package com.shubham.psych.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shubham.psych.game.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

}
