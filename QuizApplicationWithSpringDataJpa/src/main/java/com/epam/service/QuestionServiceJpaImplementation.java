package com.epam.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.dto.QuestionDto;
import com.epam.exception.DuplicateQuestionNumberException;
import com.epam.exception.EmptyQuestionLibraryException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.jparepository.QuestionRepository;
import com.epam.model.Question;
@Component
public class QuestionServiceJpaImplementation implements QuestionService {
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public int addQuestion(QuestionDto questionDto) throws DuplicateQuestionNumberException {
		return questionRepository.save(new Question(questionDto.getQNo(), questionDto.getTitle(),
				questionDto.getOptions(), questionDto.getDifficulty(), questionDto.getTaggingTopic(),
				questionDto.getAnswers(), questionDto.getMarks())).getQNo();
	}

	@Override
	public int removeQuestion(int qNo) throws QuestionNotFoundException {
		if (!questionRepository.existsByQNo(qNo)) {
			throw new QuestionNotFoundException();
		}
		questionRepository.deleteByQNo(qNo);
		return qNo;
	}

	@Override
	public List<Question> displayQuestions() throws EmptyQuestionLibraryException {
		return questionRepository.findAll();
	}

	@Override
	public boolean findQuestion(int qNo) {
		return questionRepository.existsByQNo(qNo);
	}

	@Override
	public boolean isEmptyQuestionLibrary() {
		return questionRepository.count()==0;
	}

	@Override
	public Question getQuestion(int qNo) throws QuestionNotFoundException {
		return questionRepository.findByQNo(qNo);
	}

	@Override
	public void updateQuestion(int qNo, QuestionDto question) throws QuestionNotFoundException {
		questionRepository.findByQNo(qNo).setTitle(question.getTitle());
		questionRepository.findByQNo(qNo).setDifficulty(question.getDifficulty());
		questionRepository.findByQNo(qNo).setAnswers(question.getAnswers());
		questionRepository.findByQNo(qNo).setTaggingTopic(question.getTaggingTopic());
		questionRepository.findByQNo(qNo).setMarks(question.getMarks());
		questionRepository.findByQNo(qNo).setOptions(question.getOptions());
	}

}
