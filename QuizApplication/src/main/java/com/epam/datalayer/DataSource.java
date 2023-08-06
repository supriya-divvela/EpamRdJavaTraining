package com.epam.datalayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import com.epam.model.Question;
import com.epam.model.Quiz;
import com.epam.model.User;

public class DataSource {
	private static List<User> usersData = new ArrayList<User>();
	private static List<Quiz> quizsList = new ArrayList<Quiz>();
	private static List<Question> questionsList = new ArrayList<Question>();
	static {
		// Admins data
		usersData.add(new User("hello", "1234", "admin"));
		usersData.add(new User("abcd", "1234", "admin"));
		usersData.add(new User("vijay", "1234", "admin"));
		usersData.add(new User("1234", "1234", "admin"));
		// Question data
		questionsList.add(new Question(1, "What is java ?",
				new TreeSet<String>(
						Arrays.asList("object oriented", "platform independent", "not secure", "has pointers concept")),
				"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4))));
		questionsList.add(new Question(2, "What of the following is part of java",
				new TreeSet<String>(Arrays.asList("All The Above", "JVM", "JDK", "JRE")), "easy", "java",
				new TreeSet<Integer>(Arrays.asList(1))));
		questionsList.add(new Question(3, "Number of primitive data types in Java are?",
				new TreeSet<String>(Arrays.asList("6", "7", "8", "9")), "easy", "java",
				new TreeSet<Integer>(Arrays.asList(3))));
		questionsList.add(new Question(4, "What is the size of float and double in java?",
				new TreeSet<String>(Arrays.asList("32 & 64", "32 & 32", "64 & 64", "64 & 32")), "easy", "java",
				new TreeSet<Integer>(Arrays.asList(2))));

		// Available Quizes data
		Map<Question, Integer> map = new HashMap<>();
		map.put(DataSource.questionsList.get(0), 3);
		map.put(DataSource.questionsList.get(1), 4);
		map.put(DataSource.questionsList.get(2), 5);
		map.put(DataSource.questionsList.get(3), 6);
		quizsList.add(new Quiz(1, "maths", map, 18));
		quizsList.add(new Quiz(2, "physics", map, 18));
		quizsList.add(new Quiz(3, "java", map, 18));
		quizsList.add(new Quiz(4, "python", map, 18));

		// Users data
		usersData.add(new User("supriya", "1234", "user"));
		usersData.add(new User("pavan", "1234", "user"));
		usersData.add(new User("vijay", "1234", "user"));
		usersData.add(new User("krithi", "1234", "user"));
	}

	public List<User> getUsersData() {
		return usersData;
	}

	public List<Quiz> getQuizsList() {
		return quizsList;
	}

	public List<Question> getQuestionsList() {
		return questionsList;
	}

}
