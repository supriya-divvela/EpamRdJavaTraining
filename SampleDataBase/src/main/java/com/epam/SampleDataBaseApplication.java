package com.epam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;

import com.epam.jparepository.QuestionRepository;
import com.epam.jparepository.QuizRepository;
import com.epam.jparepository.UserRepository;

@SpringBootApplication
public class SampleDataBaseApplication {
	@Autowired
	private QuizRepository quizRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SampleDataBaseApplication.class,
				args);
//		QuestionService questionService = applicationContext.getBean(QuestionService.class);
//		QuizService quizService = applicationContext.getBean(QuizService.class);
////		questionService.addQuestion(new QuestionDto(1,"ok",Arrays.asList("1","2","3","4"),"easy","java",new HashSet<>(Arrays.asList(1)),1));
//		quizService.addQuiz(new Quiz("New Quiz"));
	}

	@EventListener(ApplicationReadyEvent.class)
	public void triggerAction() {
		
	}

}
