package com.shubham.psych.game.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="contentWriters")
public class ContentWriter extends Employee{

	@ManyToMany
	List<Question> editedQuestion;
	
	
	
}
