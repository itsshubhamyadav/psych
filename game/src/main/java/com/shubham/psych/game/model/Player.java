package com.shubham.psych.game.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

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

	@OneToOne(cascade = CascadeType.ALL)
	@Getter
	@Setter
	private Stats stats = new Stats();

	@ManyToMany(mappedBy = "players")
	@JsonIdentityReference
	@Getter
	@Setter
	private List<Game> games = new ArrayList<>();

	public Player() {}
	
	public static class Builder {
		private String name;
		private String psychFaceURL;
		private String picUrl;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder psychFaceURL(String psychFaceURL) {
			this.psychFaceURL = psychFaceURL;
			return this;
		}

		public Builder picUrl(String picUrl) {
			this.picUrl = picUrl;
			return this;
		}

		public Player build() {
			return new Player(this);
		}
	}

	private Player(Builder builder) {
		this.name = builder.name;
		this.psychFaceURL = builder.psychFaceURL;
		this.picUrl = builder.picUrl;
	}
}
