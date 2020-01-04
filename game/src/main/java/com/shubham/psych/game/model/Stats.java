package com.shubham.psych.game.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "stats")
public class Stats extends Auditable{
	
	@Getter
    @Setter
	private long correctAnswers=0;
	@Getter
    @Setter
	private long gotPsychedCount=0;
	@Getter
    @Setter
	private long othersPsychedCount=0;
	
}
