package com.epam.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

//Owning side
@Entity
@Data
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int answerId;
    String value;

//    @ManyToOne
//    @JoinColumn(referencedColumnName = "questionId")
    @ManyToOne
    Question question;

}

