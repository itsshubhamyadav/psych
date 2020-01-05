package com.shubham.psych.game.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="contentWriters")
public class ContentWriter extends Employee{

	@ManyToMany
	@Getter
	@Setter
	@JsonIdentityReference
	List<Question> editedQuestion=new ArrayList<>();
	
	
	
}
