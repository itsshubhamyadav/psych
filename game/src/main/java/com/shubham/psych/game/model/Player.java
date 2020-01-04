package com.shubham.psych.game.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "players")
public class Player extends Auditable {

	@Getter
	@Setter
	@NotBlank
	private String name;

//	@Getter
//	@Setter
//	@NotBlank
//	@Email
//	private String email;

	@Getter
	@Setter
	@URL
	private String psychFaceURL;

	@Getter
	@Setter
	@URL
	private String picUrl;

	@OneToOne
	@Getter
	@Setter
	private Stats stats;
	@ManyToMany(mappedBy = "players")
	@Getter
	@Setter
	private List<Game> games;

}
