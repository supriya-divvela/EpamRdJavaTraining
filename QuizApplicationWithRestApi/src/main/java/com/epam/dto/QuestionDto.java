package com.epam.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
	private int id;
	
	@NotBlank(message="Title is not valid..")
	private String title;
	
	@Size(min = 2,message="Options minimum size should be 2..")
	private List<@NotBlank String> options = new ArrayList<>();
	
	@Pattern(regexp = "^(?i)(easy|medium|hard)$",message="Difficulty should be in easy or medium or hard..")
	private String difficulty;
	
	@NotBlank(message="tagging topic is not valid..")
	private String taggingTopic;
	
	@Size(min = 1,message="Minimum 1 answer is required..")
	private Set<Integer> answers = new TreeSet<>();
	
	@Min(1)
	@Max(5)
	private int marks;
}
