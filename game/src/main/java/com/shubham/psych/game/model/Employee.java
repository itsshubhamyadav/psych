package com.shubham.psych.game.model;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public abstract class Employee extends Auditable{
	
	@Getter
    @Setter
    @NotBlank
    private String name;

    @Getter
    @Setter
    @NotBlank
    @Email
    private String email;
    
    @Getter
    @Setter
    @NotBlank
    private String address;
    
    @Getter
    @Setter
    @NotBlank
    private String phoneNumber;
	
}
