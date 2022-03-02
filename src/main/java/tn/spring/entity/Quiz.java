package tn.spring.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;
@Entity
@Data
public class Quiz {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
	private int id ;
	private String name;
	private int totpt;
	@ManyToMany(mappedBy="quizs")
	private Set<AppUser> users;
	@OneToMany(mappedBy = "quizs")
	private Set<Question> questions;
}
