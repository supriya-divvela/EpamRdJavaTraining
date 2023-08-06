package com.epam.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	@ElementCollection(targetClass = String.class)
	private List<String> options;
	private String difficulty;
	private String taggingTopic;
	@ElementCollection(targetClass = Integer.class)
	private Set<Integer> answers;
	private Integer marks;
	@ManyToMany(mappedBy = "listOfQuestions",cascade = CascadeType.ALL)
	private List<Quiz> quizzes;

}
