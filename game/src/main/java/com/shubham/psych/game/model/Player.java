package com.shubham.psych.game.model;



import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "players")
public class Player extends Auditable {
	
    @Getter
    @Setter
    @NotBlank
    private String name;
    
    
}
