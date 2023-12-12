package com.virtuallearning.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class McqQuestion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String question;
	private String option1;
	private String option2;
	private String option3;
	private String option4;

	// Constructors, getters, setters (omitted for brevity)
}
