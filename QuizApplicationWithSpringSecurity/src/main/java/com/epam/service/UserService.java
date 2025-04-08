package com.epam.service;

import java.util.List;

import com.epam.dto.QuizDto;

public interface UserService {
	int calculateMarks(List<List<Integer>> answers,QuizDto quiz);
	int calculateTotalMarks(QuizDto quiz);
}
