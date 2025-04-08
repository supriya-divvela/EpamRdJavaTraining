package com.epam.service;

import java.util.List;

import com.epam.model.Quiz;

public interface UserService {
	int calculateMarks(List<List<Integer>> answers,Quiz quiz);
	int calculateTotalMarks(Quiz quiz);
}
