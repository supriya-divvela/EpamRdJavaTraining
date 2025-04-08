package com.epam.testservice;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.epam.exception.DuplicateQuestionNumberException;
import com.epam.exception.EmptyQuestionLibraryException;
import com.epam.exception.QuestionNotFoundException;
import com.epam.model.Question;
import com.epam.repository.QuestionDao;
import com.epam.service.QuestionServiceImplementation;

@ExtendWith(MockitoExtension.class)
class TestQuestionService {
	@Mock
	private QuestionDao questionDao;
	@InjectMocks
	private QuestionServiceImplementation questionService;
	private List<Question> questionsList;

	@BeforeEach
	public void createQuestions() {
		questionsList = new ArrayList<>(Arrays.asList(
				new Question(1, "What is java ?",
						new ArrayList<String>(Arrays.asList("object oriented", "platform independent", "not secure",
								"has pointers concept")),
						"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5),
				new Question(2, "What is java ?",
						new ArrayList<String>(Arrays.asList("object oriented", "platform independent", "not secure",
								"has pointers concept")),
						"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5),
				new Question(3, "What is java ?",
						new ArrayList<String>(Arrays.asList("object oriented", "platform independent", "not secure",
								"has pointers concept")),
						"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5)));
	}

	@Test
	void testAddingNullQuestion() throws DuplicateQuestionNumberException {
		assertThrows(NullPointerException.class, () -> questionService.addQuestion(null));
	}

	@Test
	void testAddingDuplicateQuestion() {
		 when(questionDao.getQuestionsList()).thenReturn(questionsList);
		assertThrows(DuplicateQuestionNumberException.class,()->questionService.addQuestion(new Question(1, "What is java ?",
				new ArrayList<String>(Arrays.asList("object oriented", "platform independent", "not secure",
						"has pointers concept")),"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5)));
	}

	@Test
	void testAddingNewQuestion() throws DuplicateQuestionNumberException {
		 when(questionDao.getQuestionsList()).thenReturn(questionsList);
		 Question question=new Question(4, "What is java ?",
					new ArrayList<String>(Arrays.asList("object oriented", "platform independent", "not secure",
							"has pointers concept")),"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5);
		 doNothing().when(questionDao).addQuestion(question);
		 questionService.addQuestion(question);
		assertTrue(questionDao.getQuestionsList().size()>0);
	}

	@Test
	void testEmptyLibrary() {
		List<Question> questions = new ArrayList<>();
		when(questionDao.getQuestionsList()).thenReturn(questions);
		assertTrue(questionService.isEmptyQuestionLibrary());
	}

	@Test
	void testNonEmptyLibrary() {
		 when(questionDao.getQuestionsList()).thenReturn(questionsList);
		assertFalse(questionService.isEmptyQuestionLibrary());
	}

	@Test
	void testFindQuestionInLibrary() {
		 when(questionDao.getQuestionsList()).thenReturn(questionsList);
		 assertTrue(questionService.findQuestion(1));
	}

	@Test
	void testFindQuestionNotInLibrary() {
		 when(questionDao.getQuestionsList()).thenReturn(questionsList);
		 assertFalse(questionService.findQuestion(6986));
	}

	@Test
	void testRemovingNullQuestion() throws DuplicateQuestionNumberException {
		assertThrows(NullPointerException.class, () -> questionService.removeQuestion(null));
	}

	@Test
	void testRemovingQuestionNotPresentInLibrary() {
		 when(questionDao.getQuestionsList()).thenReturn(questionsList);
		assertThrows(QuestionNotFoundException.class,()->questionService.removeQuestion(new Question(17986, "What is java ?",
				new ArrayList<String>(Arrays.asList("object oriented", "platform independent", "not secure",
						"has pointers concept")),"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5)));
	}

	@Test
	void testRemovingQuestionPresentInLibrary() throws QuestionNotFoundException {
		 when(questionDao.getQuestionsList()).thenReturn(questionsList);
		 Question question=new Question(1, "What is java ?",
					new ArrayList<String>(Arrays.asList("object oriented", "platform independent", "not secure",
							"has pointers concept")),"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5);
		 doNothing().when(questionDao).removeQuestion(question);
		 questionService.removeQuestion(question);
		 assertTrue(questionDao.getQuestionsList().size()>0);
	}

	@Test
	void testGetQuestion() throws QuestionNotFoundException {
		when(questionDao.getQuestionsList()).thenReturn(questionsList);
		assertEquals(new Question(1, "What is java ?",
					new ArrayList<String>(Arrays.asList("object oriented", "platform independent", "not secure",
							"has pointers concept")),"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5)
		 .toString(),questionService.getQuestion(1).toString());
	}

	@Test
	void testGetQuestionNotPresentInQuestionLibrary() throws QuestionNotFoundException {
		when(questionDao.getQuestionsList()).thenReturn(questionsList);
		assertThrows(QuestionNotFoundException.class,()->questionService.getQuestion(89754));
	}

	@Test
	void testDisplayQuestionsInLibrary() throws EmptyQuestionLibraryException {
		List<Question> listOfQuestions = new ArrayList<>();
		Question question = new Question(89754, "What is java ?",
				new ArrayList<String>(
						Arrays.asList("object oriented", "platform independent", "not secure", "has pointers concept")),
				"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5);
		listOfQuestions.add(question);
		when(questionDao.getQuestionsList()).thenReturn(listOfQuestions);
		assertEquals("\n" + question.toString() + "\n", questionService.displayQuestions());
	}

	@Test
	void testDisplayEmptyQuestionLibrary() {
		questionsList.clear();
		when(questionDao.getQuestionsList()).thenReturn(questionsList);
		assertThrows(EmptyQuestionLibraryException.class, () -> questionService.displayQuestions());
	}

	@Test
	void testUpdateTitle() throws QuestionNotFoundException{
		when(questionDao.getQuestionsList()).thenReturn(questionsList);
		Question question=new Question(1, "What is oops ?",
				new ArrayList<String>(Arrays.asList("object oriented", "platform independent", "not secure",
						"has pointers concept")),"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5);
		questionService.updateTitle(1, "What is oops ?");
		assertEquals(question.toString(),questionService.getQuestion(1).toString());
	}

	@Test
	void testUpdateTitleForQuestionNotInLibrary() throws QuestionNotFoundException{
		when(questionDao.getQuestionsList()).thenReturn(questionsList);
		assertThrows(QuestionNotFoundException.class,()->questionService.updateTitle(67982,"What is oops ?"));
	}

	@Test
	void testUpdateOptions() throws QuestionNotFoundException{
		when(questionDao.getQuestionsList()).thenReturn(questionsList);
		Question question=new Question(1, "What is java ?",
				new ArrayList<String>(Arrays.asList("portable","all of the above")),"easy", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5);
		questionService.updateOptions(1, new ArrayList<String>(Arrays.asList("portable","all of the above")));
		assertEquals(question.toString(),questionService.getQuestion(1).toString());
	}

	@Test
	void testUpdateOptionsForQuestionNotInLibrary() throws QuestionNotFoundException{
		when(questionDao.getQuestionsList()).thenReturn(questionsList);
		assertThrows(QuestionNotFoundException.class,()->questionService.updateOptions(67982,new ArrayList<String>(Arrays.asList("portable","all of the above"))));
	}

	@Test
	void testUpdateDifficulty() throws QuestionNotFoundException{
		when(questionDao.getQuestionsList()).thenReturn(questionsList);
		Question question=new Question(1, "What is java ?",
				new ArrayList<String>(Arrays.asList("object oriented", "platform independent", "not secure",
						"has pointers concept")),"Hard", "java", new TreeSet<Integer>(Arrays.asList(3, 4)), 5);
		questionService.updateDifficulty(1,"Hard");
		assertEquals(question.toString(),questionService.getQuestion(1).toString());
	}

	@Test
	void testUpdateDifficultyForQuestionNotInLibrary() throws QuestionNotFoundException{
		when(questionDao.getQuestionsList()).thenReturn(questionsList);
		assertThrows(QuestionNotFoundException.class,()->questionService.updateDifficulty(67982,"Hard"));
	}

	@Test
	void testUpdateTaggingTopic() throws QuestionNotFoundException{
		when(questionDao.getQuestionsList()).thenReturn(questionsList);
		Question question=new Question(1, "What is java ?",
				new ArrayList<String>(Arrays.asList("object oriented", "platform independent", "not secure",
						"has pointers concept")),"easy", "Python", new TreeSet<Integer>(Arrays.asList(3, 4)), 5);
		questionService.updateTaggingTopic(1,"Python");
		assertEquals(question.toString(),questionService.getQuestion(1).toString());
	}

	@Test
	void testUpdateTaggingTopicForQuestionNotInLibrary() throws QuestionNotFoundException{
		when(questionDao.getQuestionsList()).thenReturn(questionsList);
		assertThrows(QuestionNotFoundException.class,()->questionService.updateTaggingTopic(123455, "Python"));
	}

	@Test
	void testUpdateAnswers() throws QuestionNotFoundException{
		when(questionDao.getQuestionsList()).thenReturn(questionsList);
		Question question=new Question(1, "What is java ?",
				new ArrayList<String>(Arrays.asList("object oriented", "platform independent", "not secure",
						"has pointers concept")),"easy", "java", new TreeSet<Integer>(Arrays.asList(1,2)), 5);
		questionService.updateAnswers(1,new TreeSet<Integer>(Arrays.asList(1,2)));
		assertEquals(question.toString(),questionService.getQuestion(1).toString());
	}

	@Test
	void testUpdateAnswersForQuestionNotInLibrary() throws QuestionNotFoundException{
		when(questionDao.getQuestionsList()).thenReturn(questionsList);
		assertThrows(QuestionNotFoundException.class,()->questionService.updateAnswers(123455, new TreeSet<Integer>(Arrays.asList(1,2))));
	}

	@Test
	void testUpdateMarks() throws QuestionNotFoundException{
		when(questionDao.getQuestionsList()).thenReturn(questionsList);
		Question question=new Question(1, "What is java ?",
				new ArrayList<String>(Arrays.asList("object oriented", "platform independent", "not secure",
						"has pointers concept")),"easy", "java", new TreeSet<Integer>(Arrays.asList(3,4)), 10);
		questionService.updateMarks(1,10);
		assertEquals(question.toString(),questionService.getQuestion(1).toString());
	}

	@Test
	void testUpdateMarksForQuestionNotInLibrary() throws QuestionNotFoundException{
		when(questionDao.getQuestionsList()).thenReturn(questionsList);
		assertThrows(QuestionNotFoundException.class,()->questionService.updateMarks(123455,10));
	}
}
