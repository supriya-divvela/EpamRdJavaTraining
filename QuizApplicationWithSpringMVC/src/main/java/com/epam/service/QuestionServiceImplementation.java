package com.epam.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.epam.dto.QuestionDto;
import com.epam.exception.DuplicateQuestionNumberException;
import com.epam.exception.EmptyQuestionLibraryException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.model.Question;
import com.epam.repository.QuestionDao;

@Component
public class QuestionServiceImplementation implements QuestionService {
	@Autowired
	private QuestionDao questionDao;

	@Override
	public void addQuestion(QuestionDto questionDto) throws DuplicateQuestionNumberException {
		if (questionDto == null) {
			throw new NullPointerException();
		} else if (findQuestion(questionDto.getQNo())) {
			throw new DuplicateQuestionNumberException();
		}
		Question question=new Question();
		question.setQNo(questionDto.getQNo());
		question.setMarks(questionDto.getMarks());
		question.setDifficulty(questionDto.getDifficulty());
		question.setOptions(questionDto.getOptions());
		question.setTaggingTopic(questionDto.getTaggingTopic());
		question.setTitle(questionDto.getTitle()); 
		questionDao.addQuestion(question);
	}

	@Override
	public boolean isEmptyQuestionLibrary() {
		return questionDao.getQuestionsList().isEmpty();
	}

	@Override
	public boolean findQuestion(int qNo) {
		return questionDao.getQuestionsList().stream().filter(question -> question.getQNo() == qNo).count() == 1;
	}

	@Override
	public void removeQuestion(Question question) throws QuestionNotFoundException {
		if (question == null) {
			throw new NullPointerException();
		} else if (findQuestion(question.getQNo())) {
			questionDao.removeQuestion(question);
		} else {
			throw new QuestionNotFoundException();
		}
	}

	@Override
	public Question getQuestion(int qNo) throws QuestionNotFoundException {
		if (!findQuestion(qNo)) {
			throw new QuestionNotFoundException();
		}
		return questionDao.getQuestionsList().stream().filter(question -> question.getQNo() == qNo).toList().get(0);
	}

	@Override
	public String displayQuestions() throws EmptyQuestionLibraryException {
		if (questionDao.getQuestionsList().isEmpty()) {
			throw new EmptyQuestionLibraryException();
		}
		return questionDao.getQuestionsList().stream().map(Object::toString).reduce("\n",
				(partialString, element) -> partialString + element + "\n");
	}

	@Override
	public void updateTitle(int qNo, String title) throws QuestionNotFoundException {
		if (!findQuestion(qNo)) {
			throw new QuestionNotFoundException();
		}
		getQuestion(qNo).setTitle(title);
		questionDao.updateQuestion(getQuestion(qNo));
	}

	@Override
	public void updateOptions(int qNo, List<String> options) throws QuestionNotFoundException {
		if (!findQuestion(qNo)) {
			throw new QuestionNotFoundException();
		}
		getQuestion(qNo).setOptions(options);
		questionDao.updateQuestion(getQuestion(qNo));
	}

	@Override
	public void updateDifficulty(int qNo, String difficulty) throws QuestionNotFoundException {
		if (!findQuestion(qNo)) {
			throw new QuestionNotFoundException();
		}
		getQuestion(qNo).setDifficulty(difficulty);
		questionDao.updateQuestion(getQuestion(qNo));
	}

	@Override
	public void updateTaggingTopic(int qNo, String taggingTopic) throws QuestionNotFoundException {
		if (!findQuestion(qNo)) {
			throw new QuestionNotFoundException();
		}
		getQuestion(qNo).setTaggingTopic(taggingTopic);
		questionDao.updateQuestion(getQuestion(qNo));
	}

	@Override
	public void updateAnswers(int qNo, Set<Integer> newAnswers) throws QuestionNotFoundException {
		if (!findQuestion(qNo)) {
			throw new QuestionNotFoundException();
		}
		getQuestion(qNo).setAnswers(newAnswers);
		questionDao.updateQuestion(getQuestion(qNo));
	}

	@Override
	public void updateMarks(int qNo, int updateMarks) throws QuestionNotFoundException {
		if (!findQuestion(qNo)) {
			throw new QuestionNotFoundException();
		}
		getQuestion(qNo).setMarks(updateMarks);
		questionDao.updateQuestion(getQuestion(qNo));
	}

	@Override
	public List<Question> viewQuestions() throws EmptyQuestionLibraryException {
		if (questionDao.getQuestionsList().isEmpty()) {
			throw new EmptyQuestionLibraryException();
		}
		return questionDao.getQuestionsList();
	}
	
	@Override
	public void updateQuestion(int qNo,QuestionDto question) throws QuestionNotFoundException {
		getQuestion(qNo).setTitle(question.getTitle());
		getQuestion(qNo).setDifficulty(question.getDifficulty());
		getQuestion(qNo).setAnswers(question.getAnswers());
		getQuestion(qNo).setTaggingTopic(question.getTaggingTopic());
		getQuestion(qNo).setMarks(question.getMarks());
		getQuestion(qNo).setOptions(question.getOptions());
	}

}
