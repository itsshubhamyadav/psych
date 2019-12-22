package com.shubham.psych.game.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "contentWriters")
public class ContentWriter extends Auditable{
	
	@ManyToMany
	@Getter
    @Setter
	private Question question;
	@Getter
    @Setter
	private String name;

}
