package com.epam.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class QuizDto {
	private int id;
	@NotBlank(message="Title should not be null or empty..")
	private String title;
	@Size(min = 1,message="Atleast one question is required...")
	private List<@Valid QuestionDto> listOfQuestions;

	public QuizDto(String title, List<QuestionDto> listOfQuestions) {
		this.title = title;
		this.listOfQuestions = listOfQuestions;
	}
}
