package com.epam;

import com.epam.entities.Answer;
import com.epam.entities.Question;
import com.epam.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MappingDemoApplication {






    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MappingDemoApplication.class, args);



        QuestionService questionService = applicationContext.getBean(QuestionService.class);
//        Question question = new Question();
//
//        question.setStatement("4th Question");
//
//        Answer answer1 = new Answer();
//        answer1.setValue("answer1");
//        answer1.setQuestion(question);
//
//        Answer answer2 = new Answer();
//        answer2.setValue("answer2");
//        answer2.setQuestion(question);
//
//        Answer answer3 = new Answer();
//        answer3.setValue("answer3");
//        answer3.setQuestion(question);
//
//        List<Answer> answers = List.of(answer1,answer2,answer3);
//
////        question.setAnswers(answers);
//
//
//        questionService.addQuestion(question);
////        questionService.deleteQuestion(52);
//////
//////       questionService.removeAnswer(52);


    }

}


