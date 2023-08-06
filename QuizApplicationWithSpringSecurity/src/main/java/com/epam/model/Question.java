package com.epam.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "questions")
@Entity
@RequiredArgsConstructor
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NonNull
	private String title;
	@ElementCollection(targetClass = String.class)
	@NonNull
	private List<String> options;
	@NonNull
	private String difficulty;
	@NonNull
	private String taggingTopic;
	@ElementCollection(targetClass = Integer.class)
	@NonNull
	private Set<Integer> answers;
	@NonNull
	private Integer marks;
	@ManyToMany(mappedBy = "listOfQuestions")
	private List<Quiz> quizzes;
}
