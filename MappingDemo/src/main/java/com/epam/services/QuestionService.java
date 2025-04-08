package com.epam.services;

import com.epam.dao.AnswerRepo;
import com.epam.dao.QuestionRepo;
import com.epam.entities.Answer;
import com.epam.entities.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class QuestionService {
    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    AnswerRepo answerRepo;

    public void addQuestion(Question question)
    {
        question = questionRepo.save(question);
//        System.out.println(question.getQuestionId()+question.getStatement());
//        question.getAnswers().stream().forEach(ans -> System.out.println(ans.getAnswerId() + " "  +ans.getValue()));
    }
    public void deleteQuestion(int id)
    {
        questionRepo.deleteById(id);
    }

    public void removeAnswer(int index)
    {
        Question question = questionRepo.findById(52).get();

//        question.getAnswers().remove(1);
//        question.getAnswers().stream().forEach(ans -> System.out.println(ans.getAnswerId() + " " + ans.getValue()));
        questionRepo.save(question);


    }

}
