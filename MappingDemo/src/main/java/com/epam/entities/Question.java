package com.epam.entities;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
//Refrencening
@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int questionId;
    String statement;

//    @JsonIgnore
//    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.REMOVE},fetch = FetchType.EAGER)
//    List<Answer> answers;

	@OneToMany(mappedBy = "question")
	List<Answer> answer;
//    @JoinColumn

}








